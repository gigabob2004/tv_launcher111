package com.example.tvlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.*
import com.example.tvlauncher.ui.LauncherScreen
import com.example.tvlauncher.ui.TvLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvLauncherTheme {
                LauncherScreen()
            }
        }
    }
}