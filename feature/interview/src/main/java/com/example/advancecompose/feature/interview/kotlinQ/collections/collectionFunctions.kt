package com.example.advancecompose.feature.interview.kotlinQ.collections

// kotlin collection functions

// 1- associate by : convert list to map
data class Contact(val name: String, val phoneNumber: String) {

    constructor(fullName: String, tel: String, email: String = "$fullName.$tel") : this(
        name = fullName, phoneNumber = tel
    ) {
        println("the default email for user is $email")
    }
}

val contactList = mutableListOf<Contact>(
    Contact(name = "Abozar", phoneNumber = "1234"),
    Contact(name = "Amir", phoneNumber = ""),
    Contact(name = "AA", phoneNumber = "23"),
    Contact(name = "BB", phoneNumber = ""),
    Contact(name = "ABO", phoneNumber = ""),
    Contact(name = "Zar", phoneNumber = "12345678"),
    Contact(name = "BBo", phoneNumber = ""),
    
)

// use associate by on the contact list { key as name : value as phone number}
private fun associateOnList() {
    val nameToNumberMap = contactList.associateBy(
        { it.name }, { it.phoneNumber })

    println("name to number map is $nameToNumberMap")
}

// 2- partition : filter a collection by a prediction and keep the elements don't match
// Splits the original collection into pair of lists,
// where first list contains elements for which predicate yielded true,
// while second list contains elements for which predicate yielded false.
private fun partitionOnList() {
    // filter contact list with phone number and without phone number
    val (phoneList, withoutPhoneList) = contactList.partition {it.phoneNumber.isNotEmpty()}

    println("the phone list is $phoneList")
    println("the without phone list is $withoutPhoneList")
}

fun main() {
    associateOnList()
    partitionOnList()
}