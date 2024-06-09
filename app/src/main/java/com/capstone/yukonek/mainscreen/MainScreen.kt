package com.capstone.yukonek.mainscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
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

//    getTheme from datastore
    val viewModel:MainViewmodel = viewModel(factory = MainViewModelFactory(LocalContext.current))
    val themeSettings by viewModel.getThemeSettings().collectAsState(initial = false)

    YuKonekTheme(darkTheme = themeSettings) {
        Surface {
            Scaffold(bottomBar = {
                if(currentRoute in listOfVisibleNavigationBar){
                    BottomNavigationBar(navController = navController)
                }
            }) {  innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)){
                    AppNavHost(navController = navController)
                }
            }
        }

    }

}