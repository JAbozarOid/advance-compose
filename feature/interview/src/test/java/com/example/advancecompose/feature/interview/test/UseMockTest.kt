package com.example.advancecompose.feature.interview.test

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UseMockTest {

    @InjectMocks
    lateinit var useMock: UseMock

    @Mock
    lateinit var discountService : DiscountService

    @Test
    fun `test private method with the approach of the using mockito`() {
        `when`(discountService.getDiscountedPrice(100.0)).thenReturn(90.0)

        val finalPrice: Double = useMock.calculateFinalPrice(100.0)
        println("final price is $finalPrice")

        Assert.assertEquals(finalPrice, 90.0, 0.9)
    }
}