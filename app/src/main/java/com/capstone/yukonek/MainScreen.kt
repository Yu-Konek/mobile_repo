package com.capstone.yukonek

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstone.yukonek.bottomnavigation.BottomNavigationBar
import com.capstone.yukonek.bottomnavigation.BottomNavigationItem
import com.capstone.yukonek.navigations.AppNavHost
import com.capstone.yukonek.ui.theme.YuKonekTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val listOfVisibleNavigationBar = listOf(BottomNavigationItem.Home.route, BottomNavigationItem.Profile.route)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    YuKonekTheme {
        Surface {
            Scaffold(bottomBar = {
                if(currentRoute in listOfVisibleNavigationBar){
                    BottomNavigationBar(navController = navController)
                }
            }) {  _ ->
                AppNavHost(navController = navController)
            }
        }
    }


}