package com.example.advancecompose.feature.interview.kotlinQ

/**
 * The best thing about Kotlin is that it is designed with Java interoperability in mind.
 * It means that the existing Java code can be called from Kotlin,
 * and also the Kotlin code can be called from Java. Both ways are supported.
 */

/**
 * calling kotlin code from java
 * 1- @JvmStatic
 */
object AppUtil {

    // if java want to call the install() method there will be a compile error
    // we calling this method from java without compile error we need to add jvm static annotation
    @JvmStatic // this annotation make java like this : AppUtil.INSTANCE.install() but without INSTANCE
    fun install() {

    }
}

// call in kotlin
private fun callInstall() {
    AppUtil.install()
}


/**
 * calling kotlin code from java
 * 2- @JvmField
 */

data class Session(val id: Int, val name: String)

// using session class in Kotlin
private fun createSessionKotlin() {
    val session = Session(id = 0, name = "Abozar")
    println("the session name is ${session.name}")
}

//using the session class in Java
private fun createSessionJava() {
    //Session session = new Session("0","Abozar");
    //String name = session.name; -> there will be compile error

    // if we want to solve the compile error we must use getter method
    //Session session = new Session();
    //String name = session.getName();

    // if we want to tell compiler to not create getter and setter for a variable, should use @JvmField annotation
    // @JvmField instruct the Kotlin compiler not to generate any getter and setter
}

data class NewSession(val id : Int, @JvmField val name : String)

// so we can create in java like below after adding @JvmField
private fun createSessionJava2() {
    //Session session = new Session("0","Abozar");
    //String name = session.name; -> there is not any compile error and there is no getter method
}

/**
 * calling kotlin code from java
 * 3- @JvmOverloads
 */
// java doesn't support default parameters but Kotlin support it
data class SampleUser(val id : Int = 0, val name : String = "")

private fun createSampleUser() {
    val user1 = SampleUser(id = 0, name = "Abozar")
    val user2 = SampleUser(name = "Abozar") // although the id is not create but there is not any error because of default parameters

    // but calling the user2 in java will be a compile error because id is not created
    // for solving this issue we must add @JvmOverloads to the constructors
}

data class SampleUser2 @JvmOverloads constructor(val id: Int = 0 , val name: String = "")