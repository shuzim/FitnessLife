package com.example.fitnesslife.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


// As cores de Purple80, etc., vêm do Color.kt
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    onPrimary = Color(0xFF000000),
    secondary = Color(0xFF03DAC5),
    onSecondary = Color(0xFF000000),
    tertiary = Color(0xFFCF6679),
    onTertiary = Color(0xFF000000),
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    primaryContainer = Color(0xFF3700B3),
    onPrimaryContainer = Color(0xFFE0E0FF),
    error = Color(0xFFCF6679),
    onError = Color(0xFF000000),
    errorContainer = Color(0xFFB00020),
    onErrorContainer = Color(0xFFE0E0FF)
)

private val LightColorScheme = lightColorScheme(
    primary = VibrantPurpleLight,
    onPrimary = WhiteOnVibrantPurple,
    secondary = VibrantCyanLight,
    onSecondary = WhiteOnVibrantCyan,
    tertiary = VibrantPinkLight,
    onTertiary = WhiteOnVibrantPink,
    background = SoftWhiteBackground,
    onBackground = DarkTextOnLight,
    surface = SoftWhiteSurface,
    onSurface = DarkTextOnLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = DarkTextOnPrimaryContainer,
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = SlightlyLighterTextOnLight
)

@Composable
fun FitnessLifeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Manter true para Material You em Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb() // Use background para a barra de status
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}