package com.example.advancecompose.data.remote.product

import retrofit2.http.GET

internal interface ProductApiService {

    @GET("products")
    suspend fun fetchProducts(): ProductListResponse
}
