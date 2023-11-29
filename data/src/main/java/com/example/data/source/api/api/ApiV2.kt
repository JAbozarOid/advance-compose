package com.example.data.source.api.api

import com.example.data.source.api.model.ApiTestModel

/**
 * this should be extended to retrofit interface
 */
internal interface ApiV2 {
    fun getData() : List<ApiTestModel>
}