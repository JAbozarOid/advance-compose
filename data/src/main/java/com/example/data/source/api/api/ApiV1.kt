package com.example.data.source.api.api

import com.example.data.source.api.model.ApiTestModel

internal interface ApiV1 {

    fun getData() : List<ApiTestModel>
}