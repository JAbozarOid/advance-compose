package com.example.advancecompose.ui.home

import com.example.advancecompose.domain.entity.Product
import com.example.core.ui.ViewEvent
import com.example.core.ui.ViewSideEffect
import com.example.core.ui.ViewState

internal class HomeContract {

    /**
     * the below event will be called when user select the specific product
     */
    sealed class Event : ViewEvent {
        data class SelectProduct(val product: Product) : Event()
    }

    /**
     * state : represent the current state of home screen
     */
    data class State(
        val isLoading: Boolean = false,
        val dataState: DataState = DataState.INITIAL,
        val products: List<Product> = emptyList()
    ) : ViewState

    /**
     * effect will be a sealed class
     * handle navigation
     * in this case navigate to product detail
     */
    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToProductDetail(val product: Product) : Navigation()
        }
    }

}

internal enum class DataState {
    INITIAL,
    SUCCESS,
    FAIL
}