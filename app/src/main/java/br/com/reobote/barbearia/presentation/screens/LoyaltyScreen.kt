package br.com.reobote.barbearia.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.components.ReoboteCard
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.BorderOutlineVariant
import br.com.reobote.barbearia.presentation.theme.DarkGreenContainer
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.OnNeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.Platinum500
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark

data class LoyaltyStampHistory(
    val id: String,
    val serviceName: String,
    val dateFormatted: String,
    val stampsAdded: Int,
)

val mockStampHistory = listOf(
    LoyaltyStampHistory("1", "Corte Degradê Navalhado + Barba", "10 de Maio, 2025", +1),
    LoyaltyStampHistory("2", "Combo Cabelo + Barba", "28 de Abril, 2025", +1),
    LoyaltyStampHistory("3", "Corte Social Clássico", "15 de Abril, 2025", +1),
    LoyaltyStampHistory("4", "Corte Degradê Navalhado", "01 de Abril, 2025", +1),
    LoyaltyStampHistory("5", "Barba com Toalha Quente", "20 de Março, 2025", +1),
    LoyaltyStampHistory("6", "Corte Degradê Navalhado", "05 de Março, 2025", +1),
    LoyaltyStampHistory("7", "Corte Degradê Navalhado", "20 de Fevereiro, 2025", +1),
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoyaltyScreen(
    currentStamps: Int = 7,
    totalStamps: Int = 10,
    onNavigateToBooking: () -> Unit = {},
) {
    val progressTarget = (currentStamps.toFloat() / totalStamps.toFloat()).coerceIn(0f, 1f)
    val animatedProgress = remember { Animatable(0f) }
    val remainingStamps = (totalStamps - currentStamps).coerceAtLeast(0)

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = progressTarget,
            animationSpec = tween(durationMillis = 1000),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background),
    ) {
        // Top Header
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Text(
                text = "Cartão Fidelidade",
                style = ReoboteTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Platinum100,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Corte cabelos, acumule selos e ganhe prêmios exclusivos",
                style = ReoboteTheme.typography.bodyMedium,
                color = Platinum400,
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // 1. Hero Animated Loyalty Card
            item {
                ReoboteCard(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = 20.dp,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "REOBOTE VIP STAMP",
                                style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = NeonGreen,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "1 Corte ou Barba Grátis",
                                style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                                color = Platinum100,
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (remainingStamps > 0) {
                                    "Faltam apenas $remainingStamps ${if (remainingStamps == 1) "corte" else "cortes"}!"
                                } else {
                                    "Parabéns! Seu prêmio está pronto para resgate!"
                                },
                                style = ReoboteTheme.typography.bodyMedium,
                                color = Platinum400,
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Animated Circular Progress Bar Indicator
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(80.dp),
                        ) {
                            CircularProgressIndicator(
                                progress = { animatedProgress.value },
                                modifier = Modifier.size(80.dp),
                                color = NeonGreen,
                                trackColor = SurfaceContainerDark,
                                strokeWidth = 7.dp,
                                strokeCap = StrokeCap.Round,
                            )

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$currentStamps/$totalStamps",
                                    style = ReoboteTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                                    color = NeonGreen,
                                )
                                Text(
                                    text = "SELOS",
                                    style = ReoboteTheme.typography.labelSmall.copy(fontSize = 9.sp),
                                    color = Platinum400,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))
                    HorizontalDivider(color = BorderOutlineVariant)
                    Spacer(modifier = Modifier.height(18.dp))

                    // 2. Stamp Grid
                    Text(
                        text = "SEUS SELOS ACUMULADOS",
                        style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Platinum400,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        for (i in 1..totalStamps) {
                            val isStamped = i <= currentStamps
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isStamped) DarkGreenContainer else SurfaceContainerDark,
                                    )
                                    .border(
                                        width = if (isStamped) 1.5.dp else 1.dp,
                                        color = if (isStamped) NeonGreen else BorderOutline,
                                        shape = CircleShape,
                                    ),
                                contentAlignment = Alignment.Center,
                            ) {
                                if (isStamped) {
                                    Text(
                                        text = "✓",
                                        style = ReoboteTheme.typography.titleMedium,
                                        color = NeonGreen,
                                        fontWeight = FontWeight.Bold,
                                    )
                                } else {
                                    Text(
                                        text = i.toString(),
                                        style = ReoboteTheme.typography.labelMedium,
                                        color = Platinum500,
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    ReoboteButton(
                        text = if (remainingStamps == 0) "Resgatar Prêmio Agora" else "Agendar para Ganhar +1 Selo",
                        onClick = onNavigateToBooking,
                        modifier = Modifier.fillMaxWidth(),
                        variant = ReoboteButtonVariant.Primary,
                    )
                }
            }

            // 3. History of Points / Stamps
            item {
                Text(
                    text = "Histórico de Selos",
                    style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Platinum100,
                )
            }

            items(mockStampHistory, key = { it.id }) { history ->
                ReoboteCard(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = 14.dp,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(DarkGreenContainer),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "✂️",
                                    fontSize = 18.sp,
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = history.serviceName,
                                    style = ReoboteTheme.typography.titleMedium,
                                    color = Platinum100,
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = history.dateFormatted,
                                    style = ReoboteTheme.typography.bodySmall,
                                    color = Platinum400,
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(ReoboteTheme.shapes.small)
                                .background(DarkGreenContainer)
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        ) {
                            Text(
                                text = "+${history.stampsAdded} Selo",
                                style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = NeonGreen,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun LoyaltyScreenPreview() {
    ReoboteTheme {
        LoyaltyScreen()
    }
}
