package br.com.reobote.barbearia.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.components.ReoboteCard
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.Platinum500
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

@Composable
fun AppleLoginScreen(
    onContinueWithApple: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "🍎",
                    fontSize = 32.sp,
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Entrar com a Apple",
                    style = ReoboteTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = Platinum100,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Conecte-se com o seu Apple ID para agendar seus serviços mantendo a privacidade de seus dados.",
                style = ReoboteTheme.typography.bodyLarge,
                color = Platinum400,
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Apple ID Preview Glassmorphic Card
            ReoboteCard(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = 16.dp,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(SurfaceVariantDark)
                            .border(1.dp, NeonGreen, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "🍎",
                            fontSize = 22.sp,
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            text = "Apple ID Ativo",
                            style = ReoboteTheme.typography.titleMedium,
                            color = Platinum100,
                        )
                        Text(
                            text = "Ocultar meu e-mail (Proteção Apple)",
                            style = ReoboteTheme.typography.bodyMedium,
                            color = Platinum500,
                        )
                    }
                }
            }
        }

        // Bottom CTAs
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ReoboteButton(
                text = "Continuar com Apple ID",
                onClick = onContinueWithApple,
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Primary,
                leadingIcon = { Text("🍎", fontSize = 18.sp) },
            )

            ReoboteButton(
                text = "Voltar",
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Ghost,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun AppleLoginScreenPreview() {
    ReoboteTheme {
        AppleLoginScreen()
    }
}
