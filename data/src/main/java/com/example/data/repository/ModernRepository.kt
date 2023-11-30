package com.example.data.repository

import com.example.data.model.DataTestModel
import com.example.data.model.toEntity
import com.example.data.source.api.ApiSource
import com.example.data.source.datasource.DatastoreSource
import com.example.data.source.db.DbSource
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.flatMap
import com.github.kittinunf.result.map
import org.koin.core.annotation.Single

/**
 * repository can be used only in domain, not in view
 */
@Single
class ModernRepository internal constructor(
    private val apiSource: ApiSource,
    private val dbSource: DbSource,
    private val datastoreSource: DatastoreSource
) {

    /**
     * this method just combines the data from all the data sources.
     * The logic can be much more complex, for example, at first we may check the DB, if there is no
     * item there, we may try to call API
     *
     * Result library allows to create convenient chains of data
     *
     * here I map List<ApiTestModel> to list<TestModel>, then join this data with mapped
     * list<DbTestModel> to list<TestModel>, and then join it with the data from datastore source.
     * In this case, if any of these operations fails, the whole operation fail.
     * you may use mapError of flatMapError operators ti bypass this behaviour.
     */

    suspend fun getData(): Result<List<DataTestModel>, Exception> =
        apiSource.getData().map { list -> list.map { it.toEntity() } }
            .flatMap { fromApi ->
                dbSource.getData().map { list -> list.map { it.toEntity() } }
                    .map { fromDb -> fromApi + fromDb }
            }.map { fromApiAndDb ->
                fromApiAndDb + DataTestModel(testData = datastoreSource.getLastValue())
            }


}