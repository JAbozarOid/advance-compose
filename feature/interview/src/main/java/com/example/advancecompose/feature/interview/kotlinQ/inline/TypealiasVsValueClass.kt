package com.example.advancecompose.feature.interview.kotlinQ.inline

typealias MobileNumber = String

/**
 * the key difference between typealias and value class is
 * A- typealias is exactly String here with all functionality that string have
 * B- Value class can have function, but typealias can not, because typealias is exactly equal to the type
 */
fun call(mobileNumber: MobileNumber) {
    println(mobileNumber)
}

fun main() {

    // we can pass MobileNumber and String pass to this function, because both of them are same

    call(mobileNumber = "989121111111")
    call("  abcd")
}