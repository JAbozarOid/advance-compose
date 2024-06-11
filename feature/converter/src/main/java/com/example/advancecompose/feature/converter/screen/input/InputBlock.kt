package com.example.advancecompose.feature.converter.screen.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.advancecompose.feature.converter.Conversion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            TextField(
                value = inputText.value, onValueChange = {
                    inputText.value = it
                },
                modifier = modifier.fillMaxWidth(0.65F),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Cyan
                ),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp)
            )

            Text(
                text = conversion.convertFrom,
                fontSize = 24.sp,
                modifier = modifier
                    .padding(10.dp, 30.dp, 0.dp, 0.dp)
                    .fillMaxWidth(
                        0.35F
                    )
            )
        }
    }
}

@Preview
@Composable
fun PreviewInputBlock() {
    var inputText = mutableStateOf("input text")
    InputBlock(
        conversion = Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        inputText = inputText
    )
}