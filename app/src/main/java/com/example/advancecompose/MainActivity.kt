package com.example.advancecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.advancecompose.ui.theme.AdvanceComposeTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .background(Color(R.color.red))
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                showText(name = "Column 1")
                showText(name = "Column 2")
                showText(name = "Column 3")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    showText(name = "Row 1")
                    showText(name = "Row 2")
                }
            }


        }
    }
}

/**
 * modifier allow us defied how composable should be presented
 * 1- behavior : clickable, draggable..
 * 2- information
 * It's better to add modifier from the method parameters
 */
@Composable
fun showText(name: String) {
    Text(
        text = "Hello $name!",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color(R.color.red),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(Color(R.color.teal_700))
            .border(2.dp, color = Color.Yellow)
            .padding(8.dp)
//            .fillMaxWidth(fraction = 1f)
    )
}

/**
 * In Compose there are three different ways to arrange elements
 * column
 * row
 * box
 */

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdvanceComposeTheme {
        showText("Abozar")
    }
}