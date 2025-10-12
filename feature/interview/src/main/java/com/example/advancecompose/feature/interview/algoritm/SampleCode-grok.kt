package com.example.advancecompose.feature.interview.algoritm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    val numbers = mutableListOf<Int>(10, 11, 12, 13, 14)
    println("the reversed list is ${reverseListInPlace(numbers)}")

    val dupList = mutableListOf<Video>(
        Video(id = "1", title = "vid1"),
        Video(id = "2", title = "Vid2"),
        Video(id = "3", title = "vid3"),
        Video(id = "1", title = "vid2"),
        Video(id = "1", title = "vid4"),
    )
    println("find duplicate with filter is :${findDuplicates(dupList, "1")}")
    println("find duplicate with set is : ${findDuplicates(dupList)}")

    val videoPairList = listOf<Pair<String, String>>(
        "Inception" to "Sci-Fi",
        "Matrix" to "Sci-Fi",
        "Titanic" to "Romance"
    )
    println("groupBy the ${groupByCategory(videoPairList)}")

    val list1 = listOf<Int>(1, 2, 5)
    val list2 = listOf<Int>(3, 6, 9)
    println("the sorted merged list is : ${mergeSortedList(list1, list2)}")

    runBlocking {
        println(
            "the emitted number from 1 to 5 is ${
                numberFlow().flowOn(Dispatchers.IO).collect { print(it) }
            }"
        )
    }
    runBlocking {
        val job = launch {
            playbackPosition.collect { println("position $it") }
        }
        updatePosition()
        delay(6000)
        job.cancel()

    }

    runBlocking {
        println("the combined two flow is ${combineTwoFlow(titles,durations).collect { print(it) }}")

    }

    runBlocking {
        // collecting shared error flow
        val job1 = launch {
            errorEvents.collect { print("Collector 1 $it") }
        }
        val job2 = launch {
            errorEvents.collect {
                print("Collector 2 $it")
            }
        }
        emitError("Server Error")
        delay(1000)
        emitError("Network Error")
        delay(1000)
        job1.cancel()
        job2.cancel()
    }
}


/**
 * Challenge: Write a function that reverses a mutable list of integers in-place (without creating a new list)
 * using a two-pointer approach. Then, extend it to handle a list of any type T.
 * Time: O(n/2) ≈ O(n), Space: O(1).
 */

fun <T> reverseListInPlace(list: MutableList<T>): List<T> {
    var left = 0
    var right = list.size - 1
    val result = mutableListOf<T>()

    while (left < right) {
        val temp = list[left]
        list[left] = list[right]
        list[right] = temp
        left++
        right--
        result.add(list[right])
    }


    return result
}

/**
 * find duplicate with given id and filter
 */
fun findDuplicates(list: List<Video>, id: String): List<String> {

    val result = list.filter {
        it.id == id
    }.map {
        it.title
    }.toList()

    return result
}

/**
 * Given a list of strings (e.g., video IDs), find and return all duplicates using a set for efficiency. Handle case-insensitivity if asked.
 * Iterate once, use a set to track seen items. If add fails, it's a duplicate. Time: O(n), Space: O(n).
 */
fun findDuplicates(list: List<Video>): List<Video> {
    val seen = mutableSetOf<String>()
    val duplicates = mutableSetOf<Video>() // use set to avoid duplicate duplicates

    for (item in list) {
        if (!seen.add(item.title.lowercase())) {
            duplicates.add(item)
        }
    }
    return duplicates.toList()
}

/**
 * Given a list of pairs (e.g., video title to category), group them into a map where key is category and value is list of titles. Use groupBy().
 * Output: {Sci-Fi=[Inception, Matrix], Romance=[Titanic]}
 * groupBy creates a map of key to list of pairs, then mapValues extracts the titles. Functional and concise.
 */
fun groupByCategory(videos: List<Pair<String, String>>): Map<String, List<String>> {
    return videos.groupBy { it.second }.mapValues { mapEntry ->
        mapEntry.value.map {
            it.first
        }
    }
}

/**
 * Challenge: Merge two sorted lists of integers into one sorted list without using sort(). Handle duplicates.
 * list 1 : [1,3,5]
 * list 2 : [2,4,6]
 * output : [1,2,3,4,5,6]
 * Time: O(n + m).
 */
fun mergeSortedList(list1: List<Int>, list2: List<Int>): List<Int> {
    var result = mutableListOf<Int>()
    var i = 0
    var j = 0

    while (i < list1.size && j < list2.size) {
        if (list1[i] <= list2[j])
            result.add(list1[i++])
        else
            result.add(list2[j++])
    }
    result.addAll(list1.subList(i, list1.size))
    result.addAll(list2.subList(j, list2.size))

    return result
}

// Flow and Coroutine Challenges
/**
 * Cold flows (like flow {}) start emitting on collection
 * hot flows (StateFlow, SharedFlow) can share state or broadcast
 */

/**
 * Create a cold flow that emits numbers from 1 to 5 with a 1-second delay between each. Collect it in a coroutine and print.
 */
fun numberFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(1000)
        emit(i)
    }
}

/**
 * Challenge: Create a StateFlow to hold the current playback position (Int) in a video player. Update it from one coroutine and observe from another.
 * StateFlow is hot; it holds the latest value and replays to new collectors. MutableStateFlow allows updates.
 */

val playbackPosition = MutableStateFlow<Int>(0)

// updater
fun updatePosition() = CoroutineScope(Dispatchers.Default).launch {

    repeat(5) {
        delay(1000)
        playbackPosition.value += 10
    }
}

/**
 * Challenge: Combine two cold flows:
 * one emitting video titles,
 * another emitting durations.
 * Output pairs only when both emit.
 * Output: Video1: 120 secs, etc.
 * combine transforms multiple flows into one, emitting when any source changes (but waits for all to have values initially).
 */

val titles = flowOf("vid1","vid2","vid3")
val durations = flowOf(120,150,180)

fun combineTwoFlow(flow1: Flow<String> , flow2: Flow<Int>) : Flow<Pair<String, Int>>  = flow{
    combine(flow1,flow2) {
        title, duration -> "$title : $duration secs"
    }.collect { println(it) }
}

/**
 * Challenge: Use MutableSharedFlow to broadcast error events (strings) to multiple collectors, like network errors in a streaming app.
 * SharedFlow is hot and shareable; replay caches recent emissions.
 */

val errorEvents = MutableSharedFlow<String>(replay = 2) // Replay last event to new subscribers
fun emitError(errorMsg : String) = CoroutineScope(Dispatchers.Default).launch {
    errorEvents.emit(errorMsg)
}

// General Algorithm Challenges in Kotlin

/**
 * Challenge: Implement binary search to find an element in a sorted list of longs (e.g., timestamps).
 * Time: O(log n).
 */
fun binarySearch(list: List<Long>, target: Long): Int {
    var left = 0
    var right = list.size - 1
    while (left <= right) {
        val mid = left + (right - left) / 2
        when {
            list[mid] == target -> return mid
            list[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }
    return -1
}

// Example
// println(binarySearch(listOf(1L, 3L, 5L, 7L), 5L))  // Output: the item is at the index 2

/**
 * Challenge: Compute the nth Fibonacci number using a map for memoization to avoid recursion stack issues.
 */
fun fibonacci(n: Int, memo: MutableMap<Int, Long> = mutableMapOf()): Long {
    if (n <= 1) return n.toLong()
    if (memo.containsKey(n)) return memo[n]!!
    val result = fibonacci(n - 1, memo) + fibonacci(n - 2, memo)
    memo[n] = result
    return result
}

// Example
//println(fibonacci(10))  // Output: 55