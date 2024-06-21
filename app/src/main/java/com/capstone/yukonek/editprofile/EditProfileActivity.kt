package com.capstone.yukonek.editprofile

import MyButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.component.appbar.MyTopBar
import com.capstone.yukonek.component.textfield.MyEmailTextField
import com.capstone.yukonek.component.textfield.MyTextTextField
import com.capstone.yukonek.ui.theme.YuKonekTheme

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewEditProfile()
            }
        }
    }
}

@Preview
@Composable
fun MainViewEditProfile(navController: NavHostController? = null) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(title = "Edit Profile", onBackClick = {
                navController?.popBackStack()
            })
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        var name by remember { mutableStateOf("") }
        var youtube by remember { mutableStateOf("") }
        var instagram by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        var nameError by remember { mutableStateOf<String?>(null) }
        var youtubeError by remember { mutableStateOf<String?>(null) }
        var instagramError by remember { mutableStateOf<String?>(null) }
        var emailError by remember { mutableStateOf<String?>(null) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.thumbnail),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(48.dp))
                            .clickable { },
                        Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Change Photo",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Name",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
                MyTextTextField(
                    label = "Name",
                    text = name,
                    onTextChange = {
                        name = it
                        nameError = if (name.isNotEmpty()) null else "Name cannot be empty"
                    },
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    isError = nameError != null,
                    errorMessage = nameError
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "YouTube",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
                MyTextTextField(
                    label = "YouTube",
                    text = youtube,
                    onTextChange = {
                        youtube = it
                        youtubeError =
                            if (youtube.isNotEmpty()) null else "YouTube cannot be empty"
                    },
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    isError = youtubeError != null,
                    errorMessage = youtubeError
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Instagram",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
                MyTextTextField(
                    label = "Instagram",
                    text = instagram,
                    onTextChange = {
                        instagram = it
                        instagramError =
                            if (instagram.isNotEmpty()) null else "Instagram cannot be empty"
                    },
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    isError = instagramError != null,
                    errorMessage = instagramError
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
                MyEmailTextField(
                    label = "Email",
                    email = email,
                    onEmailChange = {
                        email = it
                        emailError = if (isValidEmail(email)) null else "Invalid email address"
                    },
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    isError = emailError != null,
                    errorMessage = emailError
                )
                Spacer(modifier = Modifier.height(32.dp))
                MyButton(text = "Save", modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

