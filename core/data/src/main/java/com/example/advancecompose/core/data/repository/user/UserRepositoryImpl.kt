package com.example.advancecompose.core.data.repository.user

import com.example.advancecompose.core.domain.datasource.UserDataSource
import com.example.advancecompose.core.domain.model.user.UserModel
import com.example.advancecompose.core.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : UserRepository {
    override suspend fun getUser(): UserModel {
        return withContext(Dispatchers.IO) {
            delay(2000)
            dataSource.getUser()

        }
    }
}