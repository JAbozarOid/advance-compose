package com.example.advancecompose.feature.interview.algoritm

/**
 * Given list of strings and a string return index of th value in the list or -1 if value was not found.
 * Don't use any Kotlin build-in methods that are directly returning index of element like indexOf / lastIndexOf / indexOfFirst / indexOfLast, etc.
 */

// time complexity : O(n)
private object LinearSearch {
    fun getIndex(list: List<String>, str: String): Int {
        list.forEachIndexed { index: Int, s: String ->
            if (s == str)
                return index
        }
        return -1
    }
}

fun main() {
    println(
        "the index of a is ${
            LinearSearch.getIndex(
                listOf<String>("r", "A", "g", "h", "a"),
                str = "a"
            )
        }"
    )
}
