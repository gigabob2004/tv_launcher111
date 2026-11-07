package com.example.tvlauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import com.example.tvlauncher.ui.LauncherScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                LauncherScreen()
            }
        }
    }
}