package com.example.advancecompose.core.domain.datasource

import com.example.advancecompose.core.domain.model.user.UserModel


interface UserDataSource {
    suspend fun getUser(): UserModel

}