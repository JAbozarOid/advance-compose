package com.example.advancecompose.feature.interview.algoritm


fun main() {
    val list = listOf(10, 21, 2, 3, 4, 5)


    var min: Int = list[0]
    var max: Int = list[0]
    var sum = 0
    list.forEach {
        if (it < min) {
            min = it
        }
        if (it > max) {
            max = it
        }
        sum += it
    }
    sum -= max + min
    println("sum $sum")
}

