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

    fun idiomaticSolution2(n: Int): Int {
        return (0..n).fold(0){accumulated,current -> accumulated + current}
    }

    fun recursiveSolution(n : Int) : Int {
        if (n == 1) return 1

        return n + recursiveSolution(n-1)
    }
}