package com.example.advancecompose.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(route = Route.Root.route, startDestination = Route.Home.route) {

        // we need  to provide all the screens
        composable(route = Route.Home.route) {
            // TODO home screen
        }
    }
}