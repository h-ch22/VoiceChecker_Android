package com.cj.voicechecker.userManagement.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cj.voicechecker.frameworks.ui.MainActivity
import com.cj.voicechecker.ui.theme.VoiceCheckerTheme
import com.cj.voicechecker.userManagement.helper.UserManagement
import com.cj.voicechecker.userManagement.models.UserManagementAlertTypeModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(parent: NavHostController) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var checkPassword by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var nickName by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var isEULAAccepted by remember {
        mutableStateOf(false)
    }

    var isPrivacyLicenseAccepted by remember {
        mutableStateOf(false)
    }

    var showProgress by remember {
        mutableStateOf(false)
    }

    var showAlert by remember {
        mutableStateOf(false)
    }

    var alertModel by remember {
        mutableStateOf<UserManagementAlertTypeModel?>(null)
    }

    val scrollState = rememberScrollState()
    val helper = UserManagement()
    val context = LocalContext.current

    VoiceCheckerTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface,
            topBar = {
                MediumTopAppBar(title = { Text(text = "회원가입") },
                    navigationIcon = {
                        IconButton(onClick = {
                            parent.popBackStack()
                        }) {
                            Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                        }
                    })
            }
        ) {
            Surface(modifier = Modifier.padding(it), color = MaterialTheme.colorScheme.surface) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(scrollState)
                ) {
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

                    TextField(
                        value = checkPassword,
                        onValueChange = { checkPassword = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "비밀번호 확인")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Key, contentDescription = null)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "이름")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = phone,
                        onValueChange = { phone = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "연락처")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Phone, contentDescription = null)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = nickName,
                        onValueChange = { nickName = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "닉네임")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Checkbox(checked = isEULAAccepted, onCheckedChange = {
                                isEULAAccepted = it
                            })

                            Text(text = "최종 사용권 계약")
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "읽기")
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Checkbox(checked = isPrivacyLicenseAccepted, onCheckedChange = {
                                isPrivacyLicenseAccepted = it
                            })

                            Text(text = "개인정보 처리 방침")
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "읽기")
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (showProgress) {
                            CircularProgressIndicator()
                        } else {
                            Button(
                                onClick = {
                                    if (!email.contains("@")) {
                                        alertModel =
                                            UserManagementAlertTypeModel.INCORRECT_EMAIL_TYPE
                                        showAlert = true
                                    } else if (password.length < 6) {
                                        alertModel = UserManagementAlertTypeModel.WEAK_PASSWORD
                                        showAlert = true
                                    } else if (password != checkPassword) {
                                        alertModel = UserManagementAlertTypeModel.PASSWORD_MISMATCH
                                        showAlert = true
                                    } else {
                                        showProgress = true

                                        helper.signUp(email, password, name, phone, nickName) {
                                            if (it == UserManagementAlertTypeModel.SUCCESS) {
                                                showProgress = false

                                                val intent = Intent(context, MainActivity::class.java)
                                                context.startActivity(intent)
                                            } else {
                                                alertModel = it
                                                showAlert = true
                                                showProgress = false
                                            }
                                        }
                                    }
                                },
                                enabled = (email != "" && password != "" && checkPassword != "" && name != "" && phone != "" && nickName != "" && isEULAAccepted && isPrivacyLicenseAccepted)
                            ) {
                                Text(text = "회원가입")
                            }
                        }
                    }

                    if (showAlert) {
                        AlertDialog(onDismissRequest = { showAlert = false },
                            title = { Text(text = alertModel!!.getTitle()) },
                            text = { Text(text = alertModel!!.getMessage()) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Rounded.Warning,
                                    contentDescription = null
                                )
                            },
                            confirmButton = {
                                TextButton(onClick = { showAlert = false }) {
                                    Text(text = "확인")
                                }
                            })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpViewPreview() {
    SignUpView(rememberNavController())
}