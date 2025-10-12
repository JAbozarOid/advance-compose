package com.example.advancecompose.core.ui.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    val main : CoroutineDispatcher
    val io: CoroutineDispatcher
    val default : CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}