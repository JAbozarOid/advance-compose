package com.example.data.source.api

import com.example.data.source.api.api.ApiV1
import com.example.data.source.api.api.ApiV2
import com.example.data.source.api.model.ApiTestModel
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import org.koin.core.annotation.Single

@Single
internal class ApiSource(
    private val apiV1: ApiV1,
    private val apiV2: ApiV2
) {
    /**
     * Result.failure must be implemented if something went wrong
     */

    // Result is a library --> Result<out v, out e>
    suspend fun getData(): Result<List<ApiTestModel>, Exception> {

        delay(1000L) //simulate network request

        return Result.success(apiV1.getData() + apiV2.getData() + ApiTestModel("api0"))
    }

}