package com.example.core.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JSONProvider {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    //json to model class
    fun <T> fromJson(json: String, clazz: Class<T>): T? {
        return moshi.adapter(clazz).fromJson(json)
    }

    // model class to json
    fun <T> toJson(value: T, clazz: Class<T>): String {
        return moshi.adapter(clazz).toJson(value)
    }
}