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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.advancecompose.activity.ui.theme.AdvanceComposeTheme
import com.example.advancecompose.ui.home.HomeScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * inorder to use flow with compose we need to get flow as state
         */
        /*setContent {
            HomeScreen(onNavigationRequested = {})
        }*/
    }
}
