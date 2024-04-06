package com.example.mark2.helper

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.advancecompose.core.ui.entity.DisplayedError
import com.example.advancecompose.core.ui.model.IAction
import com.example.advancecompose.core.ui.viewmodel.BaseViewModel

@Composable
fun BaseErrorHandler(
    viewModel: BaseViewModel<*, out IAction, *, *>,
    snackbarHostState: SnackbarHostState,
    content: (@Composable () -> Unit),
    context: Context
) {
    LaunchedEffect(Unit) {
        viewModel.errorFlow.collect {
            when (it) {
                is DisplayedError.SnackBarError -> {
                    snackbarHostState.showSnackbar(it)
                }

                is DisplayedError.ToastError -> {
                    context.showToast(it)
                }
            }
        }
    }
    content.invoke()
}