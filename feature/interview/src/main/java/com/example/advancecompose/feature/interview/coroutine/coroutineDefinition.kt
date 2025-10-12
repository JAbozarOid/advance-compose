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
 * What is structured concurrency in Kotlin coroutine?
 * we can use structured concurrency to do three things
 * 1- Cancel work when it is no longer needed
 * 2- Keep track of work while it’s running.
 * 3- Signal errors when a coroutine fails
 * *** Structured concurrency guarantees that when a coroutine errors, its caller or scope is notified.
 * 1- When a scope(view model scope, lifecycle scope) cancels, all of its coroutines cancel.
 * 2- When a suspend fun returns, all of its work is done.
 * 3- When a coroutine errors, its caller or scope is notified.”
 */

/**
 * What does it mean when we say coroutine is lightweight
 * 1- many coroutines can run in a single thread due to support suspension, doesn't block the thread the coroutine is running
 * 2- suspending save memory over blocking while supporting many concurrent operations
 * 3- fewer memory leaks
 * 4- use structured concurrency to run operations within a scope
 */

/**
 * what is the difference between coroutine and rxjava
 * - both of them use thread pools for concurrency, but they have different default thread pool implementation
 * - Coroutines use a concept called Dispatchers to determine which thread or threads to execute code on.
 *      - By default, when you launch a coroutine without specifying a dispatcher, it runs in the context of the current thread or a specific thread if specified.
 * - RxJava also provides schedulers for controlling concurrency, and they have similar characteristics to coroutines' dispatchers.
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