package br.com.reobote.barbearia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.DarkGreenContainer
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.Platinum500
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoyaltyProgressCard(
    currentStamps: Int,
    modifier: Modifier = Modifier,
    totalStamps: Int = 10,
    rewardTitle: String = "1 Corte Grátis",
) {
    val progress = (currentStamps.toFloat() / totalStamps.toFloat()).coerceIn(0f, 1f)
    val remaining = (totalStamps - currentStamps).coerceAtLeast(0)

    ReoboteCard(
        modifier = modifier,
        contentPadding = 18.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = "Cartão Fidelidade",
                    style = ReoboteTheme.typography.titleMedium,
                    color = Platinum100,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Prêmio: $rewardTitle",
                    style = ReoboteTheme.typography.bodySmall,
                    color = NeonGreen,
                )
            }

            Text(
                text = "$currentStamps/$totalStamps",
                style = ReoboteTheme.typography.titleLarge,
                color = NeonGreen,
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Linear Progress Bar
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(ReoboteTheme.shapes.small),
            color = NeonGreen,
            trackColor = SurfaceContainerDark,
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Stamp Grid
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            for (i in 1..totalStamps) {
                val isStamped = i <= currentStamps
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            if (isStamped) DarkGreenContainer else SurfaceContainerDark,
                        )
                        .border(
                            width = 1.dp,
                            color = if (isStamped) NeonGreen else BorderOutline,
                            shape = CircleShape,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = if (isStamped) "✓" else "$i",
                        style = ReoboteTheme.typography.labelSmall,
                        color = if (isStamped) NeonGreen else Platinum500,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Helper message
        Text(
            text = if (remaining > 0) {
                "Faltam $remaining ${if (remaining == 1) "corte" else "cortes"} para resgatar!"
            } else {
                "Parabéns! Resgate seu corte grátis agora!"
            },
            style = ReoboteTheme.typography.bodyMedium,
            color = Platinum400,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun LoyaltyProgressCardPreview() {
    ReoboteTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            LoyaltyProgressCard(
                currentStamps = 7,
                totalStamps = 10,
                rewardTitle = "1 Corte ou Barba Grátis",
            )
        }
    }
}
