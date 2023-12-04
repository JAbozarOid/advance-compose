package com.example.advancecompose.data.repository

import com.example.advancecompose.domain.entity.Product
import com.example.core.domain.Result

internal interface ProductRepository {
    suspend fun fetchProducts(): Result<List<Product>>
}
