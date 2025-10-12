package com.example.advancecompose.feature.interview.test

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyCoroutineRepository {
    suspend fun fetchData() : String {
        delay(2000) // Simulating network or long operation
        return "Data"
    }

    /**
     * Testing Flow requires capturing emitted values and verifying them
     */
    fun getDataStream(): Flow<Int> = flow {
        emit(1)
        delay(1000)
        emit(2)
    }
}

