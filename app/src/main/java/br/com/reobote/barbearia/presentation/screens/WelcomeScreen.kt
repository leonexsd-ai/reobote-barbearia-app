package br.com.reobote.barbearia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme

@Composable
fun WelcomeScreen(
    onNavigateToPhoneInput: () -> Unit = {},
    onNavigateToGoogleLogin: () -> Unit = {},
    onNavigateToAppleLogin: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Branding & Logo Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "✂️",
                fontSize = 56.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "REOBOTE BARBEARIA",
                style = ReoboteTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp,
                ),
                color = NeonGreen,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "O seu estilo no seu horário. Agende seus cortes e serviços com praticidade e atendimento exclusivo.",
                style = ReoboteTheme.typography.bodyLarge,
                color = Platinum400,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp),
            )
        }

        // Action Buttons Group
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ReoboteButton(
                text = "Entrar com WhatsApp",
                onClick = onNavigateToPhoneInput,
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Primary,
                leadingIcon = { Text("💬", fontSize = 18.sp) },
            )

            ReoboteButton(
                text = "Entrar com Google",
                onClick = onNavigateToGoogleLogin,
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Secondary,
                leadingIcon = { Text("🌐", fontSize = 18.sp) },
            )

            ReoboteButton(
                text = "Entrar com Apple",
                onClick = onNavigateToAppleLogin,
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Secondary,
                leadingIcon = { Text("🍎", fontSize = 18.sp) },
            )

            ReoboteButton(
                text = "Entrar com Número de Telefone",
                onClick = onNavigateToPhoneInput,
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Ghost,
            )
        }

        // Footer Policy Text
        Text(
            text = "Ao continuar, você concorda com nossos Termos de Uso e Política de Privacidade.",
            style = ReoboteTheme.typography.bodySmall,
            color = Platinum400.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun WelcomeScreenPreview() {
    ReoboteTheme {
        WelcomeScreen()
    }
}
