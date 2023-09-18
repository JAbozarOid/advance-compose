package com.example.advancecompose.ui.home

import com.example.advancecompose.domain.usecase.ProductUseCase
import com.example.core.ui.BaseViewModel
import com.example.core.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel<HomeContract.Event, HomeContract.Effect, HomeContract.State>(dispatcher = dispatcherProvider.main()) {
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