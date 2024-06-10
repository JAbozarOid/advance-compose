package com.example.advancecompose.feature.interview.kotlinQ

// 1- lambda expression as below
val square: (Int) -> Int = { value ->
    value * value
}

val add: (Int, Int) -> Int = { x, y ->
    x + y
}

//------------------------------

// 2- higher order functions : is a function that takes functions as parameter and return a function
fun passMeFunction(func: () -> Unit) {

    //execute function
    func()
}

fun returnMeAddFunc() : (Int,Int) -> Int {
    return add
}


fun main() {
    println("the square is ${square(3)}")
    println("the plus is ${add(3, 8)}")
    println(
        "pass me func result is ${
            passMeFunction {
                println("test pass me func")
            }
        }"
    )
    println("test return me add func ${returnMeAddFunc()}")
}