package com.example.advancecompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SampleViewModel : ViewModel() {

    internal var count by mutableStateOf(0)

    fun increaseCount() {
        count++
    }
}
