package com.capstone.yukonek.signup

import MyButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.yukonek.component.textfield.MyEmailTextField
import com.capstone.yukonek.component.textfield.MyPasswordTextField
import com.capstone.yukonek.component.textfield.MyTextTextField
import com.example.compose.YuKonekTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {

            }
        }
    }
}

@Preview
@Composable
fun MainViewSignUp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        var email by remember { mutableStateOf("") }
        var text by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var hidePassword by remember { mutableStateOf(true) }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Create Your Account ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                MyTextTextField(
                    text = text,
                    onTextChange = { text = it },
                    label = "Username",
                    textStyle = TextStyle(
                        fontSize = 8.sp,
                        fontStyle = FontStyle.Normal
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                MyEmailTextField(
                    email = email,
                    onEmailChange = { email = it },
                    label = "Email",
                    textStyle = TextStyle(
                        fontSize = 8.sp,
                        fontStyle = FontStyle.Normal
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                MyPasswordTextField(
                    password = password,
                    onPasswordChange = { password = it },
                    onTrailingIconClick = { hidePassword = !hidePassword },
                    hidePassword = hidePassword,
                    label = "Password",
                    textStyle = TextStyle(
                        fontSize = 8.sp,
                        fontStyle = FontStyle.Normal
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                MyPasswordTextField(
                    password = password,
                    onPasswordChange = { password = it },
                    onTrailingIconClick = { hidePassword = !hidePassword },
                    hidePassword = hidePassword,
                    label = "Confirmation Password",
                    textStyle = TextStyle(
                        fontSize = 8.sp,
                        fontStyle = FontStyle.Normal
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                MyButton(text = "Create Account", modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

