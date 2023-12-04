package com.example.advancecompose.ui.home

import androidx.lifecycle.viewModelScope
import com.example.advancecompose.domain.usecase.ProductUseCase
import com.example.core.domain.Result
import com.example.core.ui.BaseViewModel
import com.example.core.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel<HomeContract.Event, HomeContract.Effect, HomeContract.State>(dispatcher = dispatcherProvider.main()) {

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch(dispatcherProvider.main()) {
            setState { copy(isLoading = true) }

            when (val result = productUseCase(Unit)) {
                is Result.Error -> {
                    setState { copy(isLoading = false, dataState = DataState.FAIL) }
                }

                is Result.Success -> {
                    setState {
                        copy(
                            isLoading = false,
                            dataState = DataState.SUCCESS,
                            products = result.data
                        )
                    }
                }

                else -> {}
            }
        }
    }

    override fun setInitialState() = HomeContract.State()
    override fun handleEvent(event: HomeContract.Event) {
        /**
         * handle all the events will be happen on the screen like select product
         */

        when (event) {
            is HomeContract.Event.SelectProduct -> {
                // this setEffect will be catch in home screen in Launch effect block
                setEffect {
                    HomeContract.Effect.Navigation.ToProductDetail(
                        product = event.product
                    )
                }
            }

            else -> {}
        }
    }
}
