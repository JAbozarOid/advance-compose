package com.example.advancecompose.compose.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.R
import com.example.advancecompose.ui.theme.AdvanceComposeTheme

@Composable
internal fun showImage() {
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