package com.example.advancecompose.domain.repository

import com.example.advancecompose.data.remote.product.ProductApiService
import com.example.advancecompose.data.repository.ProductRepository
import com.example.advancecompose.domain.entity.Product
import com.example.core.domain.Result
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(private val productApiService: ProductApiService) :
    ProductRepository {
    override suspend fun fetchProducts(): Result<List<Product>> {
        return try {
            val response = productApiService.fetchProducts()
            val products = response.map {
                Product(
                    id = it.id.toString(),
                    title = it.title,
                    price = it.price,
                    imageUrl = it.image,
                    description = it.description
                )
            }
            Result.Success(products)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
