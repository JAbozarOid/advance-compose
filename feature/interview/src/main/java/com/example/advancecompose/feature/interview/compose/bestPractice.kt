package com.example.advancecompose.feature.interview.compose

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.example.advancecompose.feature.interview.kotlinUpperThan2.LazyColumn

// refer to compose cheat sheet on -> res/drawable/compose_cheat_sheet.jpg

// 1- @Immutable and @stable annotation for custom classes
@Immutable
data class ImmutableClass(val name: String, val age: Int) {

    // make all properties in constructor val and not var
}

@Stable
data class StableClass(val name: String, val age: Int)

@Stable
data class UserStory(val id: String, val title: String,val isSeen: Boolean)

// 2- avoid sorting or filtering during recomposition, move them to view model or cash them
@Composable
fun HeavyComposable(stories : List<UserStory>) {

    val unseenStories = remember(stories) {
        stories.filter { !it.isSeen }
    }

    LazyColumn {
        items(unseenStories) {
            StoryItem(story = it)
        }
    }
}

@Composable
fun StoryItem(story: UserStory) {

}