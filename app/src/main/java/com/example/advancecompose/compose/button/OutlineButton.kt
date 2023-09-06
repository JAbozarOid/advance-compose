package com.example.advancecompose.compose.button

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.advancecompose.ui.theme.AdvanceComposeTheme


@Composable
internal fun outlineButton() {
    OutlinedButton(onClick = {}) {
        Text(text = "Outline Button")
    }
}

@Preview(showBackground = false)
@Composable
private fun outlineButtonPreview() {
    AdvanceComposeTheme {
        outlineButton()
    }
}
