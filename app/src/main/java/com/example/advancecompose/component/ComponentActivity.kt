package com.example.advancecompose.component

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.R
import com.example.advancecompose.ui.theme.AdvanceComposeTheme
import kotlinx.coroutines.launch

private const val TAG = "ABOZAR"
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}

@Composable
private fun boxSample(modifier: Modifier) {
    Box(
        modifier
            .background(color = Color.Green)
            .size(100.dp, 150.dp)

    ) {

        Box(
            modifier
                .background(color = Color.Yellow)
                .size(125.dp, 100.dp)
                .align(Alignment.BottomEnd)
        ) {
            Column {
                showImage()
                showButton()
            }

        }

        Text(text = "Abozar")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxPreview() {
    AdvanceComposeTheme {
        boxSample(modifier = Modifier)
    }
}

@Composable
private fun showImage() {
    Image(
        painter = painterResource(id = R.drawable.ar),
        contentDescription = "",
        modifier = Modifier.size(50.dp, 50.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ImagePreview() {
    AdvanceComposeTheme {
        showImage()
    }
}

@Composable
private fun showButton() {
    Button(
        onClick = {}, colors = ButtonDefaults.textButtonColors(contentColor = Color.Blue),
        shape = CircleShape,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Magenta,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(text = "Abozar")
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    AdvanceComposeTheme {
        showButton()
    }
}

@Composable
private fun simpleButton() {
    val context = LocalContext.current
    Button(
        onClick = { Toast.makeText(context, "Button 1", Toast.LENGTH_SHORT).show() },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Color.Black),
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.White,
        ),
        shape = CutCornerShape(10.dp)
    ) {
        Text(text = "Button 1")
    }
}

@Preview(showBackground = false)
@Composable
fun simpleButtonPreview() {
    AdvanceComposeTheme {
        simpleButton()
    }
}

@Composable
fun textButton() {
    TextButton(onClick = {}) {
        Text(text = "Text Button")
    }
}

@Preview(showBackground = false)
@Composable
fun textButtonPreview() {
    AdvanceComposeTheme {
        textButton()
    }
}

@Composable
fun outlineButton() {
    OutlinedButton(onClick = {}) {
        Text(text = "Outline Button")
    }
}

@Preview(showBackground = false)
@Composable
fun outlineButtonPreview() {
    AdvanceComposeTheme {
        outlineButton()
    }
}

@Composable
fun iconButton() {
    IconButton(onClick = { }) {
        Icon(
            //painter = painterResource(id = R.drawable.ic_person_24),
            Icons.Filled.Refresh,
            contentDescription = "icon button",
            tint = Color.DarkGray,
            modifier = Modifier.size(80.dp)
        )
    }
}

@Preview(showBackground = false)
@Composable
fun iconButtonPreview() {
    iconButton()
}

/**
 * in order to display snack bar we need to have two things
 * 1- A scaffold state : is a layout which implement the basic material structure
 * 2- A Coroutine scope
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun displaySnackBar() {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost (snackbarHostState)}, modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            scope.launch {
                val snackBarResult = snackbarHostState.showSnackbar(
                    message = "This is a snackbar message",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Long
                )
                when(snackBarResult) {
                    SnackbarResult.Dismissed -> Log.i(TAG, "dismissed")
                    SnackbarResult.ActionPerformed -> Log.i(TAG, "action label clicked")
                }
            }
        }) {

            Text(text = "Display Snackbar")
        }
    }
}

@Preview(showBackground = false)
@Composable
fun snackBarPreview() {
    displaySnackBar()
}

