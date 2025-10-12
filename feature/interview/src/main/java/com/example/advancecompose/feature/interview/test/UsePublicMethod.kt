package com.example.advancecompose.feature.interview.test

/**
 * first approach for testing private methods is using private method inside of the public method
 */
class UsePublicMethod {

    fun calculateFinalPrice(originalPrice : Double) : Double {
        return applyDiscount(price = originalPrice)
    }

    // suppose we want to write test for this private method
    private fun applyDiscount(price : Double) : Double{
        return price * 0.9
    }
}