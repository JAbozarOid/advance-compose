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
import kotlin.random.Random

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

/**
 * the difference between cold and hot flow
 * cold flow :
 *      1- emit values when there is a collector
 *      2- does not store data
 *      3- can't have multiple collectors
 *      4- flow : sequence of values can be synchronously computed and delivered  over time
 * hot flow :
 *      1- emit values even there is no collector
 *      2- can store data
 *      3- can have multiple collectors
 *      4- shared-flow : allows multiple collectors to listen to the same stream
 *      5- stateflow : stores the last state and emit to all collectors
 *
 */

/**
 * Observable : {
 *                  - Single/Flowable
 *                  - Flow/stateFlow/SharedFlow
 *                  }
 * Subscriber : {
 *                  - observe / subscribe
 *                  - collect
 *                  }
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
    for (i in 0 until 10) {
        GlobalScope.launch(Dispatchers.Default) {
            hotFlow.emit(Random.nextInt(1,1000))
            delay(200)
        }
    }
    return hotFlow
}


fun main() {
    GlobalScope.launch(dispatcher) {
        sampleCollectColdFlow()
    }

    val hotCollector = sampleHotFlow()

    GlobalScope.launch(Dispatchers.IO) {
        hotCollector.collect {
            println("Collector A : $it")
            println("that's all")
        }
    }

    GlobalScope.launch(Dispatchers.IO) {
        delay(1000)
        hotCollector.collect {
            println("Collector B : $it")
            println("that's all")
        }
    }

    println("..... and we are off!")
}