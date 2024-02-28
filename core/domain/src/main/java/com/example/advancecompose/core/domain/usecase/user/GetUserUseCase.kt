package com.example.advancecompose.core.domain.usecase.user

import com.example.advancecompose.core.domain.model.user.UserModel
import com.example.advancecompose.core.domain.repository.UserRepository
import com.example.advancecompose.core.domain.usecase.BaseUseCase
import javax.inject.Inject


class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseUseCase<Unit?, UserModel>() {
    override suspend fun execute(param: Unit?): UserModel {
        return repository.getUser()
    }
}