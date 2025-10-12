package com.example.advancecompose.feature.interview.kotlinQ

// infix : allows a function to be called without the use of the dot and parentheses

infix fun Int.add(x : Int) : Int = this + x

// to in map is infix
val map = mapOf(
    "1" to "one",
    "2" to "two",
    "3" to "three"
)

fun main() {
    println("the output of the infix add is ${4 add 6}")
}