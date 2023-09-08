package com.example.advancecompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.advancecompose.activity.ui.theme.AdvanceComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sampleFlow = flow<Int> {
            for (i in 0..100) {
                emit(i)
                delay(1000)
            }
        }

        /**
         * inorder to use flow with compose we need to get flow as state
         */
        setContent {
            val currentValue = sampleFlow.collectAsState(initial = 1).value
            AdvanceComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    getFlowValue(text = "Current index is : ", currentValue = currentValue)
                }
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

@Composable
fun getFlowValue(text: String, currentValue: Int) {
    Text("$text $currentValue")
}