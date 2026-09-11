package com.lbo.quran.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream

object QuranDatabaseHelper {

    @Volatile private var db: SQLiteDatabase? = null

    // هر بار که ساختار یا محتوای quran.db.gz داخل assets تغییر کند (مثلاً افزودن
    // ستون‌های جدید)، این عدد باید بالا برود؛ وگرنه کاربرانی که برنامه را قبلاً نصب
    // کرده‌اند همچنان با نسخه‌ی قدیمیِ دیتابیس (کپی‌شده روی دستگاه) کار می‌کنند و
    // آپدیت اپ به‌تنهایی آن را جایگزین نمی‌کند.
    private const val DB_VERSION = 3
    private const val PREFS_NAME = "quran_db_meta"
    private const val PREF_KEY_VERSION = "db_version"

    fun getDatabase(context: Context): SQLiteDatabase {
        db?.let { return it }
        synchronized(this) {
            db?.let { return it }
            val dbFile = context.getDatabasePath("quran.db")
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val installedVersion = prefs.getInt(PREF_KEY_VERSION, 0)
            if (!dbFile.exists() || installedVersion < DB_VERSION) {
                dbFile.parentFile?.mkdirs()
                extractDatabase(context, dbFile)
                prefs.edit().putInt(PREF_KEY_VERSION, DB_VERSION).apply()
            }
            val opened = SQLiteDatabase.openDatabase(
                dbFile.path, null, SQLiteDatabase.OPEN_READONLY
            )
            db = opened
            return opened
        }
    }

    /**
     * برخی ابزارهای بسته‌بندی اندروید (بسته به نسخه و محیط CI) فایل‌های .gz داخل
     * assets را در حین ساخت APK به‌صورت خودکار باز می‌کنند -- گاهی با تغییر نام
     * (quran.db.gz به quran.db) و گاهی با حفظ نام ولی محتوای غیرفشرده. برای اینکه
     * برنامه در هر دو حالت (و حالت عادیِ فایل واقعاً gzip) کار کند، این تابع اول
     * هر دو نام را امتحان می‌کند و سپس با بررسی دو بایت اول محتوا (امضای gzip:
     * 0x1f 0x8b) تشخیص می‌دهد که باید gunzip کند یا مستقیم کپی.
     */
    private fun extractDatabase(context: Context, dbFile: java.io.File) {
        val assetName = listOf("quran.db.gz", "quran.db").firstOrNull { name ->
            try {
                context.assets.open(name).close()
                true
            } catch (e: java.io.IOException) {
                false
            }
        } ?: throw java.io.FileNotFoundException(
            "نه quran.db.gz و نه quran.db در assets پیدا نشد"
        )

        BufferedInputStream(context.assets.open(assetName)).use { input ->
            input.mark(2)
            val b0 = input.read()
            val b1 = input.read()
            input.reset()
            val isGzip = b0 == 0x1f && b1 == 0x8b

            val source: InputStream = if (isGzip) GZIPInputStream(input) else input
            source.use { s ->
                FileOutputStream(dbFile).use { output ->
                    s.copyTo(output)
                }
            }
        }
    }
}
