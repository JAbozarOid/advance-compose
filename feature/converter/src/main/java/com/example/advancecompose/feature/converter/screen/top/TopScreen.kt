package com.example.advancecompose.feature.converter.screen.top

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.advancecompose.feature.converter.Conversion
import com.example.advancecompose.feature.converter.screen.input.InputBlock

@Composable
fun TopScreen(list: List<Conversion>) {
    val selectedConversion: MutableState<Conversion?> = remember {
        mutableStateOf(value = null)
    }
    val inputText: MutableState<String> = remember {
        mutableStateOf("")
    }
    ConversionMenu(list = list) {
        selectedConversion.value = it
    }
    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText)
    }
}