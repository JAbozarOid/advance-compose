package com.example.advancecompose.feature.interview.algoritm

/**
 * Given two ranges implement a function which checks if range1 contains range2.
 *
 * containsRange(5..7, 5..7) // true
 *
 * containsRange(1..12, 5..7) // true
 *
 * containsRange(5..8, 5..9) // false
 */
class ContainRange {

    fun containRange(range1: IntRange, range2: IntRange) =
        range2.first >= range1.first && range2.last <= range1.last
}