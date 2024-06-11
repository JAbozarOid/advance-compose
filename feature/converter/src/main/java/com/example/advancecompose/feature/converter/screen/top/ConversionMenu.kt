package com.example.advancecompose.feature.converter.screen.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.advancecompose.feature.converter.Conversion
import com.example.advancecompose.feature.converter.ConverterViewModel

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit
) {

    // when the state of a parameter change it means the value is changed, during recomposition remember helps us
    // to get the new value and not the initialize value, remember update the value
    // -- by is delegate, instead of using = sign
    var displayingText by remember {
        mutableStateOf("Select the conversion type")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero) // to assign the dropdown the same width as TextField
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        // drop down menu in compose is associate with text field
        OutlinedTextField(
            value = displayingText, onValueChange = {
                displayingText = it
            },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { cordinates ->
                    textFieldSize = cordinates.size.toSize()
                },
            label = { Text(text = "Conversion Type") },
            trailingIcon = {
                Icon(icon, contentDescription = "icon",
                    modifier.clickable {
                        expanded = !expanded
                    })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
            },
            modifier = modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(text = {
                    Text(
                        text = conversion.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }, onClick = {
                    displayingText = conversion.description
                    expanded = false
                    convert(conversion)
                })

            }
        }
    }

}

@Preview
@Composable
fun PreviewConversionMenu(
    converterViewModel: ConverterViewModel = viewModel()
) {

    ConversionMenu(list = converterViewModel.getConversions()){

    }
}
