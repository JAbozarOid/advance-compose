package com.example.advancecompose.di

import com.example.advancecompose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Named

/**
 * we need to have access the base url which is defined in build.gradle.kts (app) in CoreNetworkModule
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    @Named("baseUrl")
    @ActivityRetainedScoped
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}