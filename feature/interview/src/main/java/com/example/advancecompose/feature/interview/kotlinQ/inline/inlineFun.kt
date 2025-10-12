package com.example.advancecompose.feature.interview.kotlinQ.inline

// tools -> kotlin -> show kotlin byte code

// what is inline functions
/**
 # define : inline keyword said to compiler copy the body of the inline function
 * wherever the function is called
 # advantage : {
 *          1- function call overhead doesn't occur
 *          2- less overhead
 *          3- faster program execution
 *      }
 # usage  : when the function code is small
 # not use: {
            1- function code is large
            2- called from many places -> large code repeated again
        }
 */
private inline fun inlineFun(data:() -> Unit) {
    println("print inline fun ")
}

private fun withoutInlineFun() {
    println("print test 2 fun ")
}

private fun callInlineAndWithoutInlineFun() {
    println("print test fun")
    withoutInlineFun()
    inlineFun {
    }
}