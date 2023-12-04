@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.advancecompose.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.advancecompose.domain.entity.Product
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
private fun HomeContent(state: HomeContract.State, onEventSent: (HomeContract.Event) -> Unit) {
    Scaffold(topBar = {
        ToolbarContent()
    }) { paddingValues ->

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
                    ContentBody(
                        products = state.products,
                        onEventSent = onEventSent
                    )
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
private fun ToolbarContent() {
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Home", modifier = Modifier.align(Alignment.Center))
        }
    })
}

@Composable
private fun FailUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = "Something is wrong")
    }
}

@Composable
private fun ContentBody(products: List<Product>, onEventSent: (HomeContract.Event) -> Unit) {
    LazyColumn(
        content = {
            items(count = products.size) {
                Column(modifier = Modifier.clickable {
                    onEventSent(HomeContract.Event.SelectProduct(products[it]))
                }) {
                    Text(text = products[it].title)
                    Text(text = "Price: ${products[it].title}")
                }
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    )
}

@Composable
@Preview(showBackground = true)
private fun ProductListPreview() {
    ContentBody(
        products = listOf(
            Product(
                id = "",
                title = "Rain Coat",
                price = 1200.0,
                description = "",
                imageUrl = ""
            )
        ), onEventSent = {})
}
