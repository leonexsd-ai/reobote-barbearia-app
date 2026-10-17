package br.com.reobote.barbearia.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Custom Elevation tokens for Reobote Barbearia Design System.
 * Provides soft shadow elevations across cards, dialogs, buttons, and surfaces.
 */
@Immutable
data class ReoboteElevation(
    val none: Dp = 0.dp,
    val low: Dp = 2.dp,
    val default: Dp = 4.dp,
    val medium: Dp = 6.dp,
    val high: Dp = 12.dp,
    val extraHigh: Dp = 24.dp,
)

val LocalReoboteElevation = staticCompositionLocalOf { ReoboteElevation() }
