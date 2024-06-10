package com.example.advancecompose.feature.converter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.advancecompose.feature.converter.screen.ConverterView

const val converterRoute = "converter_route"

internal val defaultConverterNavOptions = navOptions {
    launchSingleTop = true
    restoreState = true
}

fun NavController.navigateToConverterView(navOptions: NavOptions? = defaultConverterNavOptions) {
    this.navigate(converterRoute, navOptions)
}

fun NavGraphBuilder.converterScreen(
) {
    composable(route = converterRoute) {
        ConverterView(
        )
    }
}

fun NavGraphBuilder.converterGraph(
) {
    converterScreen(
    )
}