package com.example.domain.usecase

import com.example.data.repository.ModernRepository
import com.example.domain.model.TestModel
import com.example.domain.model.toEntity
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.map
import kotlinx.coroutines.delay
import org.koin.core.annotation.Factory

/**
 * UseCase gets the data and applies a complex logic on it
 */
@Factory
class GetDataWithLogicAppliedUseCase internal constructor(
    private val modernRepository: ModernRepository)  {

    suspend operator fun invoke() : Result<List<TestModel>,Exception> {
        val data = modernRepository.getData().map { data -> data.map { it.toEntity() } }
        delay(100L)

        return data
    }
}


//maybe warning rise