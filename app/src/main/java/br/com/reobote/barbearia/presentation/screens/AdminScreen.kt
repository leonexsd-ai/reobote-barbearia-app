package br.com.reobote.barbearia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.reobote.barbearia.presentation.navigation.AdminNavGraph
import br.com.reobote.barbearia.presentation.screens.admin.AccessDeniedScreen
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.viewmodel.UserSessionViewModel

@Composable
fun AdminScreen(
    onNavigateToHome: () -> Unit = {},
    userSessionViewModel: UserSessionViewModel = hiltViewModel(),
) {
    val sessionState by userSessionViewModel.sessionState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background),
    ) {
        if (!sessionState.hasAdminAccess) {
            AccessDeniedScreen(
                currentRole = sessionState.role,
                onRoleSelect = { newRole ->
                    userSessionViewModel.setRole(newRole)
                },
                onHomeClick = onNavigateToHome,
            )
        } else {
            AdminNavGraph(
                currentRole = sessionState.role,
                onSelectRole = { newRole ->
                    userSessionViewModel.setRole(newRole)
                },
            )
        }
    }
}
