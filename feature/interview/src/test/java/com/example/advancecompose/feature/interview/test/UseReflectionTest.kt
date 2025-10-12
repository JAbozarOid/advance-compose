package com.example.advancecompose.feature.interview.test

import androidx.compose.runtime.reflect.getDeclaredComposableMethod
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.reflect.Method

@RunWith(JUnit4::class)
class UseReflectionTest {

    lateinit var useReflection: UseReflection

    @Before
    fun setUp() {
        useReflection = UseReflection()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test private method with the approach of reflection`() {

        // given
        val method: Method = useReflection.javaClass.getDeclaredMethod(
            "applyDiscount",
            Double::class.java
        )

        //when
        method.isAccessible = true
        val result = method.invoke(useReflection, 100.0) as Double

        println("<<< access with reflection >>> $result")

        //then
        Assert.assertEquals(result, 90.0, 0.9)

    }
}