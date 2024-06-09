package com.capstone.yukonek.signin

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.component.textfield.MyEmailTextField
import com.capstone.yukonek.component.textfield.MyPasswordTextField
import com.capstone.yukonek.ui.theme.YuKonekTheme

class SigninActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewSignIn()
            }
        }
    }
}

@Preview
@Composable
fun MainViewSignIn(navController: NavHostController? = null) {
    YuKonekTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.surface
        ) { innerPadding ->
            var email by remember { mutableStateOf("") }
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
                        text = stringResource(R.string.welcome_back),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.welcome_text),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.alpha(0.5F)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    MyEmailTextField(
                        email = email,
                        onEmailChange = { email = it },
                        label = stringResource(R.string.email),
                        textStyle = TextStyle(
                            fontSize = 8.sp,
                            fontStyle = FontStyle.Normal,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    MyPasswordTextField(
                        password = password,
                        onPasswordChange = { password = it },
                        onTrailingIconClick = { hidePassword = !hidePassword },
                        hidePassword = hidePassword,
                        label = stringResource(R.string.password),
                        textStyle = TextStyle(
                            fontSize = 8.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Text(
                        text = stringResource(R.string.forget_password),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    MyButton(
                        text = "Sign In",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row {
                        Text(
                            text = stringResource(R.string.don_t_have_account),
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(R.string.welcome_text_sign_up),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

