@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.advancecompose.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
internal fun HomeScreen(
    onNavigationRequested: (navigationEffect: HomeContract.Effect.Navigation) -> Unit
) {
    // instance of view model
    val vm: HomeViewModel = hiltViewModel()

    val state = vm.state.value

    // collect all of the effects
    LaunchedEffect("HomeScreen") {
        vm.effect.onEach { effect ->
            when (effect) {
                is HomeContract.Effect.Navigation.ToProductDetail -> {
                    onNavigationRequested(effect)
                }
            }
        }.collect()
    }

    // handle state on the ui
    HomeContent(state = state, onEventSent = {})
}

@Composable
private fun HomeContent(state: HomeContract.State, onEventSent: () -> Unit) {
    Scaffold(topBar = {}) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (state.dataState) {
                DataState.INITIAL -> {
                    // just a blank screen
                }

                DataState.SUCCESS -> {
                    ContentBody()
                }

                DataState.FAIL -> {
                    FailUI()
                }
            }
            if (state.isLoading) {
                Text(modifier = Modifier.align(Alignment.Center), text = "Loading....")
            }
        }
    }
}

@Composable
private fun FailUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = "Something is wrong")
    }
}

@Composable
private fun ContentBody() {
}
