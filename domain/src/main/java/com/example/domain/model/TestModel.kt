package com.example.domain.model

import com.example.data.model.DataTestModel

/**
 * TestModel class can be expose to the view
 */
data class TestModel(
    val testData: String
)

internal fun DataTestModel.toEntity() = TestModel(testData = testData)
