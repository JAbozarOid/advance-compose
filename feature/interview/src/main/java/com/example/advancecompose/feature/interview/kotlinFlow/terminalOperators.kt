package com.example.advancecompose.feature.interview.kotlinFlow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * terminal operators start the flow by connecting the flow builder, operators with the collector
 * terminal operators :
 * 1- collect
 * 2- reduce
 */

private suspend fun sampleReduceCollector() : Int{
   val flow =  flow {
        (1..5).forEach { 
            emit(it)
        }
    }.reduce { a, b ->
        a + b
    }

    return flow
}

fun main() {
    runBlocking {
        println("${sampleReduceCollector()}")
    }
}