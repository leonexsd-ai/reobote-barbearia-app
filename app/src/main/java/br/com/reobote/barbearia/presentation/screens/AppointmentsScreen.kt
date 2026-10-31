package br.com.reobote.barbearia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import br.com.reobote.barbearia.presentation.components.AvaliacaoModal
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.components.ReoboteCard
import br.com.reobote.barbearia.presentation.theme.BorderOutlineVariant
import br.com.reobote.barbearia.presentation.theme.DarkGreenContainer
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.Platinum500
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark
import br.com.reobote.barbearia.presentation.theme.SurfaceDark
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

data class AppointmentItem(
    val id: String,
    val serviceName: String,
    val barberName: String,
    val dateFormatted: String,
    val timeFormatted: String,
    val priceFormatted: String,
    val isUpcoming: Boolean,
    val status: String,
    val userRating: Int? = null,
)

val mockUpcomingAppointments = listOf(
    AppointmentItem(
        id = "app_1",
        serviceName = "Corte Degradê Navalhado + Barba",
        barberName = "Carlos Barber",
        dateFormatted = "Amanhã, 16 de Maio",
        timeFormatted = "14:15",
        priceFormatted = "R$ 75,00",
        isUpcoming = true,
        status = "Confirmado",
    ),
    AppointmentItem(
        id = "app_2",
        serviceName = "Corte Social Clássico",
        barberName = "Matheus Silva",
        dateFormatted = "Quarta-feira, 21 de Maio",
        timeFormatted = "10:30",
        priceFormatted = "R$ 40,00",
        isUpcoming = true,
        status = "Confirmado",
    ),
)

val mockHistoryAppointments = listOf(
    AppointmentItem(
        id = "hist_1",
        serviceName = "Barba com Toalha Quente",
        barberName = "Carlos Barber",
        dateFormatted = "10 de Maio de 2025",
        timeFormatted = "15:00",
        priceFormatted = "R$ 35,00",
        isUpcoming = false,
        status = "Concluído",
        userRating = 5,
    ),
    AppointmentItem(
        id = "hist_2",
        serviceName = "Combo Cabelo + Barba",
        barberName = "André Souza",
        dateFormatted = "28 de Abril de 2025",
        timeFormatted = "18:00",
        priceFormatted = "R$ 75,00",
        isUpcoming = false,
        status = "Concluído",
        userRating = null, // Pending review!
    ),
    AppointmentItem(
        id = "hist_3",
        serviceName = "Acabamento / Pezinho",
        barberName = "Felipe Santos",
        dateFormatted = "14 de Abril de 2025",
        timeFormatted = "11:00",
        priceFormatted = "R$ 20,00",
        isUpcoming = false,
        status = "Cancelado",
    ),
)

@Composable
fun AppointmentsScreen(
    onNavigateToBooking: () -> Unit = {},
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var selectedAppointmentToRate by remember { mutableStateOf<AppointmentItem?>(null) }
    val tabs = listOf("Próximos", "Histórico")

    if (selectedAppointmentToRate != null) {
        AvaliacaoModal(
            barberName = selectedAppointmentToRate!!.barberName,
            serviceName = selectedAppointmentToRate!!.serviceName,
            onDismissRequest = { selectedAppointmentToRate = null },
            onSubmitRating = { _, _ ->
                selectedAppointmentToRate = null
            },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background),
    ) {
        // Header
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Text(
                text = "Meus Agendamentos",
                style = ReoboteTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Platinum100,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Gerencie seus horários marcados e histórico de cortes",
                style = ReoboteTheme.typography.bodyMedium,
                color = Platinum400,
            )
        }

        // Tab Bar
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = SurfaceDark,
            contentColor = NeonGreen,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = NeonGreen,
                        height = 3.dp,
                    )
                }
            },
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTabIndex == index
                Tab(
                    selected = isSelected,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            style = ReoboteTheme.typography.titleMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            ),
                            color = if (isSelected) NeonGreen else Platinum400,
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Content per Tab
        val appointments = if (selectedTabIndex == 0) mockUpcomingAppointments else mockHistoryAppointments

        if (appointments.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "📅", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Nenhum agendamento encontrado",
                        style = ReoboteTheme.typography.titleMedium,
                        color = Platinum100,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ReoboteButton(
                        text = "Agendar Novo Horário",
                        onClick = onNavigateToBooking,
                        variant = ReoboteButtonVariant.Primary,
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(appointments, key = { it.id }) { item ->
                    AppointmentCard(
                        item = item,
                        onReagendar = onNavigateToBooking,
                        onCancelar = { /* Cancel logic simulation */ },
                        onAvaliar = { selectedAppointmentToRate = item },
                    )
                }
            }
        }
    }
}

@Composable
private fun AppointmentCard(
    item: AppointmentItem,
    onReagendar: () -> Unit,
    onCancelar: () -> Unit,
    onAvaliar: () -> Unit,
) {
    ReoboteCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 16.dp,
    ) {
        // Top status badge & price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val statusBg = when (item.status) {
                "Confirmado" -> DarkGreenContainer
                "Concluído" -> SurfaceContainerDark
                else -> SurfaceVariantDark
            }
            val statusColor = when (item.status) {
                "Confirmado" -> NeonGreen
                "Concluído" -> Platinum100
                else -> Platinum500
            }

            Box(
                modifier = Modifier
                    .clip(ReoboteTheme.shapes.extraSmall)
                    .background(statusBg)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
            ) {
                Text(
                    text = item.status,
                    style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = statusColor,
                )
            }

            Text(
                text = item.priceFormatted,
                style = ReoboteTheme.typography.titleLarge,
                color = NeonGreen,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Service & Barber Info
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerDark)
                    .border(1.dp, NeonGreen, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = item.barberName.take(2).uppercase(),
                    style = ReoboteTheme.typography.titleMedium,
                    color = NeonGreen,
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.serviceName,
                    style = ReoboteTheme.typography.titleMedium,
                    color = Platinum100,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Com ${item.barberName}",
                    style = ReoboteTheme.typography.bodySmall,
                    color = Platinum400,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = BorderOutlineVariant)
        Spacer(modifier = Modifier.height(12.dp))

        // Date and Time
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🗓️", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${item.dateFormatted} às ${item.timeFormatted}",
                    style = ReoboteTheme.typography.bodyMedium,
                    color = Platinum100,
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Action Buttons according to Tab / Status
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            if (item.isUpcoming) {
                ReoboteButton(
                    text = "Cancelar",
                    onClick = onCancelar,
                    modifier = Modifier.weight(1f),
                    variant = ReoboteButtonVariant.Ghost,
                )

                ReoboteButton(
                    text = "Reagendar",
                    onClick = onReagendar,
                    modifier = Modifier.weight(1f),
                    variant = ReoboteButtonVariant.Secondary,
                )
            } else {
                if (item.status == "Concluído") {
                    ReoboteButton(
                        text = if (item.userRating != null) "Avaliado ★ ${item.userRating}" else "Avaliar",
                        onClick = onAvaliar,
                        modifier = Modifier.weight(1f),
                        variant = if (item.userRating != null) ReoboteButtonVariant.Secondary else ReoboteButtonVariant.Primary,
                    )
                }

                ReoboteButton(
                    text = "Repetir Corte",
                    onClick = onReagendar,
                    modifier = Modifier.weight(1f),
                    variant = ReoboteButtonVariant.Secondary,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun AppointmentsScreenPreview() {
    ReoboteTheme {
        AppointmentsScreen()
    }
}
