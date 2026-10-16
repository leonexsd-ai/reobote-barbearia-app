package br.com.reobote.barbearia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme

@Composable
fun BookingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ReoboteTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Booking Screen",
            style = ReoboteTheme.typography.headlineLarge,
            color = ReoboteTheme.colors.onBackground,
        )
    }
}
