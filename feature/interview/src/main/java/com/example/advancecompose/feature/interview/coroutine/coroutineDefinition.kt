package com.example.advancecompose.feature.interview.coroutine

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Coroutine :
 * Coroutine is a framework to manage concurrency in a more performant and simple way with its lightweight thread
 * which is written on top of the actual threading framework to get the most out of it by taking
 * the advantage of cooperative nature of functions.
 */

/**
 * dispatchers :
 * dispatchers help coroutine in deciding the thread on which work has to be done.
 * IO :
 * Main :
 * Default :
 */

/**
 * suspend :
 * suspend function is a function could be started, paused and resumed
 * suspend function called from coroutine or another suspend function
 */

/**
 * withContext :
 * it only shifts the context of the existing coroutine
 * it can return a result
 */

/**
 * scopes in coroutine : for canceling the background task as soon as the activity is destroyed
 */

/**
 * exception handler
 */

val handler = CoroutineExceptionHandler { _, throwable ->
    Log.d("Exception is", ": ${throwable.message}")
}

fun sampleExceptionHandler() {
    GlobalScope.launch(Dispatchers.IO + handler) {
        fetchUserData()
    }
}

suspend fun fetchUserData() {
    // make a http call
}

suspend fun asyncWithTryCatch() {
    val deferredJob = GlobalScope.async(Dispatchers.IO) {
        fetchUserData()
    }
    try {
        val user = deferredJob.await()
    } catch (e: Exception) {
        Log.d("Exception is", "asyncWithTryCatch: ${e.message}")
    }
}

/**
 * different between supervisorScope and coroutineScope
 * coroutineScope will cancel whenever any of its children fail.
 * A supervisorScope won't cancel other children when one of them fails.
 */