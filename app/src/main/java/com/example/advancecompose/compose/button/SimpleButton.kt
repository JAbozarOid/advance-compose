package com.example.advancecompose.compose.button

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.ui.theme.AdvanceComposeTheme

@Composable
internal fun simpleButton() {
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
private fun simpleButtonPreview() {
    AdvanceComposeTheme {
        simpleButton()
    }
}
