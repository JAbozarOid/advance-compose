package com.example.advancecompose.feature.interview.kotlinQ.extensions

/**
 * 1- extension functions works on a type
 */
fun String.isAPhoneNumber(): Boolean {
    return this.startsWith("+98")
}

fun <T, S> List<T>.isTheSameSize(set: Set<S>): Boolean {
    return this.size == set.size
}

////////////////////////////////////////////////////////////

/**
 * 2- nullability
 */
fun String.toName(): String {
    return "Hello $this"
}

fun String?.toFamily(): String {
    if (this == null)
        return "Hello null"
    return "Hello $this"
}

//////////////////////////////////////////////////////////////

/**
 * 3- where syntax
 */
open class UseCase {}
interface OnBackground {}
class AboUseCase : UseCase(), OnBackground {
    fun use() {
        runOnBackground()
    }
}

fun <T> T.runOnBackground(): Int
        where T : UseCase, T : OnBackground {
    return 2
}

/////////////////////////////////////////////////////////////////

/**
 * 4- companion object
 */
class User1

fun User1.isValid() {
    // this extension function will be create on a instance of the User class
    // works on instance
    // `this` is a user instance
}

// *** if the class have companion object we can declare en extension function for the companion object of that class
class User2 {
    companion object
}

// we can use these extension functions when we want use the name of the class before extension function
fun User2.Companion.dbName(): String {
    // `this` is not a User 2 instance

    return "Users"
}

///////////////////////////////////////////////////////////////////////

/**
 * 5- visibility
 */

class User3 {
    private fun String.isFinal(): Boolean {
        return this.length > 2
    }

    // access the extension function just in the class
    fun otherFunction() {
        "hello".isFinal()
    }
}

/////////////////////////////////////////////////////////////////////////////

/**
 * 6- receiver context
 * we can change the context of the object with this@ClassName
 */

class User4 {
    private fun isEmpty(): Boolean {
        return false
    }

    fun String.anotherFunction() {
        val stringIsEmpty =
            this.isEmpty() // inside of extension function this can refer to another extension function
        val userIsEmpty = this@User4.isEmpty() // with @ we can change the context of the object
    }
}

////////////////////////////////////////////////////////////////////////////////
/**
 * 7- compiler
 * extension function will not change your class and does not add method to your class
 * when byte code the extension function the result is a `static method` so it means extension functions are
 * 100 percent interoperability with java -> refer to `JavaFile` class
 */

////////////////////////////////////////////////////////////////////////////////

/**
 * 8- operator overloading
 */

class GithubProject
class GithubRepository {
    fun add(githubProject: GithubProject) {

    }
}

operator fun GithubRepository.plus(githubProject: GithubProject): GithubRepository {
    add(githubProject)
    return this
}

fun getRepository(): GithubRepository {
    return GithubRepository()
}

////////////////////////////////////////////////////////////////////////////////////

/**
 * 9- extension function usage
 * زمانی استفاده میشن که ما میخوایم از بیرون یک کلاس بهش قابلیتی و اضافه کنیم
 *
 * Usage of extension function
 *   A- adding utility function
 *      - add helper method
 */

@JvmInline
value class PhoneNumber(val number : String) {

    // here instead of using extension function I use normal function for validation
    fun isValid() : Boolean {
        return true
    }
}

fun main() {

    //1-
    val checkPhone = "+989121111111".isAPhoneNumber()
    println(checkPhone)

    //2-
    val name: String? = null
    name?.toName() // name variable is null, it doesn't go to the extension function and just return null
    name.toFamily() // name variable is null, it goes to the extension function and check nullability in function
    name.orEmpty() // orEmpty works like toFamily extension -> Returns the string if it is not null, or the empty string otherwise.

    //3-
    val aboUseCase = AboUseCase()
    aboUseCase.runOnBackground()

    //4-
    User2.dbName()

    //8-
    val repository = getRepository()
    repository.add(GithubProject())
    val newRepository = repository + GithubProject()



}