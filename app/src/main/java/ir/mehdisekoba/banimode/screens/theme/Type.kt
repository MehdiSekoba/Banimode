package ir.mehdisekoba.banimode.screens.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ir.mehdisekoba.banimode.R


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.is_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.is_light)),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.tanha_fd)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.tanha_fd)),
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
)
