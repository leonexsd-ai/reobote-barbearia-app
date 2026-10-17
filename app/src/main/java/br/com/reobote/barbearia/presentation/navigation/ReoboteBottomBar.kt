package br.com.reobote.barbearia.presentation.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceDark

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val iconSymbol: String,
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, "Início", "🏠"),
    BottomNavItem(Screen.Booking, "Agendar", "✂️"),
    BottomNavItem(Screen.Appointments, "Agenda", "📅"),
    BottomNavItem(Screen.Store, "Loja", "🛍️"),
    BottomNavItem(Screen.Profile, "Perfil", "👤"),
)

@Composable
fun ReoboteBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val showBottomBar = bottomNavItems.any { it.screen.route == currentRoute }

    if (showBottomBar) {
        NavigationBar(
            modifier = modifier,
            containerColor = SurfaceDark,
            contentColor = Platinum400,
        ) {
            bottomNavItems.forEach { item ->
                val selected = currentRoute == item.screen.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Text(
                            text = item.iconSymbol,
                            style = ReoboteTheme.typography.titleMedium,
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = ReoboteTheme.typography.labelSmall,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = NeonGreen,
                        selectedTextColor = NeonGreen,
                        unselectedIconColor = Platinum400,
                        unselectedTextColor = Platinum400,
                        indicatorColor = SurfaceDark,
                    ),
                )
            }
        }
    }
}
