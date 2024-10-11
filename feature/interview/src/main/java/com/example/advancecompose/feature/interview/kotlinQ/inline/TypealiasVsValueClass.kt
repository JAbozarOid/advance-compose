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

/**
 * ما میتونیم برای یک کلاس، اینترفیس یا هر تایپ دیگه با استفاده از typealias یه اسم مستعار بزاریم
 */
typealias Account = DataManager // *** typealias doesn't create another class, it obvious in decompile code
class DataManager(val name : String) {
    fun manage() {

    }
}

// when decompile the two below codes we can see both of them are DataManager object
val account = Account("Abozar") // there is not Account in compile time, Account is DataManager
val data = DataManager("Raghib")

fun main() {

    // we can pass MobileNumber and String pass to this function, because both of them are same

    call(mobileNumber = "989121111111")
    call("  abcd")

    account.manage()

}




