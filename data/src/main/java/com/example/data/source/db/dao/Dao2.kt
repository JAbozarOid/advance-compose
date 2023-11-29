package com.example.data.source.db.dao

import com.example.data.source.db.model.DbTestModel

internal interface Dao2 {
    fun getData(): List<DbTestModel>
}