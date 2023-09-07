package com.example.advancecompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


class StateDemoActivity : ComponentActivity() {

    var count by mutableStateOf(0)

    /**
     * state in an application is, any value that can change over time
     *
     * jetpack compose has reactive approach in order to display changes of the state of the ui
     * state must be observed by the compose runtime -> we need to use mutable state, livedata,flow or rxjava for the state of the ui
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                showBtn(count) {
                    count = it + 1
                }
            }
        }
    }
}

/**
 * recomposition : changes of the state in ui must be observed by compose
 */
@Composable
fun showBtn(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = {
        updateCount(count)
    }) {
        Text(text = "the count num is : ${count}")
    }
}
