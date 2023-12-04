package com.example.advancecompose.compose.button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun iconButton() {
    IconButton(onClick = { }) {
        Icon(
            // painter = painterResource(id = R.drawable.ic_person_24),
            Icons.Filled.Refresh,
            contentDescription = "icon button",
            tint = Color.DarkGray,
            modifier = Modifier.size(80.dp)
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun iconButtonPreview() {
    iconButton()
}
