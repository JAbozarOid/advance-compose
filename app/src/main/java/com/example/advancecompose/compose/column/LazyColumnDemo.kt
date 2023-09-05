package com.example.advancecompose.compose.column

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
 * the equivalent of the recycler view in compose is Lazy Column
 * to implement click listener we need to do two things
 * 1- add lambda expression as a parameter to the composable
 * 2- use clickable modifier to pass the selected items
 */
@Composable
fun lazyColumnDemo(selectedIem: (String) -> Unit) {
    LazyColumn() {
        items(100) {
            Text(
                text = "Username $it",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { selectedIem("$it selected") }
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

@Preview(showBackground = false)
@Composable
fun lazyColumnPreview() {
    AdvanceComposeTheme {
        lazyColumnDemo {

        }
    }
}