package com.capstone.yukonek.formuser

import MyButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.capstone.yukonek.formuser.data.Category
import com.capstone.yukonek.ui.theme.YuKonekTheme
import com.example.ui.theme.AppTypography

class FormUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YuKonekTheme {
                MainViewFormUser()
            }
        }
    }
}
@Composable
fun MainViewFormUser(navController:NavHostController? = null){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TitleInstruction(
                text = "Fill in the data below so we can give you good recommendations",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            DropdownMenuCategory(
                label = "Your Content Category 1",
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
            DropdownMenuCategory(
                label = "Your Content Category 2", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
            DropdownMenuCategory(
                label = "Your Content Category 3", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
            CustomOutlinedTextFieldNumberOnly(
                label = "Number of Your Subscribers", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
            MyButton(
                text = "Submit",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp)

            )
        }
    }
}
@Composable
fun TitleInstruction(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = AppTypography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuCategory(label: String, modifier: Modifier = Modifier) {
    val categoryYoutube = Category.category
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(categoryYoutube[0]) }

    Box(modifier = modifier) {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }) {
            OutlinedTextField(label = { Text(label) }, value = selectedText, onValueChange = {
            },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            DropdownMenu(
                modifier = Modifier.exposedDropdownSize(),
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }) {
                categoryYoutube.forEach { item ->
                    DropdownMenuItem(text = { Text(text = item) }, onClick = {
                        selectedText = item
                        expanded = false

                    })
                }
            }

        }
    }
}

@Composable
fun CustomOutlinedTextFieldNumberOnly(label: String, modifier: Modifier = Modifier) {
    val pattern = remember { Regex("^\\d+\$") }
    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            if (it.isEmpty() || it.matches(pattern)) {
                text = it;
            }
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    YuKonekTheme {
        MainViewFormUser()
    }
}