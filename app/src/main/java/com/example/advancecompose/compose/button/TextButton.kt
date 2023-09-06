package com.example.advancecompose.compose.button

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.advancecompose.ui.theme.AdvanceComposeTheme

@Composable
internal fun textButton() {
    TextButton(onClick = {}) {
        Text(text = "Text Button")
    }
}

@Preview(showBackground = false)
@Composable
private fun textButtonPreview() {
    AdvanceComposeTheme {
        textButton()
    }
}