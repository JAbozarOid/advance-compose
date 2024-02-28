package com.example.advancecompose.feature.login.screen

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.advancecompose.core.designsystem.component.CreateAccountButton
import com.example.advancecompose.core.designsystem.component.LoadingWheel
import com.example.advancecompose.core.designsystem.component.LoginButton
import com.example.advancecompose.feature.login.model.LoginAction
import com.example.advancecompose.feature.login.model.LoginEvent
import com.example.advancecompose.feature.login.model.LoginRedirection
import com.example.advancecompose.feature.login.model.LoginViewState
import com.example.advancecompose.feature.login.viewmodel.LoginViewModel

@Composable
internal fun LoginView(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit,
    onBackClicked: () -> Unit,
    showSnack: suspend (message: String) -> Unit
) {

    val state by viewModel.viewStateFlow.collectAsStateWithLifecycle(initialValue = viewModel.initialState)

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collect {
            when (it) {
                is LoginEvent.CreateAccountEvent -> {
                    showSnack.invoke(it.user.name)
                }
            }
        }
    }

    val loginClicked = {
        viewModel.process(LoginAction.LoginClicked)
    }
    val createAccountClicked = {
        viewModel.process(LoginAction.CreateAccountClicked)
    }
    LoginScreen(
        modifier,
        state,
        onLoginClicked = loginClicked,
        onCreateAccountClicked = createAccountClicked,
        onBackClicked = onBackClicked
    )

}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun LoginScreen(
    modifier: Modifier,
    state: LoginViewState,
    onLoginClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    if (state.showContent) {
        ShowLoginContent(
            modifier,
            state.user?.name ?: "",
            onLoginClicked = onLoginClicked,
            onCreateAccountClicked = onCreateAccountClicked,
            onBackClicked = onBackClicked
        )
    }
    if (state.waitingFor != LoginRedirection.NOTHING) {
        ShowRedirectingWaiting(
            modifier,
            isWaitingForCreateAccount = state.waitingFor == LoginRedirection.CREATE_ACCOUNT,
            isWaitingForLogin = state.waitingFor == LoginRedirection.LOGIN
        )
    }
}

@Composable
fun ShowRedirectingWaiting(
    modifier: Modifier,
    isWaitingForCreateAccount: Boolean,
    isWaitingForLogin: Boolean
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(10.dp)
        ) {

            IconButton(
                onClick = { },
                enabled = !(isWaitingForLogin or isWaitingForCreateAccount)
            ) {
                Icon(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "login back button",
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            )

            ProvideTextStyle(value = MaterialTheme.typography.headlineLarge) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Center,
                    text = "greeting"
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )

            ProvideTextStyle(value = MaterialTheme.typography.bodyLarge) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Center,
                    text = "loginToYourAccount"
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {

            LoginButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {},
                enabled = false
            ) {

                if (isWaitingForLogin)
                    LoadingWheel(contentDesc = "waiting for login")
                else Text(text = "Login")

            }

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )


            CreateAccountButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { },
                enabled = true
            ) {
                if (isWaitingForCreateAccount)
                    LoadingWheel(contentDesc = "waiting for create account")
                else
                    Text(text = "Create Account", style = TextStyle(color = Color.White))

            }

            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
            )

        }
    }


}

@Composable
fun ShowLoginContent(
    modifier: Modifier = Modifier,
    userName: String,
    onLoginClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(10.dp)
        ) {

            IconButton(onClick = onBackClicked) {
                Icon(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "login back button",
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            )

            ProvideTextStyle(value = MaterialTheme.typography.headlineLarge) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Center,
                    text = "greeting"
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )

            ProvideTextStyle(value = MaterialTheme.typography.bodyLarge) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Center,
                    text = userName
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {

            LoginButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onLoginClicked,
                content = { Text(text = "Login") }
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )


            CreateAccountButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onCreateAccountClicked,
                enabled = true
            ) {
                Text(text = "Create Account")
            }

            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    Column {
        ShowLoginContent(onLoginClicked = { }, userName = "", onCreateAccountClicked = { }) {}
    }
}

@Preview
@Composable
fun PreviewRedirect() {
    Column {
        ShowRedirectingWaiting(modifier = Modifier, true, true)
    }
}
