package com.example.iconchanging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iconchanging.entity.LauncherIcon
import com.example.iconchanging.manager.IconManager
import com.example.iconchanging.ui.theme.IconChangingTheme

class MainActivity : ComponentActivity() {

  private val iconManager = IconManager(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      IconChangingTheme {
        Box(
          modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
        ) {
          Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = "Текущая иконка",
              style = MaterialTheme.typography.bodyLarge
            )
            Row(
              horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
              LauncherIcon.values().forEach { icon ->
                ImageButton(
                  imageResId = icon.imageResId,
                  selected = iconManager.isIconEnabled(icon),
                  onClick = {
                    if (!iconManager.isIconEnabled(icon)) {
                      iconManager.enableIcon(icon)
                    }
                  }
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun ImageButton(
  @DrawableRes imageResId: Int,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .padding(1.dp)
      .then(
        if (selected) {
          Modifier.border(
            width = 1.dp,
            color = Color.Red,
            shape = RoundedCornerShape(8.dp)
          )
        } else {
          Modifier
        }
      )
      .size(60.dp)
      .clip(RoundedCornerShape(8.dp))
      .clickable(onClick = onClick)
  ) {
    Image(
      modifier = Modifier.matchParentSize(),
      painter = painterResource(id = imageResId),
      contentDescription = null
    )
  }
}

private val LauncherIcon.imageResId: Int
  @DrawableRes get() = when (this) {
    LauncherIcon.Default -> R.drawable.ic_launcher_inspiration
    LauncherIcon.Science -> R.drawable.ic_launcher_science
    LauncherIcon.Lemon -> R.drawable.ic_launcher_lemon
  }
