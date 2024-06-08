package com.capstone.yukonek.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.capstone.yukonek.navigations.NavigationItem


@Composable
fun BottomAppBarWithNavigation(navController: NavHostController) {
    BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {
        BottomNavigationItem(
            navController = navController,
            screen = NavigationItem.Home,
            icon = Icons.Default.Home,
            label = "Home"
        )
        BottomNavigationItem(
            navController = navController,
            screen = NavigationItem.About,
            icon = Icons.Default.AccountCircle,
            label = "Profile"
        )
    }
}

@Composable
fun BottomNavigationItem(
    navController: NavHostController,
    screen: NavigationItem,
    icon: ImageVector,
    label: String
) {
    IconButton(onClick = { navController.navigate(screen.route) }) {
        Icon(imageVector = icon, contentDescription = label)
    }
}


