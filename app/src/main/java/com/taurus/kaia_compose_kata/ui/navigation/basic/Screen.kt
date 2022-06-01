package com.taurus.kaia_compose_kata.ui.navigation.basic

/**
 * Define the screens here and their expected paths
 */
sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Detail : Screen(route = "detail_screen")
}
