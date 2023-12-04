package com.example.advancecompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.advancecompose.activity.BoxActivity
import com.example.advancecompose.activity.FlowActivity
import com.example.advancecompose.activity.RecyclerActivity
import com.example.advancecompose.activity.StateDemoActivity
import com.example.advancecompose.activity.HomeActivity
import com.example.advancecompose.ui.home.HomeScreen
import com.example.advancecompose.ui.navigation.MainNavigation
import com.example.advancecompose.ui.theme.AdvanceComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*setContent {
            Column(
                modifier = Modifier
                    .background(Color(R.color.red))
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ShowText(name = "Column 1")
                ShowText(name = "Column 2")
                ShowText(name = "Column 3")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShowText(name = "Row 1")
                    ShowText(name = "Row 2")
                }

                NavigateToComponent()
                NavigateToRecycler()
                NavigateToStateActivity()
                NavigateToFlowActivity()
                NavigateToHomeScreen()
            }
        }*/

        setContent {
            /*Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainNavigation(navHostController = rememberNavController())
            }*/
            HomeScreen(onNavigationRequested = {})
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
private fun ShowText(name: String) {
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

@Composable
private fun NavigateToComponent() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, BoxActivity::class.java))
    }) {
        Text(text = "Navigate to Component Activity")
    }
}

@Composable
private fun NavigateToRecycler() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, RecyclerActivity::class.java))
    }) {
        Text(text = "Navigate to Recycler Activity")
    }
}

/**
 * In Compose there are three different ways to arrange elements
 * column
 * row
 * box
 */

@Preview(showBackground = true)
@Composable
fun TextPreview() {
    AdvanceComposeTheme {
        ShowText("Abozar")
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    AdvanceComposeTheme {
        NavigateToComponent()
    }
}

@Preview(name = "navigateToStateActivity")
@Composable
private fun NavigateToStateActivity() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, StateDemoActivity::class.java))
    }) {
        Text("navigate to state activity")
    }
}

@Composable
private fun NavigateToFlowActivity() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, FlowActivity::class.java))
    }) {
        Text(text = "Navigate to Flow Activity")
    }
}

@Composable
private fun NavigateToHomeScreen() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }) {
        Text(text = "Navigate to Home Activity")
    }
}
