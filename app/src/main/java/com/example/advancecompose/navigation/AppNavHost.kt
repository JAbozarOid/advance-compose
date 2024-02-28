package com.example.advancecompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.advancecompose.feature.login.navigation.loginGraph
import com.example.advancecompose.feature.login.navigation.loginRoute
import com.example.advancecompose.ui.AppState

@Composable
fun AppNavHost(
    appState: AppState,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute
) {

    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        loginGraph(
            onLoginClicked = {},
            onCreateAccountClicked = {},
            onBackClicked = { navController.popBackStack() },
            showSnack = { message -> onShowSnackBar.invoke(message, "retry".uppercase()) }
        )
    }

}