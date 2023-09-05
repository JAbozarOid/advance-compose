package com.example.advancecompose.compose.column

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.ui.theme.AdvanceComposeTheme


/**
 * to make a column be scrollable we need to do two things
 * 1- Get an instance of ScrollState
 * 2- Add VerticalScroll modifier
 */
@Composable
fun scrollableColumnDemo() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(state = scrollState)) {
        for (i in 1..100) {
            Text(
                text = "Username $i",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(10.dp)
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}
@Preview(showBackground = false)
@Composable
fun scrollableColumnPreview() {
    AdvanceComposeTheme {
        scrollableColumnDemo()
    }
}