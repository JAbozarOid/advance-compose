package com.example.data.source.db

import com.example.data.source.db.dao.Dao1
import com.example.data.source.db.dao.Dao2
import com.example.data.source.db.model.DbTestModel
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import org.koin.core.annotation.Single

@Single
internal class DbSource(
    private val dao1: Dao1,
    private val dao2: Dao2
) {

    /**
     * Result.failure must be implemented if something went wrong
     */

    // Result is a library --> Result<out v, out e>
    suspend fun getData(): com.github.kittinunf.result.Result<List<DbTestModel>,Exception> {
        delay(100L)

        return Result.success(
            dao1.getData() + dao2.getData() + DbTestModel(testData = "db0")
        )
    }

}