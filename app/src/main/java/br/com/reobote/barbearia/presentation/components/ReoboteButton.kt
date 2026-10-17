package br.com.reobote.barbearia.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.OnNeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum500
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

enum class ReoboteButtonVariant {
    Primary,
    Secondary,
    Ghost,
}

@Composable
fun ReoboteButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ReoboteButtonVariant = ReoboteButtonVariant.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    val buttonShape = ReoboteTheme.shapes.medium

    val contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp)

    val contentLambda: @Composable () -> Unit = {
        if (isLoading) {
            val progressColor = when (variant) {
                ReoboteButtonVariant.Primary -> OnNeonGreen
                ReoboteButtonVariant.Secondary -> NeonGreen
                ReoboteButtonVariant.Ghost -> NeonGreen
            }
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = progressColor,
                strokeWidth = 2.5.dp,
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = ReoboteTheme.typography.titleMedium,
                )
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    trailingIcon()
                }
            }
        }
    }

    when (variant) {
        ReoboteButtonVariant.Primary -> {
            Button(
                onClick = onClick,
                modifier = modifier.height(52.dp),
                enabled = enabled && !isLoading,
                shape = buttonShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonGreen,
                    contentColor = OnNeonGreen,
                    disabledContainerColor = SurfaceVariantDark,
                    disabledContentColor = Platinum500,
                ),
                contentPadding = contentPadding,
            ) {
                contentLambda()
            }
        }

        ReoboteButtonVariant.Secondary -> {
            OutlinedButton(
                onClick = onClick,
                modifier = modifier.height(52.dp),
                enabled = enabled && !isLoading,
                shape = buttonShape,
                border = BorderStroke(
                    width = 1.dp,
                    color = if (enabled) BorderOutline else BorderOutline.copy(alpha = 0.5f),
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = SurfaceVariantDark,
                    contentColor = Platinum100,
                    disabledContainerColor = SurfaceVariantDark.copy(alpha = 0.5f),
                    disabledContentColor = Platinum500,
                ),
                contentPadding = contentPadding,
            ) {
                contentLambda()
            }
        }

        ReoboteButtonVariant.Ghost -> {
            TextButton(
                onClick = onClick,
                modifier = modifier.height(52.dp),
                enabled = enabled && !isLoading,
                shape = buttonShape,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = NeonGreen,
                    disabledContentColor = Platinum500,
                ),
                contentPadding = contentPadding,
            ) {
                contentLambda()
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun ReoboteButtonPreview() {
    ReoboteTheme {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ReoboteButton(
                text = "Agendar Horário (Primary)",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Primary,
            )
            ReoboteButton(
                text = "Carregando (Primary)",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Primary,
                isLoading = true,
            )
            ReoboteButton(
                text = "Ver Detalhes (Secondary)",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Secondary,
            )
            ReoboteButton(
                text = "Cancelar (Ghost)",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Ghost,
            )
        }
    }
}
