package com.example.advancecompose.feature.interview.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

// for doing tasks in parallel

/**
 * different between supervisorScope and coroutineScope
 * coroutineScope will cancel whenever any of its children fail.
 * A supervisorScope won't cancel other children when one of them fails.
 */

data class User(
    val name: String
)

suspend fun getUsers(): User {
    return User(name = "Abozar")
}

suspend fun getMoreUsers() {
    throw Exception("Error happened")
}

/**
 * # 1
 * the app will crash if one of the tasks leads to an error, will not go to catch block
 */
fun parallelCalls() {
    GlobalScope.launch(dispatcher) {
        try {
            val usersDeferred = async { getUsers() }
            val moreUserDeferred = async { getMoreUsers() }
            val users = usersDeferred.await()
            println("the users $users")
            val moreUsers = moreUserDeferred.await()
            println("the more users are $moreUsers")
        } catch (e: Exception) {
            println("if in one of the tasks error happened the app will crash")
            TODO("one of the tasks leads to an error, will not go to catch block")
        }
    }
}

/**
 * # 2
 * for solving the above issue we can use coroutineScope
 * if any network errors happened it will go to catch block
 */
fun parallelCallsWithCoroutineScope() {
    GlobalScope.launch(dispatcher) {
        try {
            coroutineScope {
                val usersDeferred = async { getUsers() }
                val moreUserDeferred = async { getMoreUsers() }
                val users = usersDeferred.await()
                println("the user is $users")
                val moreUsers = moreUserDeferred.await()
                println("the more users are $moreUsers")
            }
        } catch (e: Exception) {
            println("if in one of the tasks errors happened, but app will not crash")
            TODO("if any network errors happened it will go to catch block")
        }
    }
}

/**
 * # 3
 * we want to return an empty list for the network call which has failed
 * and continue with the response from the other network call
 */
fun parallelCallsWithSupervisorScope() {
    GlobalScope.launch(dispatcher) {
        supervisorScope {
            val usersDeferred = async {
                getUsers()

            }
            val moreUserDeferred = async { getMoreUsers() }
            try {
                val users = usersDeferred.await()
                println("the users is ${users.name}")
            } catch (e: Exception) {
                emptyList<User>()
                println("the exception users ${emptyList<User>()}")
            }
            try {
                val moreUsers = moreUserDeferred.await()
                println("the more users are $moreUsers")
            } catch (e: Exception) {
                emptyList<User>()
                println("the exception more users ${emptyList<User>()}")
            }
        }

    }
}

fun main() {
//    parallelCallsWithSupervisorScope()
//    parallelCallsWithCoroutineScope()
    parallelCalls()
}

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
