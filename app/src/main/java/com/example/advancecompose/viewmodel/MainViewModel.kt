package com.example.advancecompose.viewmodel

import com.example.advancecompose.core.ui.di.annotation.ForViewModel
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.core.ui.model.IReducer
import com.example.advancecompose.core.ui.viewmodel.BaseViewModel
import com.example.advancecompose.model.MainAction
import com.example.advancecompose.model.MainEvent
import com.example.advancecompose.model.MainMutation
import com.example.advancecompose.model.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltViewModel
internal class MainViewModel @Inject constructor(
    @ForViewModel(MainViewModel::class)
    actionProcessors: Map<KClass<out MainAction>, @JvmSuppressWildcards IActionProcessor<*, MainMutation, MainEvent>>,
    @ForViewModel(MainViewModel::class)
    reducers: Map<KClass<out MainMutation>, @JvmSuppressWildcards IReducer<*, MainViewState>>,
) : BaseViewModel<MainViewState, MainAction, MainMutation, MainEvent>(
    actionProcessors = actionProcessors,
    reducers = reducers,
    initialState = MainViewState()
)