package com.example.advancecompose.feature.interview.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset

data class Contact(
    val id: Int = 0, val name: String
)

@Composable
fun ContactList(recentContacts: List<Contact>) {

    /**
     * # 1
     * there is a big mistake here
     * there is no key for each item
     * when we don't use key for items compose doesn't know your items
     * if the new contact added to the list,compose can not recognize previous items so compose will recompose all of the items
     */
    LazyColumn {
        items(
            items = recentContacts
        ) { contact ->
            ContactItem(name = contact.name)
        }
    }
}

/**
 * solution for the #1 mistake
 * add key for items
 */
@Composable
fun ContactListWithKeys(recentContacts: List<Contact>) {
    LazyColumn {
        items(items = recentContacts, key = { contact ->
            contact.id
        }) { contact ->
            ContactItem(contact.name)
        }
    }
}

@Composable
fun ContactItem(name: String) {
    Text(text = name)
}


/**
 * # 2
 * assignment in composition
 * it's better to think, when you write code inside a composable functions maybe it will be repeated for many times
 * so we want assignment happen just for one time, using launchEffect
 */
@Composable
fun Screen() {
    var shouldNavigate by remember {
        mutableStateOf(false)
    }


    Button(onClick = { shouldNavigate = true }) { }

    // solution #2 -> Unit means execute once the code inside of the LaunchedEffect Code
    // LaunchEffect will be execute once per each recomposition
    LaunchedEffect(Unit) {
        shouldNavigate = !shouldNavigate // assignment in composition
    }
}

/**
 * # 3
 * using remember correctly
 */
@Composable
fun FixedContactList(contacts: List<Contact>, modifier: Modifier) {

    // when contacts have no changes, the below line makes filter on list
    // filter will be run in every recomposition

    /**
     * # 3
     * solution is using remember for filter
     * if contacts changed, filter will be execute again
     */
    val filter = remember(contacts) {
        contacts.filter {
            it.name.startsWith("A")
        }
    }


    LazyColumn(modifier) {
        items(items = filter, key = { contact -> contact.id }) { contact ->
            ContactItem(name = contact.name)
        }
    }
}

/**
 * # 2
 * reading state early
 */
@Composable
fun TransactionHistory() {
    Box(modifier = Modifier.fillMaxSize()) {
        val scrollState = rememberScrollState(0)
        TitleBar("Home", scrollState.value) // scrollState.value can be passed as function
    }
}

@Composable
fun TitleBar(title: String, scroll: Int) {
    val offset = with(LocalDensity.current) { scroll.toDp() }

    Column(modifier = Modifier.offset(y = offset)) // " y = " assign in the functional way below
    {}
}

/**
 * solution for # 2
 * reading state can be function due to read state lately
 */
@Composable
fun TransactionHistorySolution() {
    Box(modifier = Modifier.fillMaxSize()) {
        val scrollState = rememberScrollState(0)
        TitleBarSolution("Home") { scrollState.value }
    }
}

@Composable
fun TitleBarSolution(title: String, scroll: () -> Int) {
    val offset = with(LocalDensity.current) { scroll.invoke().toDp() }

    Column(modifier = Modifier.offset {
        IntOffset(x = 0, y = scroll.invoke())
    }) {
        // ...
    }
}

/**
 * # 1
 * not using derived state of
 * derived state of receive a function to know how to produce a state
 * مادامی که فانکشن منجر به تغییر استیت نشه هیچ جیزی ریکامپوز نمیشه
 * derived state of : when a value inside of the derived state of is changed -> state will be changed
 */


@Preview
@Composable
fun ContactListPreview() {

    Column {
        ContactList(listOf(Contact(name = "Abozar"), Contact(name = "Shirin")))
        ContactListWithKeys(
            listOf(
                Contact(id = 0, name = "Abozar"), Contact(id = 1, name = "Shirin")
            )
        )
        FixedContactList(
            contacts = listOf(Contact(name = "Abozar"), Contact(name = "Reza")), modifier = Modifier
        )
    }
}