package com.example.advancecompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
internal fun MainNavigation(navHostController: NavHostController) {

    NavHost(
        route = "main",
        navController = navHostController,
        startDestination = Route.Root.route,
    ) {
        mainNavGraph(navHostController)
    }
}