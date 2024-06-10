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
    returnContextObject()
    returnLambdaResult()
    localVariable()
    resultWithoutLet()
    resultWithLet()
    resultWithLetMethodReference()
    useWith()
    runWithoutExtension()
}

/**
 * it : let, also -> reference the context object as a lambda argument
 */
fun getRandomInt(): Int {
    return Random.nextInt().also {
        println("getRandomInt() generated value $it")
    }
}

/**
 * return value
 * apply, also : return the context object -> continue chaining function calls on the same object
 * let, run, with : return the lambda result -> assign the result to a variable
 */
private fun returnContextObject(): MutableList<Double> {
    val list = mutableListOf<Double>()
    return list.also {
        println("print the list $it")
    }.apply {
        this.add(3.1)
        this.add(2.2)
        this.add(4.3)
        this.add(1.4)
        println("print the populated list $this")
    }.also {
        println("print the sorted list $it")
    }
}

private fun returnLambdaResult() {
    val list = mutableListOf<String>("one", "two", "three")
    val addValue = list.run {
        add("four")
        add("five")
        add("six")
        add("seven")
        count {
            it.endsWith("e")
        }
    }
    println("counts with end of e are $addValue")
}

/**
 * ignore return value and
 * use scope function to create temporary scope for local variable
 */
private fun localVariable() {
    val num = mutableListOf<String>("one", "two", "three", "four", "five", "six")
    with(num) {
        val first = first()
        val last = last()
        println("the local variables are for first item $first and for the last item $last")
    }
}

/**
 * let
 * the context object as an argument : it
 * the return value is lambda result
 * execute a code block contain non-null values
 */
private fun resultWithoutLet() {
    val list = mutableListOf<String>("one", "two", "three", "four", "five")
    val result = list.map {
        it.length
    }.filter {
        it > 3
    }
    println("the result without let is $result")
}

private fun resultWithLet() {
    val list = mutableListOf<String>("one", "two", "three", "four", "five")
    list.map {
        it.length
    }.filter {
        it > 3
    }.let {
        println("the result with let $it")
    }
}

// method reference means ::
private fun resultWithLetMethodReference() {
    val list = mutableListOf<String>("one", "two", "three", "four", "five")
    list.map {
        it.length
    }.filter {
        it > 3
    }.let(
        ::println
    )
}


/**
 * with
 * the context object as an receiver : this
 * the return value is lambda result
 * it is not an extension function
 * use when you don't need to use the returned result.
 */
private fun useWith() {
    val nums = mutableListOf("one", "two", "three")
    val firstAndLast = with(nums) {
        println("the first element is ${first()}")
        println("the first element is ${last()}")
    }
    println(firstAndLast)
}

/**
 * - run as extension function
 *      the context object as an receiver : this
 *      the return value is lambda result
 * - run as non-extension function
 *      has no context object with return the lambda result
 */

private fun runExtensionFunction() {
    val str = "Abozar"
    str.run {
        println("the length of abozar is ${this.length}")
    }
}

private fun runWithoutExtension() {
    val hexNumRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"

        Regex("[$sign]?[$digits$hexDigits]+")
    }

    for (match in hexNumRegex.findAll("+123 -FFFF !%*& 88 XYZ")) {
        println("the regex value is ${match.value}")
    }
}


/**
 * apply
 * the context object as an receiver : this
 * the return value is object itself
 * multiple call chains for more complex processing.
 */


/**
 * also
 * the context object as an argument : it
 * the return value is object itself
 */




