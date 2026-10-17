package br.com.reobote.barbearia.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.components.ReoboteTextField
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.OnNeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

@Composable
fun ProfileSetupScreen(
    onCompleteProfile: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    var fullName by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(false) }

    val isFormValid = fullName.trim().length >= 3 && termsAccepted

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Complete seu perfil",
                style = ReoboteTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = Platinum100,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sua foto e nome serão usados na identificação dos seus agendamentos.",
                style = ReoboteTheme.typography.bodyLarge,
                color = Platinum400,
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Profile Image Picker Container
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(104.dp)
                        .clip(CircleShape)
                        .background(SurfaceVariantDark)
                        .border(1.5.dp, BorderOutline, CircleShape)
                        .clickable { /* Camera/Gallery Picker action */ },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "👤",
                        fontSize = 44.sp,
                    )

                    // Edit Badge Icon
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(NeonGreen)
                            .border(2.dp, ReoboteTheme.colors.background, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "📷",
                            fontSize = 14.sp,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Full Name Input
            ReoboteTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Nome Completo",
                placeholder = "Digite seu nome e sobrenome",
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Terms & Conditions Checkbox
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { termsAccepted = !termsAccepted },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = NeonGreen,
                        uncheckedColor = BorderOutline,
                        checkmarkColor = OnNeonGreen,
                    ),
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Li e aceito os Termos de Uso e a Política de Privacidade da Reobote Barbearia.",
                    style = ReoboteTheme.typography.bodySmall,
                    color = Platinum400,
                )
            }
        }

        // Bottom CTA Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ReoboteButton(
                text = "Concluir Cadastro",
                onClick = {
                    if (isFormValid) {
                        onCompleteProfile(fullName)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isFormValid,
                variant = ReoboteButtonVariant.Primary,
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
fun ProfileSetupScreenPreview() {
    ReoboteTheme {
        ProfileSetupScreen()
    }
}
