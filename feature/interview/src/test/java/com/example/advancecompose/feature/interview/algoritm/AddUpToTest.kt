package com.example.advancecompose.feature.interview.algoritm

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AddUpToTest {

    lateinit var addUpTo: AddUpTo

    @Before
    fun setUp() {
        addUpTo = AddUpTo()
    }

    @Test
    fun `add up to 1 should equal to 1`() {
        val upTo = addUpTo.idiomaticSolution1(1)

        Assert.assertEquals(upTo,1)
    }

    @Test
    fun `add up to 3 should equal to 6`() {
        val upTo = addUpTo.mathematicalFormulaWithO1(3)

        Assert.assertEquals(upTo,6)
    }

    @Test
    fun `add up to 10 should equal to 55`() {
        val upTo = addUpTo.iterativeSolution(10)
        Assert.assertEquals(upTo,55)
    }
}