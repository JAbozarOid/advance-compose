package com.example.advancecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.advancecompose.viewmodel.MainViewModel
import com.example.advancecompose.ui.EvaApp
import com.example.advancecompose.model.MainViewState
import com.example.advancecompose.model.MainAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {


    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var uiState: MainViewState by mutableStateOf(viewModel.initialState)

        lifecycleScope.launch {
            viewModel.process(MainAction.ViewCreated)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewStateFlow.onEach {
                    uiState = it
                }.collect()
            }
        }

        enableEdgeToEdge()
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT,
                ),
                navigationBarStyle = SystemBarStyle.auto(
                    lightScrim = lightScrim,
                    darkScrim = darkScrim
                )
            )
            EvaApp(windowSizeClass = calculateWindowSizeClass(this))
        }
    }


    /**
     * The default light scrim, as defined by androidx and the platform:
     * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
     */
    private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

    /**
     * The default dark scrim, as defined by androidx and the platform:
     * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
     */
    private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)


}

private inline fun test(data: () -> Unit) {

}