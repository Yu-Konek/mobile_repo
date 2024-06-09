package com.capstone.yukonek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.capstone.yukonek.mainscreen.MainScreen
import com.capstone.yukonek.mainscreen.MainViewModelFactory
import com.capstone.yukonek.mainscreen.MainViewmodel
import com.capstone.yukonek.splashscreen.SplashScreenViewModel

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
            MainScreen()
        }
    }
}
