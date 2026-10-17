package br.com.reobote.barbearia.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Backgrounds & Surfaces (Dark tones #0D0D10 to #16161A)
val BackgroundDark = Color(0xFF0D0D10)
val SurfaceDark = Color(0xFF121215)
val SurfaceVariantDark = Color(0xFF16161A)
val SurfaceContainerDark = Color(0xFF1F1F24)
val SurfaceContainerHighDark = Color(0xFF26262E)

// Borders & Outlines
val BorderOutline = Color(0xFF2A2A32)
val BorderOutlineVariant = Color(0xFF3A3A46)

// Neon Green & Emerald Accents
val NeonGreen = Color(0xFF00E676)
val EmeraldGreen = Color(0xFF10B981)
val NeonGreenDark = Color(0xFF00C853)
val DarkGreenContainer = Color(0xFF00381B)
val OnDarkGreenContainer = Color(0xFFA7F3D0)
val OnNeonGreen = Color(0xFF000000)

// Platinum Grays (Cinzas Platinados)
val Platinum100 = Color(0xFFFAFAFA)
val Platinum200 = Color(0xFFF1F5F9)
val Platinum300 = Color(0xFFE2E8F0)
val Platinum400 = Color(0xFFCBD5E1)
val Platinum500 = Color(0xFF94A3B8)
val Platinum600 = Color(0xFF64748B)
val Platinum700 = Color(0xFF475569)

// State Colors (Cores de Estado)
// Error
val ErrorRed = Color(0xFFEF4444)
val ErrorRedLight = Color(0xFFF87171)
val ErrorContainer = Color(0xFF450A0A)
val OnErrorContainer = Color(0xFFFECDD3)

// Success
val SuccessGreen = Color(0xFF10B981)
val SuccessGreenLight = Color(0xFF34D399)
val SuccessContainer = Color(0xFF064E3B)
val OnSuccessContainer = Color(0xFFA7F3D0)

// Warning
val WarningAmber = Color(0xFFF59E0B)
val WarningAmberLight = Color(0xFFFBBF24)
val WarningContainer = Color(0xFF451A03)
val OnWarningContainer = Color(0xFFFDE68A)

// Info
val InfoBlue = Color(0xFF3B82F6)
val InfoBlueLight = Color(0xFF60A5FA)
val InfoContainer = Color(0xFF1E3A8A)
val OnInfoContainer = Color(0xFFBFDBFE)

/**
 * Custom State Colors object for Design System specific semantic access
 */
@Immutable
data class ReoboteStateColors(
    val error: Color = ErrorRed,
    val onError: Color = Color.White,
    val errorContainer: Color = ErrorContainer,
    val onErrorContainer: Color = OnErrorContainer,
    val success: Color = SuccessGreen,
    val onSuccess: Color = Color.White,
    val successContainer: Color = SuccessContainer,
    val onSuccessContainer: Color = OnSuccessContainer,
    val warning: Color = WarningAmber,
    val onWarning: Color = Color.White,
    val warningContainer: Color = WarningContainer,
    val onWarningContainer: Color = OnWarningContainer,
    val info: Color = InfoBlue,
    val onInfo: Color = Color.White,
    val infoContainer: Color = InfoContainer,
    val onInfoContainer: Color = OnInfoContainer,
)

val LocalReoboteStateColors = staticCompositionLocalOf { ReoboteStateColors() }
