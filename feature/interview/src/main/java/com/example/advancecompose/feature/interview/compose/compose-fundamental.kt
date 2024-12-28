package com.example.advancecompose.feature.interview.compose

/**
 * androidx.compose.material
 *      - material 2
 * androidx.compose.material3
 *      - material 2 or material you
 *
 * MutableState<T>
 *     - carry changeable values
 *
 * Recomposition
 *      - The process of updating a Compose UI is called recomposition
 *      - Certain types trigger a so-called recomposition
 *      - MutableState is such a type. If we change its value, the TextField() composable is redrawn or repainted
 *
 * remember & mutableStateOf
 *      - for creating and maintaining state
 *
 * state
 *     - state in an app refers to a value that can change over time
 *     - Values that change over time are called state
 *
 * State Hoisting
 *      - when a composable function create and remember state by invoking mutableStateOf and remember, and it passes to another composable function
 *
 * Difference between setContentView(binding.root) and setContent {}
 *      - a striking difference is that with Jetpack Compose, there is no need to maintain references to the UI component tree or individual elements of it
 *
 * Why you can set a composable function inside of a MainActivity
 *      - Jetpack Compose provides alternative means to access android.content.Context. You have already seen
 *        the stringResource() composable function, which is a replacement for getString()
 *
 * Android ui Development jetpack compose
 *      - page 19 book or 38 pdf for config compose for a project
 *
 * Compose-bom
 *      - bom : bill of material
 *
 * Composition over inheritance
 *      - This means the look and the behavior of a UI element (and
 *        the complete UI) are defined by combining simple building blocks, such as Container, Padding,
 *        Align, or GestureDetector, rather than modifying a parent
 */