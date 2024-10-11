package com.example.advancecompose.feature.interview.kotlinUpperThan2

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 1- when guard
 */
sealed interface SearchResult {
    class Person(val isBlocked: Boolean) : SearchResult
}

data class Data(
    val item: String,
    val id: String
)

class SelectedSearchPanel {
    val isBlocked: Boolean = false
}

@Composable
fun DisplayLastSearchResultByPanelType(
    searchData: Sequence<Data>,
    id: String,
    searchResult: SearchResult
) {
    LazyColumn {
        val lastData = searchData.last {
            it.id == id
        }
        when {
            // && is guard and will be replace wi `if` in kotlin 2.1
            // there are two condition here
            searchResult is SearchResult.Person && searchResult.isBlocked -> {}
        }
    }
}

/**
 * 2- dataarg class
 * مثلا در کامپپوز ستسنگ column ما پدینگ های ثابتی داره یا مثلا همه column های ما از وسط بقیه ویجت ها رو میچینن
 * بعد ما یه lazy column  داریم که میتونیم از dataarg که برای ستینگ column نوشتیم در اون استفاده کنیم
 */

//  *** here this lazy column have some property which repeated
//  reverseLayout
//  verticalArrangement
//  horizontalAlignment
//  flingBehavior
//  userScrollEnabled
@Composable
fun LazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
) {

}

/**
 * This is where extensible data arguments come into play,
 * allowing us to move arguments with default values into a dataarg class,
 * simplifying the function signature and maintaining both source and binary compatibility:
 */
/*
dataarg class LazyColumnSettings(
    val contentPadding: PaddingValues = PaddingValues(0.dp),
    val reverseLayout: Boolean = false,
    val verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    val horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    val flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    val userScrollEnabled: Boolean = true,
)*/

@Composable
fun LazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    //dataarg settings: LazyColumnSettings, // *** here this is usage of LazyColumnSettings
    content: LazyListScope.() -> Unit
) {

}
