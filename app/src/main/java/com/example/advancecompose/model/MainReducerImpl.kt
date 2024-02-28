package com.example.advancecompose.model

import com.example.advancecompose.core.ui.model.IReducer
import javax.inject.Inject

internal class MainReducerImpl @Inject constructor() : IReducer<MainMutation, MainViewState> {
    override fun invoke(mutation: MainMutation, currentState: MainViewState): MainViewState {
        return when (mutation) {
            is MainMutation.StartUpDataFetched -> {
                currentState.mutateToLoadedState()
            }
        }
    }
}

private fun MainViewState.mutateToLoadedState() = copy(loading = false)