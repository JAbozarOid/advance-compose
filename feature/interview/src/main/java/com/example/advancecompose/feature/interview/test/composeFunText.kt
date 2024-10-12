package com.example.advancecompose.feature.interview.test

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * step by step
 * 1- compose test rule
 * 2- Snapshot Testing
 *      - Jetpack Compose supports snapshot testing using the ComposeTestRule.
 *      You can capture the current UI state and compare it with a previously stored snapshot to ensure consistency
 *      in visual output.
 */

@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!")
}

class GreetingTest {
    //@get:Rule
    //val composeTestRule = createComposeRule()

    //@Test
    fun testGreetingDisplay() {
        // Set the composable to be tested
       /* composeTestRule.setContent {
            Greeting(name = "Compose")
        }*/

        // Verify that the correct text is displayed
        //composeTestRule.onNodeWithText("Hello, Compose!").assertIsDisplayed()
    }
}
