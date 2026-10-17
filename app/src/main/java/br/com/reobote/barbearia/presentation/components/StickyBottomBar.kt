package br.com.reobote.barbearia.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.reobote.barbearia.presentation.theme.BackgroundDark
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceDark

@Composable
fun StickyBottomBar(
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    summaryTitle: String? = "Total",
    priceText: String? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = SurfaceDark.copy(alpha = 0.95f),
        contentColor = Platinum100,
        border = BorderStroke(width = 1.dp, color = BorderOutline),
        shadowElevation = ReoboteTheme.elevation.high,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (priceText != null || summaryTitle != null) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    if (summaryTitle != null) {
                        Text(
                            text = summaryTitle,
                            style = ReoboteTheme.typography.labelMedium,
                            color = Platinum400,
                        )
                    }
                    if (priceText != null) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = priceText,
                            style = ReoboteTheme.typography.headlineSmall,
                            color = NeonGreen,
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))
            }

            ReoboteButton(
                text = buttonText,
                onClick = onButtonClick,
                modifier = if (priceText == null && summaryTitle == null) Modifier.fillMaxWidth() else Modifier,
                variant = ReoboteButtonVariant.Primary,
                enabled = enabled,
                isLoading = isLoading,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun StickyBottomBarPreview() {
    ReoboteTheme {
        Column {
            StickyBottomBar(
                summaryTitle = "Total (1 serviço)",
                priceText = "R$ 45,00",
                buttonText = "Confirmar Agendamento",
                onButtonClick = {},
            )
        }
    }
}
