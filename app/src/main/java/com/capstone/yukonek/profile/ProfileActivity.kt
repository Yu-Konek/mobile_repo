package com.capstone.yukonek.profile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.ui.theme.YuKonekTheme
import kotlinx.coroutines.launch
import com.capstone.yukonek.profile.CardProfile as CardProfile

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewProfile()
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun MainViewProfile(navController: NavHostController? = null) {
    val viewModel: ProfileViewModel =
        viewModel(factory = ProfileViewModelFactory.getInstance(LocalContext.current))
    val themeSettings by viewModel.getThemeSettings().collectAsState(initial = false)
    val user by viewModel.getUserData().collectAsState(initial = "")
    val coroutineScope = rememberCoroutineScope()
    var progressBarVisible by remember { mutableStateOf(false) }
    val navigateToLoginPage by viewModel.navigateToLoginPage.observeAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(83.dp)
                            .width(83.dp)
                    )
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = user,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(percent = 50))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(4.dp)
                                .width(100.dp),
                            Alignment.Center
                        ) {
                            Text(
                                text = "Education",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(2.dp)
                )
                CardProfile(
                    painterResource(
                        id = R.drawable.ic_pencil
                    ),
                    text = "Edit Profile",
                    onClick = { navController?.navigate(Screen.EDIT_PROFILE.name) }
                )
//                CardProfile(
//                    painterResource(
//                        id = R.drawable.ic_favourite
//                    ),
//                    text = "Favourite",
//                    onClick = { navController?.navigate(Screen.DETAIL_FAVORITE_YOUTUBER.name) }
//                )
//                CardProfile(
//                    painterResource(
//                        id = R.drawable.ic_lock
//                    ),
//                    text = "Change Password",
//                    onClick = { navController?.navigate(Screen.CHANGE_PASSWORD.name) }
//                )
                CardProfile(
                    painterResource(
                        id = R.drawable.ic_about
                    ),
                    text = "About",
                    onClick = { navController?.navigate(Screen.ABOUT.name) }
                )
                SwitchWithIconExample(viewmodel = viewModel, themeSettings = themeSettings)
                Logout(onClick = {
                    showDialog = true
                })
                if (progressBarVisible) {
                    Column {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
    if (navigateToLoginPage == true) {
        navController?.navigate(Screen.SIGN_IN.name) {
            popUpTo(Screen.PROFILE.name) { inclusive = true }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Logout Confirmation") },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    coroutineScope.launch {
                        progressBarVisible = true
                        viewModel.logout()
                        Log.d("TAG", "TERTEKAN")
                    }
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}


@Composable
fun CardProfile(
    painter: Painter,
    text: String,
    onClick: () -> Unit = {}
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 20))
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Image(
                        modifier = Modifier.padding(20.dp),
                        painter = painter,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_bold),
                contentDescription = null,
            )
        }
    }
    HorizontalDivider(
        thickness = 2.dp,
        modifier = Modifier.padding(2.dp)
    )
}

@Preview
@Composable
fun SwitchWithIconExample(
    viewmodel: ProfileViewModel = viewModel(),
    themeSettings: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        if (themeSettings) {
            Image(
                modifier = Modifier.width(50.dp),
                painter = painterResource(id = R.drawable.ic_dark),
                contentDescription = null,
            )
        } else {
            Image(
                modifier = Modifier.width(50.dp),
                painter = painterResource(id = R.drawable.ic_light),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Switch(
            checked = themeSettings,
            onCheckedChange = { isChecked ->
                if (isChecked) {
                    viewmodel.setThemeSettings(true)
                } else {
                    viewmodel.setThemeSettings(false)
                }

            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = MaterialTheme.colorScheme.onBackground,
                uncheckedThumbColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedTrackColor = Color(0xFFFFC100),
            ),
            thumbContent = if (themeSettings) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                null
            }
        )
    }
}


@Composable
fun Logout(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Image(
            modifier = Modifier.padding(20.dp),
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = null,
        )
        Text(
            text = "Logout", color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
