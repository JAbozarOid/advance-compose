package com.example.advancecompose.core.domain.repository

import com.example.advancecompose.core.domain.model.user.UserModel


interface UserRepository {
    suspend fun getUser(): UserModel

}