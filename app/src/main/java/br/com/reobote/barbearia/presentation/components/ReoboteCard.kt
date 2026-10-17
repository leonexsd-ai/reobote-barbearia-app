package br.com.reobote.barbearia.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.BorderOutlineVariant
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

/**
 * Custom Card component for Reobote Barbearia with a subtle Glassmorphism effect.
 * Uses semi-transparent dark container, soft frosted border, and rounded corners.
 */
@Composable
fun ReoboteCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: Shape = ReoboteTheme.shapes.large,
    borderAlpha: Float = 0.6f,
    border: BorderStroke? = null,
    contentPadding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    val glassBorder = border ?: BorderStroke(
        width = 1.dp,
        brush = Brush.linearGradient(
            colors = listOf(
                BorderOutlineVariant.copy(alpha = borderAlpha),
                BorderOutline.copy(alpha = borderAlpha * 0.4f),
            ),
        ),
    )

    val glassBackground = SurfaceVariantDark.copy(alpha = 0.82f)

    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            border = glassBorder,
            colors = CardDefaults.cardColors(
                containerColor = glassBackground,
                contentColor = Platinum100,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = ReoboteTheme.elevation.low,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                content = content,
            )
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            border = glassBorder,
            colors = CardDefaults.cardColors(
                containerColor = glassBackground,
                contentColor = Platinum100,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = ReoboteTheme.elevation.low,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                content = content,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun ReoboteCardPreview() {
    ReoboteTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            ReoboteCard(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Cartão Reobote (Glassmorphism)",
                    style = ReoboteTheme.typography.titleLarge,
                    color = ReoboteTheme.colors.primary,
                )
                Text(
                    text = "Efeito de vidro fosco sutil com fundo semi-transparente e borda com gradiente.",
                    style = ReoboteTheme.typography.bodyMedium,
                    color = Platinum400,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }
    }
}
