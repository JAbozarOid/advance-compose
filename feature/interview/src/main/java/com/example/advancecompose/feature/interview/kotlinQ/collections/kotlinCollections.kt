package com.example.advancecompose.feature.interview.kotlinQ.collections

/**
 * collections in kotlin
 * 1- list
 * 2- set
 * 3- map
 */

/**
 * Collection types : Kotlin Standard Library provides implementations for basic collection types: sets, lists, and maps. A pair of interfaces represent each collection type
 * 1- read-only interface : are covariant --> "out"
 * 2- mutable interface with write operations : adding,removing,updating --> are not covariant
 *     - Write operations with a mutable collection are still possible even if it is assigned to a val
 *     - val is that you protect the reference to the mutable collection from modification.
 *          Over time, as your code grows and becomes more complex, it becomes even more important to prevent unintentional modification to references
 */

private val numbers = mutableListOf("one","two","three")


/**
 * Collection<T> is the root of the collection hierarchy. This interface represents the common behavior of a read-only collection
 * MutableCollection<T> is a Collection with write operations, such as add and remove.
 */
private fun printAll(strings : Collection<String>) {
    for (s in strings) println("$s ")
}

fun main() {
    // it is possible to add element to mutable list
    numbers.add("four")
    numbers.add("five")

    // it is not possible to reassign again to numbers
    // numbers = mutableListOf("1", "2") // compile error happened

    val stringList = listOf("1","2","3")

    val stringSet = setOf("one", "two", "three")

}