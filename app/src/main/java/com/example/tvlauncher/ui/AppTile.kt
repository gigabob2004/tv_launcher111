```kotlin
package com.example.tvlauncher.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.foundation.PivotOffsets
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.example.tvlauncher.AppInfo

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppTile(
    app: AppInfo,
    onClick: () -> Unit
) {
    var focused by remember { mutableStateOf(false) }

    // Плавное «всплытие» плитки при фокусе — как в Apple TV
    val scale by animateFloatAsState(
        targetValue = if (focused) 1.08f else 1f,
        animationSpec = tween(durationMillis = 180),
        label = "tileScale"
    )
    val elevation by animateFloatAsState(
        targetValue = if (focused) 16f else 0f,
        animationSpec = tween(durationMillis = 180),
        label = "tileElevation"
    )

    val iconBitmap = remember(app.packageName) {
        app.icon?.toBitmap(160, 160)?.asImageBitmap()
    }

    Card(
        onClick = onClick,
        onFocusChanged = { focused = it.isFocused },
        scale = androidx.tv.material3.CardDefaults.scale(
            focusedScale = 1f,   // масштаб делаем сами через Modifier.scale
            pressedScale = 0.96f
        ),
        shape = CardDefaults.shape(RoundedCornerShape(14.dp)),
        colors = CardDefaults.colors(
            containerColor = if (focused) TvSurfaceFocused else TvSurface,
            focusedContainerColor = TvSurfaceFocused
        ),
        modifier = Modifier
            .size(width = 200.dp, height = 160.dp)
            .scale(scale)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color(0x66000000))
                    )
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (iconBitmap != null) {
                    Image(
                        bitmap = iconBitmap,
                        contentDescription = app.label,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = app.label,
                    color = TvTextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
```

---