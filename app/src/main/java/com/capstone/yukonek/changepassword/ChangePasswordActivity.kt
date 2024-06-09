package com.capstone.yukonek.changepassword

import MyButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.capstone.yukonek.component.appbar.MyTopBar
import com.capstone.yukonek.component.textfield.MyPasswordTextField
import com.capstone.yukonek.ui.theme.YuKonekTheme

class ChangePasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewChangePassword()
            }
        }
    }
}

@Preview
@Composable
fun MainViewChangePassword(navController:NavHostController? = null) {
    YuKonekTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { MyTopBar(title = "Change Password") },
            containerColor = MaterialTheme.colorScheme.surface
        ) { innerPadding ->
            var oldPassword by remember { mutableStateOf("") }
            var newPassword by remember { mutableStateOf("") }
            var confirmNewPassword by remember { mutableStateOf("") }
            var hideOldPassword by remember { mutableStateOf(true) }
            var hideNewPassword by remember { mutableStateOf(true) }
            var hideconfirmNewPassword by remember { mutableStateOf(true) }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Your password must be at least (number) characters and should include a combination of numbers, letters, and special characters (!\$@%)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Old Password",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    MyPasswordTextField(
                        password = oldPassword,
                        onPasswordChange = { oldPassword = it },
                        onTrailingIconClick = { hideOldPassword = !hideOldPassword },
                        hidePassword = hideOldPassword,
                        label = "Old Password",
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "New Password",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    MyPasswordTextField(
                        password = newPassword,
                        onPasswordChange = { newPassword = it },
                        onTrailingIconClick = { hideNewPassword = !hideNewPassword },
                        hidePassword = hideNewPassword,
                        label = "New Password",
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Confirmation New Password",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    MyPasswordTextField(
                        password = confirmNewPassword,
                        onPasswordChange = { confirmNewPassword = it },
                        onTrailingIconClick = { hideconfirmNewPassword = !hideconfirmNewPassword },
                        hidePassword = hideconfirmNewPassword,
                        label = "Confirmation New Password",
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    MyButton(text = "Create Account", modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}


