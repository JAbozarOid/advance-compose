package com.example.advancecompose.feature.interview.algoritm

/**
 * Given positive integer n implement a function which calculates sum of all numbers from 1 up to (and including) number n.
 * addUpTo(1) // 1
 *
 * addUpTo(2) // 3
 *
 * addUpTo(3) // 6
 */
class AddUpTo {

    fun idiomaticSolution1(n: Int): Int {
        return (1..n).sum()
    }

    /**
     * fold is a method that accumulates all elements into a single variable (called an "accumulator") using a defined operation.
     * For instance, let's say that our collection contains the numbers from 1 to 4, our initial accumulator value is 0, and our operation is addition.
     */
    fun idiomaticSolution2(n: Int): Int {
        return (0..n).fold(0) { accumulated, current -> accumulated + current }
    }

    fun recursiveSolution(n: Int): Int {
        if (n == 1) return 1

        return n + recursiveSolution(n - 1)
    }

    // time complexity : o(1)
    fun mathematicalFormulaWithO1(n: Int): Int {
        return n * (n + 1) / 2
    }

    // time complexity : o(n)
    // iterative solution
    fun iterativeSolutionWithON(n: Int): Int {
        var total = 0
        repeat((0..n).count()) { total += 1 }
        return total
    }

    fun iterativeSolution(n: Int): Int {
        var total = 0
        repeat(n + 1) { total += it }
        return total
    }


}