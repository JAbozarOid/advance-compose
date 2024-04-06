package com.example.mark2.helper

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.advancecompose.core.ui.viewmodel.BaseViewModel

/**
 * this composable must be called
 * in the chain normal application flow
 * because it needs "LocalSnackBarState.current"
 * @see com.example.mark2.helper.LocalSnackBarState
 * @throws IllegalStateException when the snackbarHostState
 * not provided in call site
 */
@Composable
fun BaseScreen(
    viewModel: BaseViewModel<*, *, *, *>,
    snackbarHostState: SnackbarHostState = LocalSnackBarState.current,
    context: Context = LocalContext.current,
    feature: (@Composable () -> Unit)
) {
    BaseErrorHandler(
        viewModel = viewModel,
        snackbarHostState = snackbarHostState,
        context = context,
        content = feature
    )
}