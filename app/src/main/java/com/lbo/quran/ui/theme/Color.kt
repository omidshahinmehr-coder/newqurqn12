package com.lbo.quran.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// رنگ‌های اصلی برند: سرمه‌ای مایل به آبی + طلایی
private val NavyPrimary = Color(0xFF1B4079)
private val NavyPrimaryLight = Color(0xFFA9C6FF)
private val Gold = Color(0xFFC9A227)
private val GoldLight = Color(0xFFE6C55C)

val QuranLightColorScheme = lightColorScheme(
    primary = NavyPrimary,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD9E2F5),
    onPrimaryContainer = Color(0xFF0D2547),
    secondary = Gold,
    onSecondary = Color(0xFF3A2E00),
    secondaryContainer = Color(0xFFF5E9BE),
    onSecondaryContainer = Color(0xFF4A3A00),
    tertiary = NavyPrimary,
    onTertiary = Color(0xFFFFFFFF),
    background = Color(0xFFF3F6FB),
    onBackground = Color(0xFF1A1C1E),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFE3E8F0),
    onSurfaceVariant = Color(0xFF43474E),
    outline = Color(0xFF73777F),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
)

val QuranDarkColorScheme = darkColorScheme(
    primary = NavyPrimaryLight,
    onPrimary = Color(0xFF0A2A54),
    primaryContainer = NavyPrimary,
    onPrimaryContainer = Color(0xFFD9E2F5),
    secondary = GoldLight,
    onSecondary = Color(0xFF3A2E00),
    secondaryContainer = Color(0xFF5A4700),
    onSecondaryContainer = Color(0xFFF5E9BE),
    tertiary = NavyPrimaryLight,
    onTertiary = Color(0xFF0A2A54),
    background = Color(0xFF0A1730),
    onBackground = Color(0xFFE2E6EF),
    surface = Color(0xFF101B33),
    onSurface = Color(0xFFE2E6EF),
    surfaceVariant = Color(0xFF2C3F60),
    onSurfaceVariant = Color(0xFFC3C9D6),
    outline = Color(0xFF8D93A6),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
)
