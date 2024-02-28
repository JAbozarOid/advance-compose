package com.example.advancecompose.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.advancecompose.feature.login.screen.LoginView

const val loginRoute = "login_route"

internal val defaultLoginNavOptions = navOptions {
    launchSingleTop = true
    restoreState = true
}

fun NavController.navigateToLoginView(navOptions: NavOptions? = defaultLoginNavOptions) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onCreateAccountClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    onBackClicked: () -> Unit,
    showSnack: suspend (message : String) -> Unit
    ) {
    composable(route = loginRoute) {
        LoginView(
            onCreateAccountClicked = onCreateAccountClicked,
            onLoginClicked = onLoginClicked,
            onBackClicked = onBackClicked,
            showSnack = showSnack
        )
    }
}

fun NavGraphBuilder.loginGraph(
    onLoginClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit,
    onBackClicked: () -> Unit,
    showSnack: suspend (message : String) -> Unit
) {
    loginScreen(
        onCreateAccountClicked = onCreateAccountClicked,
        onLoginClicked = onLoginClicked,
        onBackClicked = onBackClicked,
        showSnack = showSnack
    )
}