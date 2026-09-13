package com.lbo.quran.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

private fun TextStyle.withEstedad() = this.copy(fontFamily = EstedadFont)

val AppTypography = Typography().let { base ->
    Typography(
        displayLarge = base.displayLarge.withEstedad(),
        displayMedium = base.displayMedium.withEstedad(),
        displaySmall = base.displaySmall.withEstedad(),
        headlineLarge = base.headlineLarge.withEstedad(),
        headlineMedium = base.headlineMedium.withEstedad(),
        headlineSmall = base.headlineSmall.withEstedad(),
        titleLarge = base.titleLarge.withEstedad(),
        titleMedium = base.titleMedium.withEstedad(),
        titleSmall = base.titleSmall.withEstedad(),
        bodyLarge = base.bodyLarge.withEstedad(),
        bodyMedium = base.bodyMedium.withEstedad(),
        bodySmall = base.bodySmall.withEstedad(),
        labelLarge = base.labelLarge.withEstedad(),
        labelMedium = base.labelMedium.withEstedad(),
        labelSmall = base.labelSmall.withEstedad(),
    )
}

/** تم اصلی برنامه: پالت سرمه‌ای مایل به آبی + طلایی، با پشتیبانی خودکار از حالت تاریک سیستم. */
@Composable
fun QuranTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) QuranDarkColorScheme else QuranLightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
