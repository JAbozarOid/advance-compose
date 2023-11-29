package com.example.data.source.api.model

/**
 * this is a model that should be used only in 'api' datasource.
 * In may differ from TestModel or may contain some additions like @SerializedName for GSON parsing
 */
internal data class ApiTestModel(val testData : String)
