package com.example.tvlauncher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.example.tvlauncher.AppListViewModel
import java.text.SimpleDateFormat
import java.util.*

private val TvBackground = Color(0xFF0B0B0D)
private val TvTextPrimary = Color(0xFFFFFFFF)
private val TvTextSecondary = Color(0xFF8E8E93)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LauncherScreen(vm: AppListViewModel = viewModel()) {
    val apps by vm.apps.collectAsState()
    val context = LocalContext.current

    var time by remember { mutableStateOf(currentTime()) }
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(30_000)
            time = currentTime()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TvBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp, vertical = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 28.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Приложения",
                    color = TvTextPrimary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = time,
                    color = TvTextSecondary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            TvLazyVerticalGrid(
                columns = TvGridCells.Fixed(5),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(apps) { app ->
                    AppTile(app) {
                        try {
                            context.startActivity(app.intent)
                        } catch (_: Exception) { }
                    }
                }
            }
        }
    }
}

private fun currentTime(): String {
    val fmt = SimpleDateFormat("HH:mm", Locale.getDefault())
    return fmt.format(Date())
}