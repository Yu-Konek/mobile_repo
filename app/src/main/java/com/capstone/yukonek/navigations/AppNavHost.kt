package com.capstone.yukonek.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone.yukonek.detailreminder.MainViewDetailReminder
import com.capstone.yukonek.detailyoutuberforyou.DetailYoutuberForYou
import com.capstone.yukonek.detailyoutuberforyou.MainViewDetailYoutuberForYou
import com.capstone.yukonek.home.HomeActivity
import com.capstone.yukonek.home.MainViewHome

@Composable
fun AppNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  startDestination: String = NavigationItem.Home.route

){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){

        composable(NavigationItem.Home.route){
            MainViewHome(navController)
        }

        composable(NavigationItem.DetailYoutuberForYou.route){
            MainViewDetailYoutuberForYou(navController)
        }

        composable(NavigationItem.DetailReminder.route){
            MainViewDetailReminder(navController)
        }
    }
}