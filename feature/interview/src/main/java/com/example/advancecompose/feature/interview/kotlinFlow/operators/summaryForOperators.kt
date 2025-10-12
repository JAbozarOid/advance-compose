package com.example.advancecompose.feature.interview.kotlinFlow.operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * see the picture on res/drawable/flow_operators.jpg
 * debounce : great for handling search inputs
 * sample : useful for limiting updates (e.g. UI refresh rate)
 * timeout : cancel if no value is emitted in time
 * flatMapLatest – cancels the previous search request if either query or filter changes.
 * combine – merges search query and filter changes into a single stream.
 * merge : emits values from multiple flows as soon as each flow emits, interleaving results.
 * zip – pairs emissions by index order:
 *      - 1st item from Flow A pairs with 1st item from Flow B, 2nd item from Flow A pairs with 2nd item from Flow B, and so on.
 */


fun main() = runBlocking {
    //testDebounce()
    //testCombine()
    //testMerge()
    zipTest()
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
suspend fun testDebounce() {

    val searchQuery = MutableStateFlow("")

    // Simulated search flow
    val job = searchQuery
        .debounce(300) // Wait for the user to stop typing
        .filter { it.isNotBlank() }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            fakeSearchApi(query) // Cancel previous search if new query comes
        }
        .onEach { results ->
            println("Search results: $results")
        }
        .launchIn(CoroutineScope(Dispatchers.Default))

    // Simulate user typing quickly
    val simulatedInputs = listOf("a", "ab", "abo", "abozar")
    for (input in simulatedInputs) {
        delay(150) // User typing every 150ms
        println("User typed: $input")
        searchQuery.value = input
    }

    delay(2000) // Wait for final search result
    job.cancel()
}

// Fake API that returns Flow
fun fakeSearchApi(query: String): Flow<List<String>> = flow {
    println("Searching for \"$query\"...")
    delay(500) // Simulate network delay
    emit(listOf("$query result 1", "$query result 2", "$query result 3"))
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
suspend fun testCombine() {
    val searchQuery = MutableStateFlow("")
    val filterType = MutableStateFlow("ALL") // e.g. ALL, DEBIT, CREDIT

    // Combine search input + filter changes
    val job = combine(
        searchQuery.debounce(300) // Debounce user input
            .filter { it.isNotBlank() }
            .distinctUntilChanged(),
        filterType
    ) { query, filter ->
        query to filter // Pair of (searchQuery, filterType)
    }
        .flatMapLatest { (query, filter) ->
            // Cancel if API call takes more than 1 second
            fakeTransactionSearch(query, filter)
                .timeout(100.milliseconds)
                .catch { e ->
                    if (e is TimeoutCancellationException) {
                        emit(listOf("Request timed out for $query [$filter]"))
                    } else throw e
                }
        }
        .onEach { results ->
            println("Filtered transactions: $results")
        }
        .launchIn(CoroutineScope(Dispatchers.Default))

    // Simulate user typing quickly
    val simulatedInputs = listOf("a", "ab", "abo", "abozar")
    for (input in simulatedInputs) {
        delay(150) // User typing every 150ms
        println("User typed: $input")
        searchQuery.value = input
    }

    // Change filter after typing
    delay(500)
    println("User selected filter: CREDIT")
    filterType.value = "CREDIT"

    delay(2000) // Wait for the last search
    job.cancel()
}

// Simulated search API (returns flow of results)
fun fakeTransactionSearch(query: String, filter: String): Flow<List<String>> = flow {
    println("Searching \"$query\" with filter [$filter]...")
    delay(500) // Simulate API delay
    emit(
        listOf(
            "$query - $filter result 1",
            "$query - $filter result 2",
            "$query - $filter result 3"
        )
    )
}

suspend fun testMerge() {
    val cardPayment = flow<String> {
        emit("coffee : 5$")
        delay(100)
        emit("taxi : 2$")
        delay(700)
        emit("snack : 6$")
    }
    val bankTransfer = flow<String> {
        emit("salary : 1000$")
        delay(400)
        emit("loan : 400$")
        delay(200)
        emit("debt : 630$")
    }

    val job = merge(cardPayment,bankTransfer).onEach {trans->
        println("transaction event : $trans")
    }.launchIn(CoroutineScope(Dispatchers.Default))

    delay(4000)
    job.cancel()

}

suspend fun zipTest() {
    val job = transactionIdsFlow()
        .zip(transactionStatusFlow()) { id, status ->
            "Transaction ${"id is : $id"}: ${"status is : $status"}"
        }
        .onEach { result ->
            println(result)
        }
        .launchIn(CoroutineScope(Dispatchers.Default))

    delay(3000) // Wait for all transactions to finish
    job.cancel()
}

// Simulated transaction IDs
fun transactionIdsFlow(): Flow<String> = flow {
    emit("TXN-1001")
    delay(400)
    emit("TXN-1002")
    delay(400)
    emit("TXN-1003")
}

// Simulated transaction statuses
fun transactionStatusFlow(): Flow<String> = flow {
    delay(200) // Status updates come slightly later
    emit("CONFIRMED")
    delay(500)
    emit("PENDING")
    delay(300)
    emit("FAILED")
}


@OptIn(FlowPreview::class)
fun testSample() {
    val scrollPosition = MutableStateFlow(0)

    scrollPosition
        .sample(200) // Emit every 200ms
        .onEach { position ->
            println("Scroll position for analytics: $position")
        }

}
