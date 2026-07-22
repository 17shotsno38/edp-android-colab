package com.example.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val MockupPrimary = Color(0xFFF48FB1)
val MockupBackground = Color(0xFF121212)
val MockupSurface = Color(0xFF1E1E1E)

private val MockupColorScheme = darkColorScheme(
    primary = MockupPrimary,
    background = MockupBackground,
    surface = MockupSurface,
    onBackground = Color.White,
    onSurface = Color.White
)

val MockupTypography = Typography(
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)

@Composable
fun MockupTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MockupColorScheme,
        typography = MockupTypography,
        content = content
    )
}
