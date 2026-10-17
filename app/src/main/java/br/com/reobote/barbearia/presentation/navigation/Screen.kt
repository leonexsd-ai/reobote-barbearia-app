package br.com.reobote.barbearia.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object PhoneInput : Screen("phone_input")
    object GoogleLogin : Screen("google_login")
    object AppleLogin : Screen("apple_login")
    object OtpVerification : Screen("otp_verification")
    object ProfileSetup : Screen("profile_setup")
    object Home : Screen("home")
    object Booking : Screen("booking")
    object Appointments : Screen("appointments")
    object Loyalty : Screen("loyalty")
    object Store : Screen("store")
    object Profile : Screen("profile")
    object Admin : Screen("admin")
}
