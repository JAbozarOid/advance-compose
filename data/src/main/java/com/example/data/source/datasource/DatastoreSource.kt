package com.example.data.source.datasource

import org.koin.core.annotation.Single

@Single
internal class DatastoreSource {
    fun getLastValue() : String = "datastore1"
}