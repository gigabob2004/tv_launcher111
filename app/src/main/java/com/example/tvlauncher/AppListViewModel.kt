package com.example.tvlauncher

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppListViewModel(app: Application) : AndroidViewModel(app) {

    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps: StateFlow<List<AppInfo>> = _apps

    init { loadApps() }

    fun loadApps() {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                val ctx = getApplication<Application>()
                val pm = ctx.packageManager
                val self = ctx.packageName

                val leanback = pm.queryIntentActivities(
                    Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER), 0
                )
                val normal = pm.queryIntentActivities(
                    Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER), 0
                )

                (leanback + normal)
                    .distinctBy { it.activityInfo.packageName }
                    .filter { it.activityInfo.packageName != self }
                    .map {
                        AppInfo(
                            label = it.loadLabel(pm).toString(),
                            packageName = it.activityInfo.packageName,
                            icon = it.loadIcon(pm),
                            intent = Intent(Intent.ACTION_MAIN).apply {
                                addCategory(Intent.CATEGORY_LAUNCHER)
                                setClassName(it.activityInfo.packageName, it.activityInfo.name)
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                        )
                    }
                    .sortedBy { it.label.lowercase() }
            }
            _apps.value = list
        }
    }
}
