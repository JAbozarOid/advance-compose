package com.example.advancecompose.feature.interview.algoritm

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CapitalizeSentenceTest {

    lateinit var capitalizeSentence: CapitalizeSentence

    @Before
    fun setUp() {
        capitalizeSentence = CapitalizeSentence()
    }

    @Test
    fun `flower is capitalized to Flower`() {
        val capStr = capitalizeSentence.idiomaticSolution(str = "flower")
        println("using solution 1 is $capStr")
        Assert.assertEquals(capStr,"Flower")

    }

    @Test
    fun `this is a house is capitalized to This Is A House`() {
        val capStr = capitalizeSentence.iterativeSolution(str = "this is a house")
        println("using solution 2 is $capStr")
        Assert.assertEquals(capStr,"This Is A House")
    }
}