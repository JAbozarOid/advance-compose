package com.example.advancecompose.feature.interview.coursera

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlin.times

/**
 * higher order function
 * A higher-order function is a function that has other functions as its parameter or return value.
 * Delegate responsibilities to the argument function
 */

@Composable
fun SimpleUI(onBtnClicked: () -> Unit) {
    Button(onClick = onBtnClicked) {
        Text("Show a Toast")
    }
}

/**
 * Android KTX: A library that adds higher-order functions to Android, enabling cleaner and more concise code.
 */
// val saveBln = sharedPreferences.edit().putBoolean("key", value).apply() // without android-ktx It creates a shared preferences editor, puts a Boolean value into it and then writes that changes to disk asynchronously.
// val saveBln = sharedPreferences .edit { putBoolean("key", value)} // with android-ktx --> apply is removed


fun greet(name: String, function: (name: String) -> Unit) {
    function(name)
}

fun print(name: String) {
    println("Hello $name")
}


fun main() {
    greet(name = "") {
        print(name = it)
    }
    greet("", ::print)

    val number = 2
    var output = 1
    repeat(3) { index ->
        output += (index * number)
    }
    println("the output is $output")

}
/**
 * The `repeat` function is a higher-order function in Kotlin that executes a given function a specified number of times, defined as `inline fun repeat(times: Int, action: (Int) -> Unit)`
 */

/**
 * collection processing : applying operations on elements in a collection
 * 1- Accessing elements of a collection on which the operation is to be applied.
 * 2- Defining and applying the operation on the element
 * 3- The value held in an optional cannot be directly accessed
 */

/**
 * map : The `map` function is a collection processing function that applies a transformation to each element of a collection, returning a new list of transformed elements.
 * map and Map are two separate entities. The former is a collection processing function, and the latter is a collection type.
 */
fun mapDefinition() {

    val orderAmount = 134
    val discountCouponList = listOf<DiscountCoupon>(
        DiscountCoupon(10, 150),
        DiscountCoupon(5, 75),
        DiscountCoupon(8, 100),
        DiscountCoupon(6, 90),
    )
    discountCouponList.map { discountCoupon ->
        if (orderAmount >= discountCoupon.minimumOrderAmount) {
            // todo : something
        } else {
            0
        }
    }
}

data class DiscountCoupon(
    val percentage: Int,
    val minimumOrderAmount: Int
)

/**
 * filter : The filter function is a collection processing function that returns elements from a collection that match a given predicate, which is a Boolean expression.
 * Iterable is an interface that is inherited by the Collection interface and hence by all collections such as List, Set etc. too
 */
data class Ingredient(
    val name: String,
    val stockUnits: Int
)

val ingredientList = listOf(
    Ingredient("Olive Oil", 7),
    Ingredient("Wheat", 3),
    Ingredient("Soda", 10),
    Ingredient("Egg", 0),
    Ingredient("Yeast", 2)
)
val ingredientsToRefill = ingredientList.filter { ingredient ->
    ingredient.stockUnits < 5
}

fun filterDefinitaion() {
    print(ingredientsToRefill)
}

/**
 * set
 */
data class Dish(
    val name: String,
    val ingredients: Set<String>
)

private val dished = listOf<Dish>(
    Dish("Fish and  chips", setOf("Cod", "chips", "oil", "Flour"))
)

/**
 * fold : The `fold` function is a collection processing function that accumulates a value by applying an operation to an initial value and each element from left to right.
 * The ‘fold’ function takes in two parameters:
 * 1- Initial value – This is used as the initial accumulator value.
 * 2- Operation function type
 */
data class OrderItem(
    val name: String,
    val amount: Int,
    val quantity: Int
)

val orderItemList = listOf(
    OrderItem("Burger", 6, 2),
    OrderItem("Fries", 2, 1),
    OrderItem("Soda", 3, 3)
)

// Now, calculate the total order amount using the fold function as:
val totalAmount = orderItemList.fold(0) { totalOrderAmount, orderItem ->
    totalOrderAmount + (orderItem.amount * orderItem.quantity)
}

fun foldDefinition() {
    print(totalAmount) // This will print 23
}

val diff: (Int, Int) -> Int = { x: Int, y: Int -> x - y }
fun display(): (Int) -> Unit {
    return TODO("Provide the return value")
}
