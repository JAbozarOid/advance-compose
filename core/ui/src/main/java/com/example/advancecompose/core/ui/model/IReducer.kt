package com.example.advancecompose.core.ui.model

fun interface IReducer<Mutation, State> {
    fun invoke(mutation: Mutation, currentState: State): State
}