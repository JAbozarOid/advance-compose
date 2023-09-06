package com.example.advancecompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.R
import com.example.advancecompose.compose.box.boxSample
import com.example.advancecompose.compose.button.iconButton
import com.example.advancecompose.compose.button.outlineButton
import com.example.advancecompose.compose.button.simpleButton
import com.example.advancecompose.compose.button.textButton
import com.example.advancecompose.compose.snackbar.displaySnackBar
import com.example.advancecompose.ui.theme.AdvanceComposeTheme

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

