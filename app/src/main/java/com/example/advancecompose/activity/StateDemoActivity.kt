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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

val count = mutableStateOf(0)

class StateDemoActivity : ComponentActivity() {

    companion object {
    }

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
                showBtn()
            }
        }
    }
}

/**
 * recomposition : changes of the state in ui must be observed by compose
 */
@Preview(name = "showBtn")
@Composable
fun showBtn() {
    Button(onClick = {
        count.value = count.value + 1
    }) {
        Text(text = "the count num is : ${count.value}")
    }
}
