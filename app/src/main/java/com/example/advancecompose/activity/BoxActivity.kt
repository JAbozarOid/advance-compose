package com.example.advancecompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.advancecompose.compose.box.boxSample
import com.example.advancecompose.compose.button.iconButton
import com.example.advancecompose.compose.button.outlineButton
import com.example.advancecompose.compose.button.simpleButton
import com.example.advancecompose.compose.button.textButton
import com.example.advancecompose.compose.snackbar.displaySnackBar

class BoxActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(modifier = Modifier.background(color = Color.DarkGray)) {
                boxSample(modifier = Modifier)

                // showing buttons
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.LightGray),
                ) {
                    simpleButton()
                    textButton()
                    outlineButton()
                    iconButton()
                    displaySnackBar()
                }
            }
        }
    }
}
