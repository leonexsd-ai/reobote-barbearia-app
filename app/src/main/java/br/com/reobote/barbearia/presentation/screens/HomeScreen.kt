package br.com.reobote.barbearia.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.Crossfade
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reobote.barbearia.presentation.components.BarberAvatar
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.components.ReoboteCard
import br.com.reobote.barbearia.presentation.components.ServiceCard
import br.com.reobote.barbearia.presentation.components.SkeletonBox
import br.com.reobote.barbearia.presentation.components.SkeletonCircle
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.DarkGreenContainer
import br.com.reobote.barbearia.presentation.theme.EmeraldGreen
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.OnNeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

data class FakeService(
    val name: String,
    val price: String,
    val duration: String,
)

data class FakeBarber(
    val name: String,
    val rating: Double,
)

data class FakeBanner(
    val title: String,
    val description: String,
    val tag: String,
)

val fakeServices = listOf(
    FakeService("Corte Degradê Navalhado", "R$ 45,00", "45 min"),
    FakeService("Barba com Toalha Quente", "R$ 35,00", "30 min"),
    FakeService("Combo Cabelo + Barba", "R$ 75,00", "60 min"),
    FakeService("Sobrancelha na Navalha", "R$ 20,00", "15 min"),
)

val fakeBarbers = listOf(
    FakeBarber("Carlos Barber", 4.9),
    FakeBarber("Matheus Silva", 4.8),
    FakeBarber("André Souza", 5.0),
    FakeBarber("Felipe Santos", 4.9),
)

val fakeBanners = listOf(
    FakeBanner(
        title = "Combo Terça de Leão",
        description = "Cabelo + Barba com 20% OFF todas as terças-feiras.",
        tag = "PROMOÇÃO",
    ),
    FakeBanner(
        title = "Cartão Fidelidade",
        description = "Complete 10 selos e ganhe 1 corte grátis!",
        tag = "EXCLUSIVO",
    ),
    FakeBanner(
        title = "Horário Estendido",
        description = "Atendimento até às 21h de quinta a sábado.",
        tag = "NOVIDADE",
    ),
)

@Composable
fun HomeScreen(
    userName: String = "Lucas Ferreira",
    onNavigateToBooking: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    // Simulate skeleton loading state for 1.5 seconds
    LaunchedEffect(Unit) {
        delay(1.5.seconds)
        isLoading = false
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background)
            .verticalScroll(scrollState),
    ) {
        // 1. Header Section
        HomeHeaderSection(
            userName = userName,
            isLoading = isLoading,
            onAvatarClick = onNavigateToProfile,
            onNotificationClick = onNavigateToNotifications,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Crossfade(targetState = isLoading, label = "HomeContentCrossfade") { loading ->
            if (loading) {
                HomeSkeletonContent()
            } else {
                Column {
                    // 2. Quick Action Card
                    QuickActionSection(
                        onBookingClick = onNavigateToBooking,
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // 3. Banner Carousel
                    BannersSection()

                    Spacer(modifier = Modifier.height(24.dp))

                    // 4. Location Shortcut Card
                    LocationShortcutSection(
                        onOpenMaps = {
                            val uri = Uri.parse("geo:0,0?q=Av.+Principal,+1000+-+Centro,+S%C3%A3o+Paulo+-+SP")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            try {
                                context.startActivity(intent)
                            } catch (_: Exception) {
                                // Fallback
                            }
                        },
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // 5. Services Grid Section
                    ServicesSection(
                        services = fakeServices,
                        onServiceClick = { onNavigateToBooking() },
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // 6. Barbers Section
                    BarbersSection(
                        barbers = fakeBarbers,
                        onBarberClick = { onNavigateToBooking() },
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
private fun HomeHeaderSection(
    userName: String,
    isLoading: Boolean,
    onAvatarClick: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isLoading) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                SkeletonCircle(size = 48.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    SkeletonBox(modifier = Modifier.size(100.dp, 14.dp))
                    Spacer(modifier = Modifier.height(6.dp))
                    SkeletonBox(modifier = Modifier.size(140.dp, 20.dp))
                }
            }
            SkeletonCircle(size = 42.dp)
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onAvatarClick() },
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(SurfaceVariantDark)
                        .border(1.5.dp, NeonGreen, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = userName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString(""),
                        style = ReoboteTheme.typography.titleMedium,
                        color = NeonGreen,
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f, fill = false)) {
                    Text(
                        text = "Bem-vindo de volta,",
                        style = ReoboteTheme.typography.labelSmall,
                        color = Platinum400,
                    )
                    Text(
                        text = "Olá, $userName",
                        style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Platinum100,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            // Notification Icon Button
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(SurfaceVariantDark)
                    .border(1.dp, BorderOutline, CircleShape)
                    .clickable { onNotificationClick() },
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "🔔", fontSize = 18.sp)
                // Red badge indicator
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(2.dp)
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(NeonGreen),
                )
            }
        }
    }
}

@Composable
private fun QuickActionSection(
    onBookingClick: () -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(ReoboteTheme.shapes.large)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            NeonGreen,
                            EmeraldGreen,
                        ),
                    ),
                )
                .padding(20.dp),
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "ÚLTIMO CORTE REALIZADO",
                            style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = OnNeonGreen.copy(alpha = 0.7f),
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Degradê + Barba",
                            style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                            color = OnNeonGreen,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = "Com Carlos Barber • R$ 75,00",
                            style = ReoboteTheme.typography.bodySmall,
                            color = OnNeonGreen.copy(alpha = 0.85f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = "✂️", fontSize = 36.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    ReoboteButton(
                        text = "Agendar Agora",
                        onClick = onBookingClick,
                        modifier = Modifier.weight(1f),
                        variant = ReoboteButtonVariant.Secondary,
                    )

                    ReoboteButton(
                        text = "Repetir Corte",
                        onClick = onBookingClick,
                        modifier = Modifier.weight(1f),
                        variant = ReoboteButtonVariant.Primary,
                    )
                }
            }
        }
    }
}

@Composable
private fun BannersSection() {
    Column {
        Text(
            text = "Destaques & Promoções",
            style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Platinum100,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            items(fakeBanners) { banner ->
                ReoboteCard(
                    modifier = Modifier.width(260.dp),
                    contentPadding = 16.dp,
                ) {
                    Box(
                        modifier = Modifier
                            .clip(ReoboteTheme.shapes.extraSmall)
                            .background(DarkGreenContainer)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = banner.tag,
                            style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = NeonGreen,
                            maxLines = 1,
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = banner.title,
                        style = ReoboteTheme.typography.titleMedium,
                        color = Platinum100,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = banner.description,
                        style = ReoboteTheme.typography.bodySmall,
                        color = Platinum400,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Composable
private fun LocationShortcutSection(
    onOpenMaps: () -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "Nossa Unidade",
            style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Platinum100,
        )

        Spacer(modifier = Modifier.height(12.dp))

        ReoboteCard(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = 16.dp,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "📍", fontSize = 28.sp)

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Reobote Barbearia - Matriz",
                        style = ReoboteTheme.typography.titleMedium,
                        color = Platinum100,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "Av. Principal, 1000 - Centro, São Paulo - SP",
                        style = ReoboteTheme.typography.bodySmall,
                        color = Platinum400,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            ReoboteButton(
                text = "Abrir no Google Maps",
                onClick = onOpenMaps,
                modifier = Modifier.fillMaxWidth(),
                variant = ReoboteButtonVariant.Secondary,
                leadingIcon = { Text(text = "🗺️", fontSize = 16.sp) },
            )
        }
    }
}

@Composable
private fun ServicesSection(
    services: List<FakeService>,
    onServiceClick: () -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Serviços Populares",
                style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Platinum100,
            )

            Text(
                text = "Ver todos",
                style = ReoboteTheme.typography.labelMedium,
                color = NeonGreen,
                modifier = Modifier.clickable { onServiceClick() },
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            services.forEach { service ->
                ServiceCard(
                    name = service.name,
                    price = service.price,
                    duration = service.duration,
                    modifier = Modifier.fillMaxWidth(),
                    onSelectToggle = { onServiceClick() },
                )
            }
        }
    }
}

@Composable
private fun BarbersSection(
    barbers: List<FakeBarber>,
    onBarberClick: () -> Unit,
) {
    Column {
        Text(
            text = "Nossos Barbeiros",
            style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Platinum100,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(barbers) { barber ->
                BarberAvatar(
                    name = barber.name,
                    rating = barber.rating,
                    onClick = onBarberClick,
                )
            }
        }
    }
}

@Composable
private fun HomeSkeletonContent() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SkeletonBox(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp),
            )
            SkeletonBox(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun HomeScreenPreview() {
    ReoboteTheme {
        HomeScreen()
    }
}
