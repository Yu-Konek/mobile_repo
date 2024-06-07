package com.capstone.yukonek.navigations

enum class Screen {
    SPLASH,
    HOME,
    FORM_USER,
    DETAIL_REMINDER,
    DETAIL_YOUTUBER_FOR_YOU,
    DETAIL_FAVORITE_YOUTUBER,
    ABOUT

}

sealed class NavigationItem(val route: String) {
    object Splash:NavigationItem(Screen.SPLASH.name)
    object Home : NavigationItem(Screen.HOME.name)
    object FormUser : NavigationItem(Screen.FORM_USER.name)
    object DetailReminder : NavigationItem(Screen.DETAIL_REMINDER.name)

    object DetailYoutuberForYou : NavigationItem(Screen.DETAIL_YOUTUBER_FOR_YOU.name)

    object DetailFavoriteYoutuber : NavigationItem(Screen.DETAIL_FAVORITE_YOUTUBER.name)

    object About : NavigationItem(Screen.ABOUT.name)


}