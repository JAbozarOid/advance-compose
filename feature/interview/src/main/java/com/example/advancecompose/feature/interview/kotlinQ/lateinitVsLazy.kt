package com.example.advancecompose.feature.interview.kotlinQ

/**
 * lateinit
 */
open class SampleData() {

    open var testData : Int = 3
}

/**
 * when we want to declare a variable we have two options
 */
// 1- with null we must initialize it
private var sampleData1 : SampleData? = null

// 2- with lateinit we don't have to initialize it at the time of declaration - lateinit is non-nullable
private lateinit var sampleData2: SampleData

//*** lateintit must be initialize before use
fun initLateInitVar() {
    sampleData2 = SampleData()
}

// *** we can check lateinit variable is initialize or not
fun checkIsInitOrNot() {

    if (::sampleData2.isInitialized) {
        // write code
    }else {
        // do something else
    }
}


/**
 * lazy
 * when we want to create an object inside of the class
 */

class Information(){

    // class Information is depend to the creation of the class SampleData internally
    // if the creation of the sampleData takes time, the creation of the class Information will take time
    // sampleData creation is expensive
    private val sampleData1 : SampleData = SampleData()

    // sampleData2 will get initialized only when it is accessed for the first time
    // fast creation of Information class, because sample sampleData2 will not initialized unnecessary during the creation of Information class
    internal val sampleData2 : SampleData by lazy { SampleData() }


}

fun main() {
    val information = Information()

    println("for the fist access to lazy variable ${information.sampleData2.testData}")

    information.sampleData2.testData = 8

    // same object : sampleData2
    println("for the second access to lazy variable ${information.sampleData2.testData}")

}