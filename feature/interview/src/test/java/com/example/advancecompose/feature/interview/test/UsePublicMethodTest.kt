package com.example.advancecompose.feature.interview.test

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class UsePublicMethodTest {

    lateinit var usePublicMethod: UsePublicMethod

    @Before
    fun setup() {
        usePublicMethod = UsePublicMethod()
    }

    @Test
    fun `test private method with the approach of the using public method`() {

        //given
        // init the class

        //when
        val discountedPrice = usePublicMethod.calculateFinalPrice(originalPrice = 100.0)

        println("the discounted price is : $discountedPrice")
        //then
        Assert.assertEquals(discountedPrice, 90.0, 0.9)


    }
}