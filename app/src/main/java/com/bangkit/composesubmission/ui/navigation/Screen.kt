package com.bangkit.composesubmission.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object About : Screen("about_page")
    object DetailMotor : Screen("home/{playerId}") {
        fun createRoute(motorId: Long) = "home/$motorId"
    }
}
