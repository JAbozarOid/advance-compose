package com.example.advancecompose.ui.navigation

import com.example.advancecompose.domain.entity.Product
import com.example.advancecompose.util.Constants.MAIN_APP
import com.example.advancecompose.util.Constants.PRODUCT
import com.example.core.util.JSONProvider

/**
 * create composable functions for each screens
 * these composable will be used to build the ui for the screen
 * to connect the routes and composable functions we use nag graph builder, this is a extension function that takes the app controller as an argument (MainNavGraph)
 */
internal sealed class Route(val route: String) {

    object Root : Route(MAIN_APP)
    object Home : Route("$MAIN_APP/home")

    object ProductDetail : Route("$MAIN_APP/product-details?$PRODUCT={$PRODUCT}") {
        fun createRoute(product: Product) = "$MAIN_APP/product-details?$PRODUCT={${
        JSONProvider.toJson(
            product,
            Product::class.java
        )
        }}"
    }
}
