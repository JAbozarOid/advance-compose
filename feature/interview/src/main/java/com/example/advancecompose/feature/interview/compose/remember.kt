package com.example.advancecompose.feature.interview.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * the key in lazy column, means it seams the key knows each item of the list Where to put the memory
 */
/**
 * normal remember scope is bind to the current configuration
 * remember savable give us a saver for saving data in configuration
 * remember is live in recomposition, it means when you write a conditional code
 *  for a remember which not run, it means that remember will works when that composable start from first
 */
/**
 * each time remember remove previous memory
 * each time using remember ask yourself it should be in the composable function or in viewModel
 */

// sometimes we need to set a value we use mutableStateOf`
// mutableStateOf is a delegate, it's something like a class and this class has getter and setter
// remember create an instance of this class and always pass that instance of that class to you
@Composable
fun DelegatedCounter() {
    var count by remember { mutableIntStateOf(0) }
    var text by remember { mutableStateOf("") }


    val test = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { count++ }) {
            Text("Count : $count")
            Text("Count : ${test.value}")
        }
        count =
            test.value.toInt() // if you define by remember like "count" you can set and get directly
        test.value =
            count.toString() // if you define "= remember" like "test" you can set and get by ".value"

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text") }
        )
    }
}

/**
 * difference between mutableStateOf and derivedStateOf
 * mutableStateOf
 *  - Usage: Use it when you have a value that needs to be updated and observed directly.
 *  - Whenever the state changes, all the Composables that are reading this state are recomposed.
 * derivedStateOf
 *  - Usage: Use it when you have a state that depends on other states and you want to optimize recompositions by avoiding unnecessary calculations
 *  - derivedStateOf will only recompute its value when the states it depends on change, and recomposition will only occur if the derived state itself changes.
 */
@Composable
fun Different() {
    var fName by remember { mutableStateOf("abozar") }
    var lName by remember { mutableStateOf("raghib") }
    val family = remember { derivedStateOf { "$fName $lName" } }
}

@Preview
@Composable
fun PreviewDelegateCounter() {
    DelegatedCounter()
}