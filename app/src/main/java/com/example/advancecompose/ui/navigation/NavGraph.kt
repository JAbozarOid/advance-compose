package com.example.advancecompose.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.advancecompose.ui.home.HomeContract
import com.example.advancecompose.ui.home.HomeScreen

internal fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(route = Route.Root.route, startDestination = Route.Home.route) {

        // we need  to provide all the screens
        composable(route = Route.Home.route) {
            HomeScreen(onNavigationRequested = {
                when (it) {
                    is HomeContract.Effect.Navigation.ToProductDetail -> {
                        navController.navigate(Route.ProductDetail.createRoute(it.product))
                    }
                }
            })
        }
    }
}