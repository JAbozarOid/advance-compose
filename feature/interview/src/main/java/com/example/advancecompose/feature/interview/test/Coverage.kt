package com.example.advancecompose.feature.interview.test

/**
 * You can measure how many lines of code of your app have been executed when you run your tests
 */

fun calculateTestCoverage(linesOfCodeCalledByTheTestSuit: Int, totalLinesOfCode: Int): Double {

    return (linesOfCodeCalledByTheTestSuit / totalLinesOfCode) * 100.toDouble()

}

fun main() {
    println("if you write 200 lines test code for the 2000 total lines of code, the test coverage percentage is" + " " +
            "${calculateTestCoverage(linesOfCodeCalledByTheTestSuit = 1000, totalLinesOfCode = 2000)}")
}

/**
 *  Function/method coverage: How many functions have been called?
 *  Statement coverage: How many statements of each function have been executed?
 *  Branch coverage: Has each branch in an if or a when statement been executed?
 *  Condition coverage: Has each sub condition in an if statement been evaluated to true and also to false?
 */