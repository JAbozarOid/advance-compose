package com.example.advancecompose.feature.interview.designpattern

/**
 * <<< MVVM : Model - View - Viewmodel >>>
 * A- Architecture component
 * 1- model : represent the data layer, data source, database and network
 * 2- view : represent the ui layer. it observe the viewmodel and display the data
 * 3- viewmodel : act as bridge between view and the model

 * B- Data Flow
 * - data flow from model to viewmodel and then from viewmodel to view
 * - viewmodel expose data through livedata and stateflow which view observe and react to
 *
 * <<< MVI : Model - View - Intent >>>
 * A- Architecture component
 * 1- Model: Represents the state of the application.
 * 2- View: Represents the UI and emits user intents.
 * 3- Intent: Represents the user’s actions.
 * 4- Reducer: Takes the current state and an intent and returns a new state.
 * 5- State: Represents the immutable state of the View at any point in time.
 *
 * B- Data Flow
 * - The View emits intents based on user actions.
 * - Intents are processed and passed to a reducer.
 * - The reducer updates the state, which the View observes and renders.
 *
 * <<< key difference >>>
 * - Data Flow: MVVM uses a bidirectional data flow between the View and the ViewModel,
 * while MVI enforces a unidirectional data flow, which makes state management more predictable.
 * - State Management: In MVVM, the ViewModel holds the state and exposes it to the View.
 * In MVI, the state is immutable, and the View renders based on this immutable state.
 */