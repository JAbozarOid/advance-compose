package com.example.advancecompose.feature.interview.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// both of them start a coroutine
// launch {
//    1- return a job
//    2- does not carry any result
//    3- fire and forget
//    4- get status or cancel the job
//    5- exception handling : crash the application if we have not handle it
//    }
// async {
//    1- return an instance of Deferred<T>
//    2- await function return the result of the coroutine
//    3- perform a task and return a result
//    4- get status or cancel the job, but in deferred we can call await function to get the result
//    5- exception handling : it is stored inside the resulting deferred and not delivered anywhere else, silently dropped unless we handle it
//    }

val job = GlobalScope.launch(Dispatchers.IO) {
    // do something and do not return result
}

val deferredJob = GlobalScope.async(Dispatchers.IO) {
    // do something and return result
    return@async 10
}

private suspend fun getAsyncResult() {
    val result = deferredJob.await()

    println("the async result is $result")
}

fun main() {
    runBlocking {
        getAsyncResult()
    }
}


// ------------------------
// Exception Handling

private fun throwException() {
    throw Exception("Exception happened")
}

private fun callExceptionInsideLaunch() {
    GlobalScope.launch(Dispatchers.IO) {
        // with below line the application crash, unless we handle it like try/catch
        try {
            throwException()
        } catch (e: Exception) {
            println("Exception happened ${e.message}")
        }
    }
}

private fun callExceptionInsideAsync() {
    GlobalScope.async(Dispatchers.IO) {
        // exception dropped silently, we can handle it like try/catch
        throwException()
    }
}
object TestException {

    @JvmStatic
    fun main(args : Array<String>) {
//        callExceptionInsideLaunch()
        callExceptionInsideAsync()
    }
}




