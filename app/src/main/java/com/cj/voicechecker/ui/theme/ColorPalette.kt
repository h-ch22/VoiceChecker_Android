package com.cj.voicechecker.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val txtColor : Color = Color.Unspecified,
    val btnColor : Color = Color.Unspecified,
    val txtFieldColor : Color = Color.Unspecified
)

val VoiceCheckerColorPalette = staticCompositionLocalOf {
    ColorPalette()
}