package com.example.advancecompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ModernRepository
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ModernViewModel(
    private val repository: ModernRepository
) : ViewModel() {

    /**
     * fold provide two callbacks
     * success
     * fail
     */
    fun getData() {
        viewModelScope.launch {
            repository.getData().fold({ list ->
                for (element in list) {
                    Log.d("TAG", "getData: ${element.testData}")
                }
            }, { exception -> Log.e("TAG", "getData: ${exception.message}",exception) })
        }
    }
}