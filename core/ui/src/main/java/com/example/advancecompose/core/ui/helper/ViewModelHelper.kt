package com.example.advancecompose.core.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.advancecompose.core.ui.viewmodel.BaseViewModel


@Composable
fun <STATE> getViewState(viewModel: BaseViewModel<STATE, *, *, *>): State<STATE> {
    return viewModel.viewStateFlow.collectAsStateWithLifecycle(initialValue = viewModel.initialState)
}

@Composable
fun <STATE> BaseViewModel<STATE, *, *, *>.getComposableState(): State<STATE> {
    return this.viewStateFlow.collectAsStateWithLifecycle(initialValue = this.initialState)
}