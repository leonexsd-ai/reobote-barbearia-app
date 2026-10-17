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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.time.Duration.Companion.seconds

@Composable
fun OtpVerificationScreen(
    phoneNumber: String = "+55 (11) 98765-4321",
    onVerifyClick: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    var otpCode by remember { mutableStateOf("") }
    var timerSeconds by remember { mutableIntStateOf(45) }

    LaunchedEffect(timerSeconds) {
        if (timerSeconds > 0) {
            delay(1.seconds)
            timerSeconds -= 1
        }
    }

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
                text = "Digite o código",
                style = ReoboteTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = Platinum100,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enviamos um SMS com 6 dígitos para $phoneNumber",
                style = ReoboteTheme.typography.bodyLarge,
                color = Platinum400,
            )

            Spacer(modifier = Modifier.height(36.dp))

            // 6-Digit OTP Box Grid
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                // Hidden BasicTextField capturing keyboard input
                BasicTextField(
                    value = otpCode,
                    onValueChange = { input ->
                        if ((input.length <= 6) && input.all { it.isDigit() }) {
                            otpCode = input
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            for (i in 0 until 6) {
                                val digit = otpCode.getOrNull(i)?.toString() ?: ""
                                val isFocused = otpCode.length == i || (i == 5 && otpCode.length == 6)

                                val boxBorder = if (isFocused) {
                                    Modifier.border(2.dp, NeonGreen, ReoboteTheme.shapes.medium)
                                } else if (digit.isNotEmpty()) {
                                    Modifier.border(1.dp, NeonGreen.copy(alpha = 0.5f), ReoboteTheme.shapes.medium)
                                } else {
                                    Modifier.border(1.dp, BorderOutline, ReoboteTheme.shapes.medium)
                                }

                                Box(
                                    modifier = Modifier
                                        .size(48.dp, 56.dp)
                                        .then(boxBorder)
                                        .clip(ReoboteTheme.shapes.medium)
                                        .background(SurfaceVariantDark),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = digit,
                                        style = ReoboteTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                        ),
                                        color = if (isFocused) NeonGreen else Platinum100,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                        }
                    },
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Resend Code Timer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (timerSeconds > 0) {
                    Text(
                        text = String.format(Locale.US, "Reenviar código em 00:%02d", timerSeconds),
                        style = ReoboteTheme.typography.bodyMedium,
                        color = Platinum400,
                    )
                } else {
                    Text(
                        text = "Reenviar código agora",
                        style = ReoboteTheme.typography.titleSmall,
                        color = NeonGreen,
                        modifier = Modifier.clickable {
                            timerSeconds = 45
                        },
                    )
                }
            }
        }

        // Bottom CTA Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ReoboteButton(
                text = "Verificar e Continuar",
                onClick = {
                    if (otpCode.length == 6) {
                        onVerifyClick(otpCode)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = otpCode.length == 6,
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
fun OtpVerificationScreenPreview() {
    ReoboteTheme {
        OtpVerificationScreen()
    }
}
