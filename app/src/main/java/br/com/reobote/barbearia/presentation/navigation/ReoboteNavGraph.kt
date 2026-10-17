package br.com.reobote.barbearia.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.reobote.barbearia.presentation.screens.AdminScreen
import br.com.reobote.barbearia.presentation.screens.AppointmentsScreen
import br.com.reobote.barbearia.presentation.screens.BookingScreen
import br.com.reobote.barbearia.presentation.screens.HomeScreen
import br.com.reobote.barbearia.presentation.screens.LoyaltyScreen
import br.com.reobote.barbearia.presentation.screens.ProfileScreen
import br.com.reobote.barbearia.presentation.screens.SplashScreen
import br.com.reobote.barbearia.presentation.screens.StoreScreen
import br.com.reobote.barbearia.presentation.screens.WelcomeScreen
import br.com.reobote.barbearia.presentation.screens.auth.AppleLoginScreen
import br.com.reobote.barbearia.presentation.screens.auth.GoogleLoginScreen
import br.com.reobote.barbearia.presentation.screens.auth.OtpVerificationScreen
import br.com.reobote.barbearia.presentation.screens.auth.PhoneInputScreen
import br.com.reobote.barbearia.presentation.screens.auth.ProfileSetupScreen

@Composable
fun ReoboteNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            ReoboteBottomBar(navController = navController)
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onNavigateToNext = {
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    },
                )
            }
            composable(Screen.Welcome.route) {
                WelcomeScreen(
                    onNavigateToPhoneInput = {
                        navController.navigate(Screen.PhoneInput.route)
                    },
                    onNavigateToGoogleLogin = {
                        navController.navigate(Screen.GoogleLogin.route)
                    },
                    onNavigateToAppleLogin = {
                        navController.navigate(Screen.AppleLogin.route)
                    },
                )
            }
            composable(Screen.PhoneInput.route) {
                PhoneInputScreen(
                    onNavigateToOtp = { _ ->
                        navController.navigate(Screen.OtpVerification.route)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(Screen.GoogleLogin.route) {
                GoogleLoginScreen(
                    onContinueWithGoogle = { _ ->
                        navController.navigate(Screen.ProfileSetup.route)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(Screen.AppleLogin.route) {
                AppleLoginScreen(
                    onContinueWithApple = {
                        navController.navigate(Screen.ProfileSetup.route)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(Screen.OtpVerification.route) {
                OtpVerificationScreen(
                    onVerifyClick = { _ ->
                        navController.navigate(Screen.ProfileSetup.route)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(Screen.ProfileSetup.route) {
                ProfileSetupScreen(
                    onCompleteProfile = { _ ->
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToBooking = {
                        navController.navigate(Screen.Booking.route)
                    },
                    onNavigateToProfile = {
                        navController.navigate(Screen.Profile.route)
                    },
                    onNavigateToNotifications = {
                        navController.navigate(Screen.Appointments.route)
                    },
                )
            }
            composable(Screen.Booking.route) {
                BookingScreen()
            }
            composable(Screen.Appointments.route) {
                AppointmentsScreen()
            }
            composable(Screen.Loyalty.route) {
                LoyaltyScreen()
            }
            composable(Screen.Store.route) {
                StoreScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Admin.route) {
                AdminScreen()
            }
        }
    }
}
