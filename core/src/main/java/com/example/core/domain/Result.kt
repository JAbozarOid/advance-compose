package com.example.core.domain

/**
 * domain layer help us to handle data from the server
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : com.example.core.domain.Result<T>()
    data class Error(val exception: Exception) : com.example.core.domain.Result<Nothing>()
}

