package com.example.advancecompose.feature.interview.algoritm

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ContainRangeTest {

    lateinit var containRange: ContainRange

    @Before
    fun setUp() {
        containRange = ContainRange()
    }

    @Test
    fun `5-7 range contain 5-7 return true`() {

        val bln = containRange.containRange(5..7, 5..7)

        Assert.assertTrue(bln)
    }

    @Test
    fun `1-12 range contain 5-7 return true`() {
        val bln = containRange.containRange(1..12,5..7)

        Assert.assertTrue(bln)
    }
    @Test
    fun `5-8 range contain 5-9 return false`() {
        val bln = containRange.containRange(5..8,5..9)

        Assert.assertFalse(bln)
    }
}