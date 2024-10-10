package com.example.advancecompose.feature.interview.algoritm

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OddNumbersTest {

    lateinit var oddNumbers: OddNumbers

    @Before
    fun setup() {
        oddNumbers = OddNumbers()
    }

    @Test
    fun `empty list return empty list`() {
        val list = oddNumbers.recursiveSolution(emptyList())

        Assert.assertEquals(list, emptyList<Int>())
    }

    @Test
    fun `1,2,3 return 1,3`() {
        val list = oddNumbers.recursiveWithHelperSolution(listOf(1, 2, 3))

        Assert.assertEquals(list, listOf(1, 3))
    }

    @Test
    fun `2,9,2,5,4 return 9,5`() {
        val list = oddNumbers.recursiveWithHelperSolution(listOf(2, 9, 2, 5, 4))

        Assert.assertEquals(list, listOf(9, 5))
    }

    @Test
    fun `7,4,6,8,7,9 return 7,7,9`() {
        val list = oddNumbers.recursiveSolution(listOf(7, 4, 6, 8, 7, 9))

        Assert.assertEquals(list, listOf(7, 7, 9))
    }
}