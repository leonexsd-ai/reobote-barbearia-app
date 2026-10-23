package br.com.reobote.barbearia.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.reobote.barbearia.presentation.screens.booking.BarberSelectionScreen
import br.com.reobote.barbearia.presentation.screens.booking.BookingStep
import br.com.reobote.barbearia.presentation.screens.booking.BookingViewModel
import br.com.reobote.barbearia.presentation.screens.booking.CheckoutScreen
import br.com.reobote.barbearia.presentation.screens.booking.DateTimeSelectionScreen
import br.com.reobote.barbearia.presentation.screens.booking.ServiceSelectionScreen
import br.com.reobote.barbearia.presentation.screens.booking.SuccessScreen
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark
import br.com.reobote.barbearia.presentation.theme.SurfaceDark
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

@Composable
fun BookingScreen(
    onNavigateToHome: () -> Unit = {},
    onBackClick: () -> Unit = {},
    viewModel: BookingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ReoboteTheme.colors.background,
        topBar = {
            if (uiState.currentStep != BookingStep.Success) {
                BookingTopHeader(
                    currentStep = uiState.currentStep,
                    onBackClick = {
                        if (uiState.currentStep == BookingStep.Services) {
                            onBackClick()
                        } else {
                            viewModel.goToPreviousStep()
                        }
                    },
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            AnimatedContent(
                targetState = uiState.currentStep,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "BookingStepTransition",
            ) { step ->
                when (step) {
                    BookingStep.Services -> {
                        ServiceSelectionScreen(
                            services = viewModel.mockServices,
                            selectedCategory = uiState.selectedCategory,
                            selectedServices = uiState.selectedServices,
                            totalPriceFormatted = uiState.totalPriceFormatted,
                            totalDurationFormatted = uiState.totalDurationFormatted,
                            onSelectCategory = { viewModel.selectCategory(it) },
                            onToggleService = { viewModel.toggleService(it) },
                            onNextClick = { viewModel.goToNextStep() },
                        )
                    }

                    BookingStep.Barber -> {
                        BarberSelectionScreen(
                            barbers = viewModel.mockBarbers,
                            selectedBarber = uiState.selectedBarber,
                            onSelectBarber = { viewModel.selectBarber(it) },
                            onNextClick = { viewModel.goToNextStep() },
                        )
                    }

                    BookingStep.DateTime -> {
                        DateTimeSelectionScreen(
                            dates = viewModel.mockDates,
                            selectedDate = uiState.selectedDate,
                            timeSlots = viewModel.mockTimeSlots,
                            selectedTimeSlot = uiState.selectedTimeSlot,
                            onSelectDate = { viewModel.selectDate(it) },
                            onSelectTimeSlot = { viewModel.selectTimeSlot(it) },
                            onNextClick = { viewModel.goToNextStep() },
                        )
                    }

                    BookingStep.Checkout -> {
                        CheckoutScreen(
                            selectedServices = uiState.selectedServices,
                            selectedBarber = uiState.selectedBarber,
                            selectedDate = uiState.selectedDate,
                            selectedTimeSlot = uiState.selectedTimeSlot,
                            selectedPaymentMethod = uiState.selectedPaymentMethod,
                            observationText = uiState.observationText,
                            subtotalFormatted = uiState.subtotalFormatted,
                            totalPriceFormatted = uiState.totalPriceFormatted,
                            totalDurationFormatted = uiState.totalDurationFormatted,
                            isLoading = uiState.isLoading,
                            onSelectPaymentMethod = { viewModel.selectPaymentMethod(it) },
                            onObservationChange = { viewModel.updateObservation(it) },
                            onConfirmClick = { viewModel.confirmBooking() },
                        )
                    }

                    BookingStep.Success -> {
                        SuccessScreen(
                            confirmationCode = uiState.bookingConfirmationCode,
                            selectedServices = uiState.selectedServices,
                            selectedBarber = uiState.selectedBarber,
                            selectedDate = uiState.selectedDate,
                            selectedTimeSlot = uiState.selectedTimeSlot,
                            paymentMethod = uiState.selectedPaymentMethod,
                            totalPriceFormatted = uiState.totalPriceFormatted,
                            onHomeClick = {
                                viewModel.resetBooking()
                                onNavigateToHome()
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BookingTopHeader(
    currentStep: BookingStep,
    onBackClick: () -> Unit,
) {
    val totalSteps = 4
    val progress = currentStep.stepNumber.toFloat() / totalSteps.toFloat()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceDark)
            .padding(top = 12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onBackClick() }
                    .padding(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(SurfaceVariantDark),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "←",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Platinum100,
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "PASSO ${currentStep.stepNumber} DE $totalSteps",
                        style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = NeonGreen,
                    )
                    Text(
                        text = currentStep.title,
                        style = ReoboteTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Platinum100,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(SurfaceVariantDark)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
            ) {
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = ReoboteTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = NeonGreen,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp),
            color = NeonGreen,
            trackColor = SurfaceContainerDark,
        )
    }
}
