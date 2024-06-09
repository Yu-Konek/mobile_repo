package com.capstone.yukonek.navigations

enum class Screen {
    SIGN_IN,
    SIGN_UP,
    FORGOT_PASSWORD,
    FORM_USER,
    HOME,
    DETAIL_REMINDER,
    DETAIL_YOUTUBER_FOR_YOU,
    DETAIL_FAVORITE_YOUTUBER,
    PROFILE,
    EDIT_PROFILE,
    CHANGE_PASSWORD,
    ABOUT

}

sealed class NavigationItem(val route: String) {
    data object SignIn:NavigationItem(Screen.SIGN_IN.name)
    data object SignUp:NavigationItem(Screen.SIGN_UP.name)
    data object ForgotPassword:NavigationItem(Screen.FORGOT_PASSWORD.name)
    data object FormUser : NavigationItem(Screen.FORM_USER.name)
    data object Home : NavigationItem(Screen.HOME.name)
    data object DetailReminder : NavigationItem(Screen.DETAIL_REMINDER.name)
    data object DetailYoutuberForYou : NavigationItem(Screen.DETAIL_YOUTUBER_FOR_YOU.name)
    data object DetailFavoriteYoutuber : NavigationItem(Screen.DETAIL_FAVORITE_YOUTUBER.name)
    data object Profile:NavigationItem(Screen.PROFILE.name)
    data object EditProfile:NavigationItem(Screen.EDIT_PROFILE.name)
    data object ChangePassword:NavigationItem(Screen.CHANGE_PASSWORD.name)
    data object About : NavigationItem(Screen.ABOUT.name)


}