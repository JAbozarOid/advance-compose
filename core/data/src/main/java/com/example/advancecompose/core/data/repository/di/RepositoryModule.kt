package com.example.advancecompose.core.data.repository.di

import com.example.advancecompose.core.data.repository.user.UserRepositoryImpl
import com.example.advancecompose.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

}