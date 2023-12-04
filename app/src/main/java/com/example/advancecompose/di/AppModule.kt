package com.example.advancecompose.di

import com.example.advancecompose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * we need to have access the base url which is defined in build.gradle.kts (app) in CoreNetworkModule
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}
