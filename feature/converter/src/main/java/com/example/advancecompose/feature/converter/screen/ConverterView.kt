package com.example.advancecompose.feature.converter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.advancecompose.feature.converter.ConverterViewModel
import com.example.advancecompose.feature.converter.screen.history.HistoryScreen
import com.example.advancecompose.feature.converter.screen.top.TopScreen

@Composable
fun ConverterView(
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel()
) {

    val conversionList = converterViewModel.getConversions()

    Column(
        modifier = modifier.padding(30.dp)
    ) {
        TopScreen(conversionList)
        Spacer(modifier = modifier.height(20.dp))
        HistoryScreen()
    }
}