package com.example.advancecompose.feature.interview.kotlinQ.inline

// https://www.martinfowler.com/ieeeSoftware/whenType.pdf
// https://typealias.com/guides/inline-classes-and-autoboxing/

/**
 * when we use inline classes, it means the body of class will be inline, it means the body will be copy and paste
 */

// if we use this class a lot in many places, this means this class will be instantiated many times and this can bad effect on performance
// for avoiding this bad performance we can use inline class or value class
// value class means the places we use the below class, it will not create instance of the class, just pass the string
// value class can implement interfaces

interface Callable {
    val number: String
}

@JvmInline
value class PhoneNumber(override val number: String) : Callable {

    // number is underline type
    // PhoneNumber is wrapper type

    fun isIran(): Boolean {
        return number.startsWith("+98")
    }
}

class CallerService {

    // this function receive all type of strings
    fun callWithoutType(phoneNumber: String) {
        // call number
    }

    // we must create type to set definition, because we want to accept only phone numbers strings
    // when decompile this method to the java, it shows the method receive string as parameter, because of value class
    fun callWithType(phoneNumber: PhoneNumber) {
        // kotlin compiler works inline
        // `inline` means here is not really use PhoneNumber class, actually it is String
        println(phoneNumber.number)
    }

    fun callInterface(phoneNumber: Callable) {
        // kotlin compiler does not works inline, actually compiler works `boxing`
        // `boxing` means the below is really using the PhoneNumber class
        println(phoneNumber.number)
    }
}

fun getStringNumber(): String {
    return "989121111111"
}

fun getPhoneNumber(): PhoneNumber {
    return PhoneNumber("989121111111")
}

fun main() {
    val callerService = CallerService()

    // call function can receive any string, phone number or other strings
    callerService.callWithoutType(getStringNumber())

    // call function can receive any string, phone number or other strings
    callerService.callWithoutType("absd") // -> *** ISSUE ***

    callerService.callWithType(getPhoneNumber()) // -> receive only phone numbers


}
