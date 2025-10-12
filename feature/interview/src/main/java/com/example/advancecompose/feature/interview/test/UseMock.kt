package com.example.advancecompose.feature.interview.test

class UseMock(val discountService: DiscountService) {


    fun calculateFinalPrice(price: Double): Double {
        return applyDiscount(price = price)
    }

    private fun applyDiscount(price: Double): Double {
        return discountService.getDiscountedPrice(price = price)
    }

}


interface DiscountService {
    fun getDiscountedPrice(price: Double) = price * 0.9
}