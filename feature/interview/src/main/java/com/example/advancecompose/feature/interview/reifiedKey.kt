package com.example.advancecompose.feature.interview

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// reified keyword in kotlin

data class User(val name: String)

// we want to convert the json string to User data class to get name
object WithoutT {
    @JvmStatic
    fun main(args: Array<String>) {

        val json = "{\"name\" : \"Abozar\"}"

        // every time we want to convert json string to data class we should write a below code
        // so I created an extension function for the below code
        val userType = object : TypeToken<User>() {}.type
        val user = Gson().fromJson<User>(json, userType)

        println(user.name)
    }
}

object WithT {

    @JvmStatic
    fun main(args: Array<String>) {
        val json = "{\"name\" : \"Abozar\"}"
        val user = Gson().fromJson<User>(json)

        println(user.name)
    }

    // if doesn't use reified keyword the compiler error will be happen -> cannot be cast to class User
    // reified means something more concrete or real
    private inline fun <reified T> Gson.fromJson(json: String): T {
        return fromJson(json, object : TypeToken<T>(){}.type)
    }
}
