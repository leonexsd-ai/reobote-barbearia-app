package br.com.reobote.barbearia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import br.com.reobote.barbearia.presentation.components.ReoboteCard
import br.com.reobote.barbearia.presentation.theme.DarkGreenContainer
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

data class ProfileOption(
    val title: String,
    val subtitle: String,
    val icon: String,
    val onClick: () -> Unit,
)

@Composable
fun ProfileScreen(
    userName: String = "Lucas Ferreira",
    userPhone: String = "+55 (11) 98765-4321",
    onNavigateToAppointments: () -> Unit = {},
    onNavigateToLoyalty: () -> Unit = {},
    onNavigateToClub: () -> Unit = {},
    onNavigateToAdmin: () -> Unit = {},
    onLogout: () -> Unit = {},
) {
    val options = listOf(
        ProfileOption("Meus Agendamentos", "Histórico e próximos cortes", "📅", onNavigateToAppointments),
        ProfileOption("Cartão Fidelidade", "7 de 10 selos acumulados", "🎯", onNavigateToLoyalty),
        ProfileOption("Clube Reobote", "Assinaturas e benefícios VIP", "👑", onNavigateToClub),
        ProfileOption("Painel do Administrador", "Gestão da barbearia", "⚙️", onNavigateToAdmin),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background),
    ) {
        // Header
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Text(
                text = "Perfil do Cliente",
                style = ReoboteTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Platinum100,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Gerencie sua conta e suas vantagens",
                style = ReoboteTheme.typography.bodyMedium,
                color = Platinum400,
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // User Card
            item {
                ReoboteCard(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = 18.dp,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(SurfaceVariantDark)
                                .border(2.dp, NeonGreen, CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = userName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString(""),
                                style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = NeonGreen,
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = userName,
                                style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = Platinum100,
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = userPhone,
                                style = ReoboteTheme.typography.bodyMedium,
                                color = Platinum400,
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(DarkGreenContainer)
                                    .padding(horizontal = 10.dp, vertical = 3.dp),
                            ) {
                                Text(
                                    text = "Cliente VIP",
                                    style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    color = NeonGreen,
                                )
                            }
                        }
                    }
                }
            }

            // Options List
            items(options.size) { index ->
                val option = options[index]
                ReoboteCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { option.onClick() },
                    contentPadding = 16.dp,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = option.icon, fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = option.title,
                                    style = ReoboteTheme.typography.titleMedium,
                                    color = Platinum100,
                                )
                                Text(
                                    text = option.subtitle,
                                    style = ReoboteTheme.typography.bodySmall,
                                    color = Platinum400,
                                )
                            }
                        }

                        Text(text = "→", fontSize = 20.sp, color = NeonGreen)
                    }
                }
            }

            // Logout
            item {
                ReoboteCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLogout() },
                    contentPadding = 16.dp,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🚪", fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(14.dp))
                        Text(
                            text = "Sair da Conta",
                            style = ReoboteTheme.typography.titleMedium,
                            color = ReoboteTheme.colors.error,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun ProfileScreenPreview() {
    ReoboteTheme {
        ProfileScreen()
    }
}
