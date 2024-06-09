package com.capstone.yukonek.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone.yukonek.about.MainViewAbout
import com.capstone.yukonek.changepassword.MainViewChangePassword
import com.capstone.yukonek.detailfavoriteyoutuber.MainViewDetailFavoriteYoutuber
import com.capstone.yukonek.detailreminder.MainViewDetailReminder
import com.capstone.yukonek.detailyoutuberforyou.DetailYoutuberForYou
import com.capstone.yukonek.detailyoutuberforyou.MainViewDetailYoutuberForYou
import com.capstone.yukonek.forgetpassword.MainViewForgetPassword
import com.capstone.yukonek.formuser.MainViewFormUser
import com.capstone.yukonek.home.HomeActivity
import com.capstone.yukonek.home.MainViewHome
import com.capstone.yukonek.profile.MainViewProfile
import com.capstone.yukonek.signin.MainViewSignIn
import com.capstone.yukonek.signup.MainViewSignUp

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

        composable(NavigationItem.SignIn.route){
            MainViewSignIn(navController)
        }
        composable(NavigationItem.SignUp.route){
            MainViewSignUp(navController)
        }
        composable(NavigationItem.ForgetPassword.route){
            MainViewForgetPassword(navController)
        }
        composable(NavigationItem.FormUser.route){
            MainViewFormUser(navController)
        }
        composable(NavigationItem.Home.route){
            MainViewHome(navController)
        }
        composable(NavigationItem.Profile.route){
            MainViewProfile(navController)
        }

        composable(NavigationItem.DetailYoutuberForYou.route){
            MainViewDetailYoutuberForYou(navController)
        }

        composable(NavigationItem.DetailReminder.route){
            MainViewDetailReminder(navController)
        }

        composable(NavigationItem.DetailFavoriteYoutuber.route){
            MainViewDetailFavoriteYoutuber(navController)
        }
        composable(NavigationItem.EditProfile.route){
//            TODO : Belum ada halaman edit profile
        }
        composable(NavigationItem.ChangePassword.route){
            MainViewChangePassword(navController)
        }
        composable(NavigationItem.About.route){
            MainViewAbout(navController)
        }

    }
}