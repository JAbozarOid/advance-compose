package com.example.data.source.db.dao

import com.example.data.source.db.model.DbTestModel

// this should be extended to Room DAO interface
internal interface Dao1 {
    fun getData() : List<DbTestModel>
}