package com.capstone.yukonek.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstone.yukonek.about.MainViewAbout
import com.capstone.yukonek.changepassword.MainViewChangePassword
import com.capstone.yukonek.detailfavoriteyoutuber.MainViewDetailFavoriteYoutuber
import com.capstone.yukonek.detailnews.DetailNewsWebViews
import com.capstone.yukonek.detailreminder.MainViewDetailReminder
import com.capstone.yukonek.detailyoutuber.MainViewDetailYoutuber
import com.capstone.yukonek.detailyoutuberforyou.DetailYoutuberForYou
import com.capstone.yukonek.detailyoutuberforyou.MainViewDetailYoutuberForYou
import com.capstone.yukonek.editprofile.MainViewEditProfile
import com.capstone.yukonek.forgetpassword.MainViewForgetPassword
import com.capstone.yukonek.formuser.MainViewFormUser
import com.capstone.yukonek.home.MainViewHome
import com.capstone.yukonek.profile.MainViewProfile
import com.capstone.yukonek.signin.MainViewSignIn
import com.capstone.yukonek.signup.MainViewSignUp
import com.capstone.yukonek.welcome.MainViewWelcome

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Welcome.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {


        composable(NavigationItem.Welcome.route) {
            MainViewWelcome(navController)
        }
        composable(NavigationItem.SignIn.route) {
            MainViewSignIn(navController)
        }
        composable(NavigationItem.SignUp.route) {
            MainViewSignUp(navController)
        }
        composable(NavigationItem.ForgetPassword.route) {
            MainViewForgetPassword(navController)
        }
        composable(NavigationItem.FormUser.route) {
            MainViewFormUser(navController)
        }
        composable(NavigationItem.Home.route) {
            MainViewHome(navController)
        }
        composable(NavigationItem.Profile.route) {
            MainViewProfile(navController)
        }
        composable(
            "${NavigationItem.DetailYoutuber.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {entry->
            val id = entry.arguments?.getString("id") ?: ""
            MainViewDetailYoutuber(navController,id)
        }
        composable(NavigationItem.DetailYoutuberForYou.route) {
            MainViewDetailYoutuberForYou(navController)
        }
        composable(NavigationItem.DetailReminder.route) {
            MainViewDetailReminder(navController)
        }
        composable(NavigationItem.DetailFavoriteYoutuber.route) {
            MainViewDetailFavoriteYoutuber(navController)
        }
        composable(NavigationItem.EditProfile.route) {
            MainViewEditProfile(navController)
        }
        composable(NavigationItem.ChangePassword.route) {
            MainViewChangePassword(navController)
        }
        composable(NavigationItem.About.route) {
            MainViewAbout(navController)
        }
        composable(
            "${NavigationItem.DetailNews.route}/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { entry ->
            val url = entry.arguments?.getString("url") ?: ""
            DetailNewsWebViews(url = url)
        }

    }
}