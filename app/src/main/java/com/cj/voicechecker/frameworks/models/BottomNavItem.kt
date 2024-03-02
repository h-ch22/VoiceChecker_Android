package com.cj.voicechecker.frameworks.models

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.QueueMusic
import androidx.compose.ui.graphics.vector.ImageVector

const val HOME = "HOME"
const val CHART = "CHART"
const val HISTORY = "HISTORY"
const val MORE = "MORE"

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screenRoute: String
) {
    object home: BottomNavItem("홈", Icons.Rounded.Home, HOME)
    object chart: BottomNavItem("차트", Icons.Rounded.QueueMusic, CHART)
    object history: BottomNavItem("기록", Icons.Rounded.History, HISTORY)
    object more: BottomNavItem("더 보기", Icons.Rounded.MoreHoriz, MORE)
}