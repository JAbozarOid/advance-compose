package com.example.advancecompose.feature.composenavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.advancecompose.feature.composenavigation.screen.FirstScreen
import com.example.advancecompose.feature.composenavigation.screen.SecondScreen

@Composable
fun DemoAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "first_screen") {
        composable(route = "first_screen") {
            FirstScreen(
                //navController = navController
                onNavigate = {
                    navController.navigate("second_screen/$it")
                }
            )
        }

        // passing argument to second argument when navigate
        composable(
            route = "second_screen/{inputName}",
            arguments = listOf(
                navArgument("inputName") {
                    type = NavType.StringType
                }
            )
        ) {
            SecondScreen(textToDisplay = it.arguments?.getString("inputName").toString())
        }
    }
}