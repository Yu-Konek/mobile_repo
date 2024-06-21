package com.capstone.yukonek.welcome

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.mainscreen.MainViewModelFactory
import com.capstone.yukonek.mainscreen.MainViewmodel
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.ui.theme.YuKonekTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewWelcome()
            }
        }
    }
}

@Preview()
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainViewWelcome(navController: NavHostController? = null) {

    val context = LocalContext.current
    val viewModel: WelcomeViewModel =
        viewModel(factory = WelcomeViewModelFactory.getInstance(LocalContext.current))
    val userLogin by viewModel.getUser().observeAsState()

    Log.d("ini token dari datastore", userLogin?.token.toString())
    Log.d("ini username dari datastore", userLogin?.username.toString())

    if (userLogin?.username != "" && userLogin?.token != "" && userLogin?.isLogin == true) {
        navController?.navigate(Screen.HOME.name)
    }


    val pagerState = rememberPagerState(0) {
        3
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.urban_welcome),
                    contentDescription = "Welcome Page",
                    modifier = Modifier
                        .width(368.dp)
                        .height(276.dp)
                        .padding(16.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(16.dp)) // Add some space between image and text
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) { page ->
                        var title = ""
                        var text = ""
                        var showButtons = false
                        when (page) {
                            0 -> {
                                title = "ABOUT"
                                text = stringResource(R.string.about_onboarding)
                            }

                            1 -> {
                                title = "WHAT FOR"
                                text = stringResource(R.string.what_for_onboarding)
                            }

                            2 -> {
                                title = "WELCOME"
                                text = "Welcome to Yu-Konek"
                                showButtons = true
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = text, color = Color.White, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(16.dp))
                            if (showButtons) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(0.8F),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    Button(
                                        onClick = { navController?.navigate(Screen.SIGN_IN.name) },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .width(200.dp)
                                    ) {
                                        Text(
                                            text = "Sign in",
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    Button(
                                        onClick = { navController?.navigate(Screen.SIGN_UP.name) },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .width(200.dp)
                                    ) {
                                        Text(
                                            text = "Sign Up",
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                repeat(3) {
                    CustomIndicator(isSelected = pagerState.currentPage == it)
                }
            }
        }
    }
}

@Composable
fun CustomIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = if (isSelected) Color.Gray else Color.LightGray,
                shape = (RoundedCornerShape(percent = 50))
            )
            .width(50.dp)
            .height(15.dp)
    )
}
