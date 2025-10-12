package com.example.advancecompose.feature.interview.kotlinQ.inner

class Parent(

    private val firstName: String,
    private val lastName: String
) {

    init {
        println("the first name in parent $firstName and the last name is $lastName")
    }

    inner class Child {

        private var name: String = ""

        init {
            name = "$firstName $lastName"
            println("the name in child is $name")
        }
    }
}

fun main() {
    val parent = Parent(firstName = "Abozar", lastName = "Raghibdoust")

}