package com.example.advancecompose.core.data.datasource.di

import com.example.advancecompose.core.data.datasource.user.UserDataSourceImpl
import com.example.advancecompose.core.domain.datasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindUserDataSource(userDataSource: UserDataSourceImpl): UserDataSource

}