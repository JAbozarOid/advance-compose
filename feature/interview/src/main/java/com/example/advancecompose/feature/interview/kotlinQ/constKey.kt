package com.example.advancecompose.feature.interview.kotlinQ

// tools -> kotlin -> show kotlin byte code

// advantage of using const
object ValClass {
    val name = "Abozar"
}

/**
 * when decompile the below code {
 *          1- new instance of ValClass created
 *          2- then call the method getName of class ValClass
 *      }
 */
private fun callValInsideClass() {
    val callValName = ValClass.name
}


//----------------------------------

object ConstClass {
    const val name = "Abozar"
}

/**
 * when decompile the below code {
 *          1- new instance of ConstClass created
 *          2- method getName not created
 *          3- just create String callConstName = "Abozar"; inside of ConstClass
 *      }
 # advantage : no overhead to access that variable at runtime
 */
private fun callConstInsideClass() {
    val callConstName = ConstClass.name
}