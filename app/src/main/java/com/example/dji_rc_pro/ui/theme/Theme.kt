package com.example.dji_rc_pro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DeckBlue,
    secondary = DeckOrange,
    tertiary = DeckGreen,
    background = DeckBackground,
    surface = DeckSurface,
    surfaceVariant = DeckSurfaceMuted,
    onPrimary = DeckSurface,
    onSecondary = DeckSurface,
    onTertiary = DeckSurface,
    onBackground = DeckTextPrimary,
    onSurface = DeckTextPrimary,
    onSurfaceVariant = DeckTextSecondary,
    outline = DeckBorder,
    error = DeckRed
)

private val LightColorScheme = lightColorScheme(
    primary = DeckBlue,
    secondary = DeckOrange,
    tertiary = DeckGreen,
    background = DeckBackground,
    surface = DeckSurface,
    surfaceVariant = DeckSurfaceMuted,
    primaryContainer = DeckBlueSoft,
    secondaryContainer = DeckOrangeSoft,
    tertiaryContainer = DeckGreenSoft,
    errorContainer = DeckRedSoft,
    onPrimary = DeckSurface,
    onSecondary = DeckSurface,
    onTertiary = DeckSurface,
    onBackground = DeckTextPrimary,
    onSurface = DeckTextPrimary,
    onSurfaceVariant = DeckTextSecondary,
    onPrimaryContainer = DeckTextPrimary,
    onSecondaryContainer = DeckTextPrimary,
    onTertiaryContainer = DeckTextPrimary,
    outline = DeckBorder,
    error = DeckRed
)

@Composable
fun Dji_RC_ProTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme && dynamicColor) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
