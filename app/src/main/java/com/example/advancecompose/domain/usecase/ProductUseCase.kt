package com.example.advancecompose.domain.usecase

import com.example.advancecompose.data.repository.ProductRepository
import com.example.advancecompose.domain.entity.Product
import com.example.core.domain.Result
import com.example.core.domain.UseCase
import com.example.core.util.DispatcherProvider
import javax.inject.Inject

internal class ProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    UseCase<Unit, Result<List<Product>>>(dispatcher = dispatcherProvider.io()) {
    override suspend fun execute(p: Unit): Result<List<Product>> {
        return productRepository.fetchProducts()
    }
}