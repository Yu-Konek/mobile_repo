package com.capstone.yukonek.splashscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.R
import com.capstone.yukonek.ui.theme.YuKonekTheme
import com.capstone.yukonek.ui.theme.primaryLight

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewSplashScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainViewSplashScreen() {
    YuKonekTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor =  Color(0XFF284880)
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(150.dp)
                    )
                    Spacer(modifier = Modifier.height(300.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logo2),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }

        }
    }
}

