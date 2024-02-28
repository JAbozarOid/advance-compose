package com.example.advancecompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.example.advancecompose.navigation.AppNavHost
import com.example.advancecompose.ui.AppState
import com.example.advancecompose.ui.rememberMarkAppState

@OptIn(
    ExperimentalComposeUiApi::class,
)
@Composable
fun EvaApp(
    windowSizeClass: WindowSizeClass,
    appState: AppState = rememberMarkAppState(windowSizeClass = windowSizeClass)
) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {}
    ) { padding ->

        AppNavHost(
            modifier = Modifier.padding(padding),
            appState = appState,
            onShowSnackBar = { message, action ->
                snackBarHostState.showSnackbar(
                    message = message,
                    actionLabel = action,
                    duration = SnackbarDuration.Short
                ) == SnackbarResult.ActionPerformed
            }
        )


    }


}