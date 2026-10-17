package br.com.reobote.barbearia.presentation.theme

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = NeonGreen,
    onPrimary = OnNeonGreen,
    primaryContainer = DarkGreenContainer,
    onPrimaryContainer = OnDarkGreenContainer,
    secondary = EmeraldGreen,
    onSecondary = Color.Black,
    secondaryContainer = SuccessContainer,
    onSecondaryContainer = OnSuccessContainer,
    tertiary = Platinum300,
    onTertiary = Color.Black,
    background = BackgroundDark,
    onBackground = Platinum100,
    surface = SurfaceDark,
    onSurface = Platinum100,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = Platinum400,
    surfaceContainer = SurfaceContainerDark,
    surfaceContainerHigh = SurfaceContainerHighDark,
    outline = BorderOutline,
    outlineVariant = BorderOutlineVariant,
    error = ErrorRed,
    onError = Color.White,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
)

@Composable
fun ReoboteTheme(
    darkTheme: Boolean = true, // Dark theme is default for Reobote Barbearia
    elevation: ReoboteElevation = ReoboteElevation(),
    stateColors: ReoboteStateColors = ReoboteStateColors(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else DarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                window.navigationBarColor = colorScheme.background.toArgb()
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = false
                insetsController.isAppearanceLightNavigationBars = false
            }
        }
    }

    CompositionLocalProvider(
        LocalReoboteElevation provides elevation,
        LocalReoboteStateColors provides stateColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ReoboteTypography,
            shapes = ReoboteShapes,
            content = content,
        )
    }
}

/**
 * Convenience accessor for Reobote Barbearia Design System tokens.
 */
object ReoboteTheme {
    val colors: ColorScheme
        @Composable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val elevation: ReoboteElevation
        @Composable
        get() = LocalReoboteElevation.current

    val stateColors: ReoboteStateColors
        @Composable
        get() = LocalReoboteStateColors.current
}
