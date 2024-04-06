package com.example.mark2.helper

import androidx.compose.ui.graphics.Color

object ColorHelper {

    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }

}