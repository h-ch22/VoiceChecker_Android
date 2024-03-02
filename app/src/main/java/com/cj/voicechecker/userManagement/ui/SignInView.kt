package com.cj.voicechecker.userManagement.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.voicechecker.R
import com.cj.voicechecker.frameworks.ui.MainActivity
import com.cj.voicechecker.frameworks.ui.RichText
import com.cj.voicechecker.ui.theme.VoiceCheckerTheme
import com.cj.voicechecker.ui.theme.gray
import com.cj.voicechecker.userManagement.helper.UserManagement
import com.cj.voicechecker.userManagement.models.UserManagementAlertTypeModel

@Composable
fun SignInView() {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showProgress by remember {
        mutableStateOf(false)
    }

    var showAlert by remember {
        mutableStateOf(false)
    }

    val navController = rememberNavController()
    val helper = UserManagement()
    val context = LocalContext.current

    VoiceCheckerTheme {
        NavHost(navController = navController, startDestination = "SignInView") {
            composable("SignUpView") {
                SignUpView(parent = navController)
            }

            composable("SignInView") {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(id = R.drawable.ic_appstore),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )

                        RichText(text = "**Voice** Checker", fontSize = 18)

                        Spacer(modifier = Modifier.weight(1f))

                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "E-Mail")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.AlternateEmail,
                                    contentDescription = null
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "비밀번호")
                            },
                            leadingIcon = {
                                Icon(imageVector = Icons.Rounded.Key, contentDescription = null)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation()
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "비밀번호 재설정")
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TextButton(onClick = {
                                navController.navigate("SignUpView") {
                                    popUpTo("SignInView") {
                                        inclusive = false
                                    }
                                }
                            }) {
                                Text(text = "회원가입")
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            if (showProgress) {
                                CircularProgressIndicator()
                            } else {
                                Button(
                                    onClick = {
                                        showProgress = true

                                        helper.signIn(email, password){
                                            if(it != UserManagementAlertTypeModel.SUCCESS){
                                                showAlert = true
                                                showProgress = false
                                            } else{
                                                showProgress = false

                                                val intent = Intent(context, MainActivity :: class.java)
                                                context.startActivity(intent)
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(20.dp),
                                    enabled = email != "" && password != ""
                                ) {
                                    Text(text = "로그인")
                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "© 2024 Changjin Ha\nAll Rights Reserved.",
                            color = gray,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInViewPreview() {
    SignInView()
}