package com.example.advancecompose.feature.interview.bestPractice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

/**
 * 1- inject dispatchers
 *      - because of testing easier as you can replace those dispatchers in unit and instrumented test with a test dispatcher
 * 2- suspend functions should be safe to call from the main thread
 * 3- view model should create coroutines
 * 4- don't expose mutable types
 * 5- repository class should have suspend function and Flows
 * 6- avoid GlobalScope
 *      - make testing very hard, we can not handle it's state
 *      - it is like singleton so complex for testing
 * 7- make coroutine cancelable
 */

class NewsRepository(
    // 1- inject dispatchers
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    // 2- loadNews suspend fun needs to be run on dispatchers io
    suspend fun loadNews() = withContext(Dispatchers.IO) {
        /* .. */
    }


    // 4- don't expose mutable types
    private val _uiState = MutableStateFlow(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState> = _uiState
}

sealed class NewsUiState {
    data object Loading : NewsUiState()
}

// 5- repository class should have suspend function and Flows
class ExampleRepository {
    suspend fun makeNetworkRequest() {

    }

    fun getExamples(): Flow<Example> {
        return flow {
            emit(Example(id = 0))
        }
    }
}

data class Example(
    private val id: Int
)


// 7- make coroutine cancelable
val scope = CoroutineScope(Dispatchers.IO).launch {
    val files: List<File> = listOf()
    for (file in files) {
        ensureActive() // check for cancellation
        readFile(file)
    }
}

fun readFile(file: File) {
    TODO("Not yet implemented")
}
