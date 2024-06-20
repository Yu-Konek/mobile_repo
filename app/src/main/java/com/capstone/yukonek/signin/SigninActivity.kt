package com.capstone.yukonek.signin

import MyButton
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.component.textfield.MyEmailTextField
import com.capstone.yukonek.component.textfield.MyPasswordTextField
import com.capstone.yukonek.local.datastore.MUser
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.network.Result
import com.capstone.yukonek.ui.theme.YuKonekTheme
import kotlinx.coroutines.launch

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

    val context = LocalContext.current
    val viewModel: LoginViewModel =
        viewModel(factory = LoginViewModelFactory.getInstance(context))

    val shouldNavigate by viewModel.navigateToHomePage.observeAsState()
    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate == true) {
            navController?.navigate(Screen.HOME.name)
        }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    var hidePassword by remember { mutableStateOf(true) }
    var progressBarVisible by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf<Pair<String, () -> Unit>?>(null) }
    val coroutineScope = rememberCoroutineScope()

    YuKonekTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.surface
        ) { innerPadding ->

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
                    if (progressBarVisible) {
                        Column {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    MyEmailTextField(
                        email = email,
                        onEmailChange = {
                            email = it
                            emailError = if (isValidEmail(email)) null else "Invalid email address"
                        },
                        label = stringResource(R.string.email),
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Normal,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        isError = emailError != null,
                        errorMessage = emailError
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    MyPasswordTextField(
                        password = password,
                        onPasswordChange = {
                            password = it
                            passwordError = if (isValidPassword(password)) null else "Password must be at least 6 characters"
                        },
                        onTrailingIconClick = { hidePassword = !hidePassword },
                        hidePassword = hidePassword,
                        label = stringResource(R.string.password),
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Normal
                        ),
                        isError = passwordError != null,
                        errorMessage = passwordError
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ClickableText(
                        text = AnnotatedString(stringResource(id = R.string.forgot_password)),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.End,
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { navController?.navigate(Screen.FORGOT_PASSWORD.name) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    MyButton(
                        text = "Sign In",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            coroutineScope.launch {
                                viewModel.login(email, password)
                                    .observe(context as ComponentActivity) { result ->
                                        when (result) {
                                            is Result.Loading -> {
                                                progressBarVisible = true
                                            }

                                            is Result.Success -> {
                                                progressBarVisible = true
                                                result.data.let {
                                                    viewModel.saveTokenAndNavigate(
                                                        MUser(
                                                            token = it.accessToken ?: "",
                                                            isLogin = true
                                                        )
                                                    )
                                                }
                                            }

                                            is Result.Error -> {
                                                    dialogState = Pair(result.error) {}
                                            }
                                        }
                                    }
                                Log.d("TAG", "TERTEKAN")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Text(
                            text = stringResource(R.string.don_t_have_account),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        ClickableText(
                            text = AnnotatedString(stringResource(id = R.string.welcome_text_sign_up)),
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                        textDecoration = TextDecoration.Underline
                            ),
                            onClick = { navController?.navigate(Screen.SIGN_UP.name) }
                        )
                    }
                }
            }
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun isValidPassword(password: String): Boolean {
    return password.length >= 6
}

