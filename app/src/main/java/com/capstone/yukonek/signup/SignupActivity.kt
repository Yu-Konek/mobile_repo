package com.capstone.yukonek.signup

import MyButton
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.component.textfield.MyEmailTextField
import com.capstone.yukonek.component.textfield.MyPasswordTextField
import com.capstone.yukonek.component.textfield.MyTextTextField
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.network.Result
import com.capstone.yukonek.ui.theme.YuKonekTheme
import kotlinx.coroutines.launch

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewSignUp()
            }
        }
    }
}

@Preview
@Composable
fun MainViewSignUp(
    navController: NavHostController? = null
) {
    val context = LocalContext.current
    val viewModel: RegisterViewModel =
        viewModel(factory = RegisterViewModelFactory.getInstance(context))
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    var hidePassword by remember { mutableStateOf(true) }
    var hidePasswordConfirm by remember { mutableStateOf(true) }
    var progressBarVisible by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf<Pair<String, () -> Unit>?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val isSignUpButtonEnabled =
        nameError == null && emailError == null && passwordError == null && confirmPasswordError == null &&
                email.isNotBlank() && password.isNotBlank() && name.isNotBlank() && confirmPassword.isNotBlank()

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.create_your_account),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.alpha(0.5F)

                )
                Spacer(modifier = Modifier.height(12.dp))
                MyTextTextField(
                    text = name,
                    onTextChange = {
                        name = it
                        nameError = if (name.isNotEmpty()) null else "Name cannot be empty"
                    },
                    label = stringResource(R.string.username),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    isError = nameError != null,
                    errorMessage = nameError
                )
                Spacer(modifier = Modifier.height(12.dp))
                MyEmailTextField(
                    email = email,
                    onEmailChange = {
                        email = it
                        emailError = if (isValidEmail(email)) null else "Invalid email address"
                    },
                    label = stringResource(R.string.email),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    isError = emailError != null,
                    errorMessage = emailError
                )
                Spacer(modifier = Modifier.height(12.dp))
                if (progressBarVisible) {
                    Column {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                MyPasswordTextField(
                    password = password,
                    onPasswordChange = {
                        password = it
                        passwordError =
                            if (isValidPassword(password)) null else "Password must be at least 8 characters"
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
                MyPasswordTextField(
                    password = confirmPassword,
                    onPasswordChange = {
                        confirmPassword = it
                        confirmPasswordError = if (doPasswordsMatch(
                                password,
                                confirmPassword
                            )
                        ) null else "Passwords do not match"
                    },
                    onTrailingIconClick = { hidePasswordConfirm = !hidePasswordConfirm },
                    hidePassword = hidePasswordConfirm,
                    label = stringResource(R.string.confirmation_password),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    isError = confirmPasswordError != null,
                    errorMessage = confirmPasswordError
                )
                Spacer(modifier = Modifier.height(12.dp))
                MyButton(
                    text = "Create Account",
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isSignUpButtonEnabled,
                    onClick = {
                        coroutineScope.launch {
                            viewModel.register(name, email, password, confirmPassword)
                                .observe(context as ComponentActivity) { result ->
                                    when (result) {
                                        is Result.Loading -> {
                                            progressBarVisible = true
                                        }

                                        is Result.Success -> {
                                            progressBarVisible = true
                                            navController?.navigate(Screen.SIGN_IN.name)
                                        }

                                        is Result.Error -> {
                                            progressBarVisible = true
                                            showDialog = true
                                        }
                                    }
                                }
                            Log.d("TAG", "TERTEKAN")
                        }
                    })
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Logout Confirmation") },
                text = { Text("Are you sure you want to logout?") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        coroutineScope.launch {
                            progressBarVisible = true
                            Log.d("TAG", "TERTEKAN")
                        }
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("No")
                    }
                }
            )
        }
    }
}


private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun isValidPassword(password: String): Boolean {
    return password.length >= 8
}

private fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}


