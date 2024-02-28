package com.example.advancecompose.core.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<PARAM, RESULT>(
    var executeDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(param: PARAM): RESULT {
        return withContext(executeDispatcher) {
            execute(param = param)
        }
    }

    protected abstract suspend fun execute(param: PARAM): RESULT

}