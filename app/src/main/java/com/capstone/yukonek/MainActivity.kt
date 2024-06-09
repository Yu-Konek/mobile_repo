package com.capstone.yukonek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.capstone.yukonek.splashscreen.SplashScreenViewModel
import com.capstone.yukonek.ui.theme.YuKonekTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.dark(
//                android.graphics.Color.TRANSPARENT,
//            ),
//            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
//        )
//        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.light(
//                android.graphics.Color.TRANSPARENT,
//                android.graphics.Color.TRANSPARENT
//            ),
//            navigationBarStyle = SystemBarStyle.light(
//                android.graphics.Color.TRANSPARENT,
//                android.graphics.Color.TRANSPARENT
//            )
//        )
//        enableEdgeToEdge()
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoading.value
        }
        setContent {
            YuKonekTheme {
                MainScreen()
            }
        }
    }
}
