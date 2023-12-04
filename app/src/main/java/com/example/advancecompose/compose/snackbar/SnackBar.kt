package com.example.advancecompose.compose.snackbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

private const val TAG = "ABOZAR"

/**
 * in order to display snack bar we need to have two things
 * 1- A scaffold state : is a layout which implement the basic material structure
 * 2- A Coroutine scope
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun displaySnackBar() {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            scope.launch {
                val snackBarResult = snackbarHostState.showSnackbar(
                    message = "This is a snackbar message",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Long
                )
                when (snackBarResult) {
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
private fun snackBarPreview() {
    displaySnackBar()
}
