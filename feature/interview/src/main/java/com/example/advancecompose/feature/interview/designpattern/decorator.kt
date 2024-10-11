package com.example.advancecompose.feature.interview.designpattern

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.util.zip.GZIPInputStream

/**
 * اگر بخوایم به یک کلاس قابلیت های جدید اضافه کنیم بدون اینکه کدش و تغییر بدیم
 * اگر بخوایم از ترکیب قابلیت های متفاوت یک کلاس استفاده کنیم بدون اینکه بخوایم از اون کلاس ارث بری کنیم چه روشی وجود دارد
 */

/**
 * real example of decorator
 *  - java input stream and output stream
 *      - input stream
 *          - fun read()
 *          - FilterInputStream class
 *  - Compose Modifiers
 */

@Composable
fun DecoratorExample() {

    // compose modifier is a decorator pattern

    Text(
        text = "", modifier = Modifier
            .border(1.dp, Color.Blue)
            .background(Color(153, 49, 63))
            .padding(4.dp)
            .verticalScroll(
                rememberScrollState()
            )
    )

}

fun main() {

    // we can do many functions on this input stream
    val inputStream = GZIPInputStream(
        BufferedInputStream(
            FileInputStream("Abo.txt")
        )
    )
}

/**
 * another example is
 *  - we want to have a text view which have all the belows
 *      - has scrollable
 *      - has border
 */