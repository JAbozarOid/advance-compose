package com.example.advancecompose.feature.converter.screen.input

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.advancecompose.feature.converter.Conversion

@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    calculate: (String) -> Unit,
    isLandscape: Boolean,
    context: Context = LocalContext.current
) {

    if (isLandscape) {
        Column(
            modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Row() {
                TextField(
                    value = inputText.value, onValueChange = {
                        inputText.value = it
                    },
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

                )
            }
            Spacer(modifier = modifier.height(20.dp))
            OutlinedButton(
                onClick = {
                    if (inputText.value != "") {
                        calculate(inputText.value)
                    } else {
                        Toast.makeText(context, "please enter your value", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
            ) {
                Text(
                    text = "Convert",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }
        }
    } else {
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
            Spacer(modifier = modifier.height(20.dp))
            OutlinedButton(onClick = {
                if (inputText.value != "") {
                    calculate(inputText.value)
                } else {
                    Toast.makeText(context, "please enter your value", Toast.LENGTH_SHORT).show()
                }
            }, modifier = modifier.fillMaxWidth(1F)) {
                Text(
                    text = "Convert",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewInputBlock() {
    var inputText: MutableState<String> = remember {
        mutableStateOf("input value")
    }
    InputBlock(
        conversion = Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        inputText = inputText,
        calculate = {},
        isLandscape = false
    )
}