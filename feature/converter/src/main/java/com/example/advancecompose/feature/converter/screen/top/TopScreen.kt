package com.example.advancecompose.feature.converter.screen.top

import android.icu.text.DecimalFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.advancecompose.feature.converter.Conversion
import com.example.advancecompose.feature.converter.screen.input.InputBlock
import com.example.advancecompose.feature.converter.screen.result.ResultBlock
import java.math.RoundingMode

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TopScreen(list: List<Conversion>, isLandscape: Boolean) {
    val selectedConversion: MutableState<Conversion?> = remember {
        mutableStateOf(value = null)
    }
    val inputText: MutableState<String> = remember {
        mutableStateOf("")
    }
    val typedValue = remember { mutableStateOf("0.0") }

    // make column scrollable
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ConversionMenu(list = list, isLandscape = isLandscape) {
            selectedConversion.value = it
        }
        selectedConversion.value?.let { conversion ->
            InputBlock(conversion = conversion, inputText = inputText, calculate = { input ->
                Log.i("ABOZAR", "TopScreen: user typed $input")
                typedValue.value = input
            }, isLandscape = isLandscape)
        }

        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value!!.multiplyBy
            val result = input * multiply

            val df = DecimalFormat("#.####")
            //df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val message1 =
                "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val message2 = "${roundedResult} ${selectedConversion.value!!.convertTo}"

            ResultBlock(message1 = message1, message2 = message2)
        }
    }
}