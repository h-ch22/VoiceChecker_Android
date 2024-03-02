package com.cj.voicechecker.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cj.voicechecker.ui.theme.VoiceCheckerTheme
import com.cj.voicechecker.userManagement.helper.UserManagement

@Composable
fun HomeView(){
    VoiceCheckerTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                Text(text = "안녕하세요,\n${UserManagement.getUserInfo()?.name ?: "알 수 없는 사용자"}님 😆", fontSize = 12.sp)
            }
        }
    }
}

@Preview
@Composable
fun HomeViewPreview(){
    HomeView()
}