package com.example.advancecompose.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancecompose.R
import com.example.advancecompose.ui.theme.AdvanceComposeTheme
import java.nio.file.WatchEvent

class BoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            boxSample(modifier = Modifier)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}

@Composable
private fun boxSample(modifier: Modifier) {
    Box(
        modifier
            .background(color = Color.Green)
            .size(100.dp, 150.dp)

    ) {

        Box(
            modifier
                .background(color = Color.Red)
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

@Composable
private fun showImage() {
    Image(
        painter = painterResource(id = R.drawable.ar),
        contentDescription = "",
        modifier = Modifier.size(50.dp, 50.dp)
    )
}

@Composable
private fun showButton() {
    Button(
        onClick = {}, colors = ButtonDefaults.textButtonColors(contentColor = Color.Blue),
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
fun BoxPreview() {
    AdvanceComposeTheme {
        boxSample(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun ImagePreview() {
    AdvanceComposeTheme {
        showImage()
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    AdvanceComposeTheme {
        showButton()
    }
}

