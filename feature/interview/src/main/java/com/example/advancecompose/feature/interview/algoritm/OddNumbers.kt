package com.example.advancecompose.feature.interview.algoritm

/**
 * Given a list of integers return a list that contains only odd integers (only integers which are not a multiple of 2)
 *  getOdd(listOf(1, 2, 3)) // 1, 3
 *  getOdd(listOf(4, 6, 8, 7, 9)) // 7, 9
 */
class OddNumbers {

    // idiomatic solution
    fun idiomaticSolution(list: List<Int>): List<Int> {
        return list.filter {
            it % 2 == 1
        }
    }

    // recursive solution
    fun recursiveSolution(list: List<Int>): List<Int> {

        if (list.isEmpty()) {
            return list
        }

        return if (list.first() % 2 == 1) {
            mutableListOf(list.first()) + idiomaticSolution(list.drop(1))
        } else {
            idiomaticSolution(list.drop(1))
        }
    }

    // recursive with helper solution
    fun recursiveWithHelperSolution(list: List<Int>): List<Int> {
        val result = mutableListOf<Int>()

        fun helper(list: List<Int>) {
            if (list.isEmpty()) {
                return
            }

            if (list.first() % 2 == 1) {
                result.add(list.first())
            }

            helper(list.drop(1))
        }

        helper(list)

        return result
    }


}