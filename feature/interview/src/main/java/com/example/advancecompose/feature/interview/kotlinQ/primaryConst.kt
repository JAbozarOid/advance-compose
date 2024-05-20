package com.example.advancecompose.feature.interview.kotlinQ

// primary constructor
class UserClass(private val firstname: String, private val lastname: String) {

    // secondary constructor
    constructor(firstname: String, lastname: String, interest: String) : this(
        firstname = firstname,
        lastname = lastname
    ) {
        // we can write code inside secondary constructor
    }

    // there are two ways to put some code to do something during initialization
    // 1- init block
    // 2- secondary constructor

    /**
     * the init block called after primary constructor and before secondary constructor
     * we can have more than one init block, they run in the order if they appear in the class body
     */
    init {
        val fullname = "$firstname $lastname".also {
            println("the fullname is $it")
        }
    }


}

