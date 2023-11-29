package com.example.data.model

import com.example.data.source.api.model.ApiTestModel
import com.example.data.source.db.model.DbTestModel

// TestModel is public so it expose to view
data class TestModel(val testData: String)

//mappers --> they are internal it means they can not be expose so the leak is not technically possible
internal fun ApiTestModel.toEntity() = TestModel(testData = testData)

internal fun DbTestModel.toEntity() = TestModel(testData = testData)
