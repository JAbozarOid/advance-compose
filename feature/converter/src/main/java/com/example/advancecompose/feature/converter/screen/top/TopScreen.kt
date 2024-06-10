package com.example.advancecompose.feature.converter.screen.top

import androidx.compose.runtime.Composable
import com.example.advancecompose.feature.converter.Conversion

@Composable
fun TopScreen(list: List<Conversion>) {
    ConversionMenu(list = list)
}