package com.example.advancecompose.model

import com.example.advancecompose.core.ui.model.IAction
import com.example.advancecompose.core.ui.model.IEvent
import com.example.advancecompose.core.ui.model.IMutation


internal sealed interface MainAction : IAction {
    data object ViewCreated : MainAction
}

internal sealed interface MainMutation : IMutation {
    data object StartUpDataFetched : MainMutation
}

internal sealed interface MainEvent : IEvent