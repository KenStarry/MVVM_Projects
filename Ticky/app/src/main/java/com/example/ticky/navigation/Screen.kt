package com.example.ticky.navigation

sealed class Screen(val route: String) {

    object Groups : Screen("groups_screen")
}
