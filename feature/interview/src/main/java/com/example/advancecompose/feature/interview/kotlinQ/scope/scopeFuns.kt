package com.example.advancecompose.feature.interview.kotlinQ.scope

import kotlin.random.Random

/**
 * In this scope, you can access the object without its name
 * these functions all perform the same action: execute a block of code on an object.
 * 1- let
 *      - object ref -> it
 *      - return value -> lambda result
 *      - is extension : Yes
 *      - usage : {
 *                  - Executing a lambda on non-nullable objects
 *                  - Introducing an expression as a variable in local scope
 *                  }
 * 2- run
 *      - object ref -> this
 *      - return value -> lambda result
 *      - is extension : Yes
 *      - usage : {
 *                  - Object configuration and computing the result
 *                  -
 *                  }
 * 3- run
 *      - object ref -> ---
 *      - return value -> lambda result
 *      - is extension : called without the context object
 *      - usage : {
 *                  - Running statements where an expression is required: non-extension
 *                  }
 * 3- with
 *      - object ref -> this
 *      - return value -> lambda result
 *      - is extension :  takes the context object as an argument.
 *      - usage {
 *                  - Grouping function calls on an object
 *                 }
 * 4- apply
 *      - object ref -> this
 *      - return value -> Context object
 *      - is extension : Yes
 *      - usage : {
 *                  - Object configuration
 *                  }
 * 5- also
 *      - object ref -> it
 *      - return value -> Context object
 *      - is extension : Yes
 *      - usage : {
 *                  - Additional effects
 *                  }
 */

/**
 * difference between scope functions
 * 1- The way they refer to the context object.
 *      - context object means this or it, {
 *          - this : lambda receiver
 *          - it   : lambda argument
 *      }
 *      - Inside the lambda passed to a scope function, the context object is available by a short reference instead of its actual name.
 * 2- Their return value.
 */


internal fun testScope() {
    val str = "Abozar"

    str.run {
        println("the length is $length")
    }

    str.let {
        println("the length is ${it.length}")
    }
}

/**
 * this : run, with, apply -> reference the context object as a lambda receiver
 */

internal data class Person(var id: Int = 0, var name: String = "")

private fun configureObject(): Person {
    return Person().apply {
        id = 1
        name = "Abozar"
    }
}

fun main() {
    println("the person object id is ${configureObject().id} and name is ${configureObject().name}")
    getRandomInt()
}

/**
 * it : let, also -> reference the context object as a lambda argument
 */
fun getRandomInt(): Int {
    return Random.nextInt().also {
        println("getRandomInt() generated value $it")
    }
}

