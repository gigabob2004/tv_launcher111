package com.example.tvlauncher.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.material3.*
import com.example.tvlauncher.AppInfo

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppTile(app: AppInfo, onClick: () -> Unit) {
    val iconBitmap = remember(app.packageName) {
        app.icon?.toBitmap(128, 128)?.asImageBitmap()
    }

    Card(
        onClick = onClick,
        modifier = Modifier.size(160.dp, 140.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (iconBitmap != null) {
                Image(
                    bitmap = iconBitmap,
                    contentDescription = app.label,
                    modifier = Modifier.size(64.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = app.label,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}