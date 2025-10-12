package com.example.advancecompose.feature.interview.kotlinQ

// open for class, variable and function

/**
 * the open for the class means the class is open for extension
 * in Kotlin all classes are final by default, they can not be inherited
 * it's opposite of java
 */
open class Mentor() {

    open val learner: String = ""

    open fun guide() {
        println("Everyday You need to be learn new thing")
    }

    /**
     * functions in Kotlin are final by default, it means they can not be override
     */
    fun selfMotivated() {

    }


}

/**
 * class Experience is final
 */
class Experience : Mentor() {
    override fun guide() {
        println("some days learn more")
    }
}


fun main() {
    val experience = Experience()
    println(experience.guide())
}