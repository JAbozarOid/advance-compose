package com.example.advancecompose.feature.converter.screen

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.advancecompose.feature.converter.ConverterViewModel
import com.example.advancecompose.feature.converter.screen.history.HistoryScreen
import com.example.advancecompose.feature.converter.screen.top.TopScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ConverterView(
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel()
) {

    val conversionList = converterViewModel.getConversions()

    //handle orientation
    val configuration = LocalConfiguration.current
    var isLandscape by remember {
        mutableStateOf(false)
    }
    when (configuration.orientation) {

        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandscape = true
            Row(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(conversionList, isLandscape)
                Spacer(modifier = modifier.width(10.dp))
                HistoryScreen()
            }
        }

        Configuration.ORIENTATION_PORTRAIT -> {
            isLandscape = false
            Column(
                modifier = modifier.padding(30.dp)
            ) {
                TopScreen(conversionList, isLandscape)
                Spacer(modifier = modifier.height(20.dp))
                HistoryScreen()
            }
        }

        Configuration.ORIENTATION_SQUARE -> {
            TODO()
        }

        Configuration.ORIENTATION_UNDEFINED -> {
            TODO()
        }

        else -> {

        }
    }
}