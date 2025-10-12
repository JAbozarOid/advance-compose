package com.example.advancecompose.feature.interview.test

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class MyCoroutineRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()

    @Test
    fun testFetchData() {

        //given
        val repository = MyCoroutineRepository()

        //when
        val result = runTest(testDispatcher) {
            repository.fetchData()
        }

        //then
        assertEquals("Data", result)

    }

    @Test
    fun testGetDataStream() = runTest {
        // given
        val repository = MyCoroutineRepository()

        // Collect all emitted values into a list -> capturing emitted values
        val emittedValues = repository.getDataStream().toList()

        // Assert the values -> verifying values
        assertEquals(listOf(1, 2), emittedValues)
    }
}