package com.example.advancecompose.core.ui.entity

sealed class DisplayedError(val errorMessage : String) {

    class SnackBarError(message : String) : DisplayedError(errorMessage = message)

    class ToastError(message: String) : DisplayedError(errorMessage = message)
}