package com.example.advancecompose.feature.interview.kotlinFlow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

/**
 * the difference between cold and hot flow
 * cold flow :
 *      1- emit values when there is a collector
 *      2- does not store data
 *      3- can't have multiple collectors
 * hot flow :
 *      1- emit values even there is no collector
 *      2- can store data
 *      3- can have multiple collectors
 */


/**
 * in cold flow the collector will get the values from the beginning
 */
private fun sampleColdFlow(): Flow<Int> {
    val coldFlow = channelFlow {
        (1..5).forEach {
            send(it)
        }
    }
    return coldFlow
}

private suspend fun sampleCollectColdFlow() {
    val collector = sampleColdFlow()

    collector.collect {
        println("1st collector for cold flow collect values from beginning of the 1 to 5 $it")
    }

    delay(2500)

    collector.collect {
        println("2st collector for cold flow collect values from beginning of the 1 to 5 $it")
    }

}

private fun sampleHotFlow() : Flow<Int> {
    val hotFlow = MutableSharedFlow<Int>(replay = 1)
    (0..5).forEach {
        GlobalScope.launch(Dispatchers.Default) {
            hotFlow.emit(it)
            delay(1000)
        }
    }
    return hotFlow
}


fun main() {
    GlobalScope.launch(dispatcher) {
        sampleCollectColdFlow()
    }

    GlobalScope.launch(Dispatchers.IO) {
        val hotCollector1 = sampleHotFlow()
        hotCollector1.collect {
            println("Collector A : $it")
            println("that's all")
        }
    }

    GlobalScope.launch(Dispatchers.IO) {
        val hotCollector1 = sampleHotFlow()
        hotCollector1.collect {
            println("Collector B : $it")
            println("that's all")
        }
    }

    println("..... and we are off!")
}