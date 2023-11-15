package com.example.iconchanging.manager

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import com.example.iconchanging.entity.LauncherIcon

class IconManager(private val context: Context) {

  fun isIconEnabled(icon: LauncherIcon): Boolean {
    val componentEnableSetting = context.packageManager.getComponentEnabledSetting(createComponentName(icon))
    return componentEnableSetting == PackageManager.COMPONENT_ENABLED_STATE_ENABLED ||
      componentEnableSetting == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT && icon == LauncherIcon.Default
  }

  fun enableIcon(icon: LauncherIcon) {
    LauncherIcon.values().onEach { i ->
      context.packageManager.setComponentEnabledSetting(
        createComponentName(i),
        if (i == icon) {
          PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        } else {
          PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        },
        PackageManager.DONT_KILL_APP
      )
    }
  }

  private fun createComponentName(icon: LauncherIcon): ComponentName {
    return ComponentName(
      context.packageName,
      "com.example.iconchanging.${icon.key}"
    )
  }
}
