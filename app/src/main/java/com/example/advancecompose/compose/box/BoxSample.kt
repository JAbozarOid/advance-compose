package com.example.advancecompose.compose.box

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.compose.image.showImage
import com.example.advancecompose.ui.theme.AdvanceComposeTheme

@Composable
internal fun boxSample(modifier: Modifier) {
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
private fun showButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.textButtonColors(contentColor = Color.Blue),
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
