package com.example.advancecompose.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object ButtonDefaultValues {
    const val DisabledOutlineButtonBorderAlpha = 0.12f
    val OutlineButtonBorderWidth = 1.dp
}

@Composable
fun LoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun CreateAccountButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(
            width = ButtonDefaultValues.OutlineButtonBorderWidth,
            color = if (enabled) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface.copy(
                alpha = ButtonDefaultValues.DisabledOutlineButtonBorderAlpha
            )
        ),
        contentPadding = contentPadding,
        content = content
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewLoginButton() {
    LoginButton(
        onClick = {},
        enabled = true,
        contentPadding = PaddingValues(8.dp),
        content = { Text("Test Login Button", style = TextStyle(color = Color.White)) })
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginOutlineButton() {
    CreateAccountButton(
        onClick = {},
        enabled = true,
        contentPadding = PaddingValues(8.dp),
        content = { Text("Test Create Account Button", style = TextStyle(color = Color.White)) })


}