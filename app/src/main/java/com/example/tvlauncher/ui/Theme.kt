```kotlin
package com.example.tvlauncher.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Палитра в духе Apple TV: почти чёрный фон, серые плитки, белый текст
val TvBackground = Color(0xFF0B0B0D)
val TvSurface = Color(0xFF1C1C1E)
val TvSurfaceFocused = Color(0xFF2C2C2E)
val TvTextPrimary = Color(0xFFFFFFFF)
val TvTextSecondary = Color(0xFF8E8E93)
val TvAccent = Color(0xFF0A84FF)

private val TvColorScheme = darkColorScheme(
    primary = TvAccent,
    background = TvBackground,
    surface = TvSurface,
    onPrimary = Color.White,
    onBackground = TvTextPrimary,
    onSurface = TvTextPrimary
)

@Composable
fun TvLauncherTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = TvColorScheme, content = content)
}
```

---