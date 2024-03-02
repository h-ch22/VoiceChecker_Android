package com.cj.voicechecker.more.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.voicechecker.home.ui.HomeView
import com.cj.voicechecker.ui.theme.VoiceCheckerTheme

@Composable
fun MoreView(){
    VoiceCheckerTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {

        }
    }
}

@Preview
@Composable
fun MoreViewPreview(){
    MoreView()
}