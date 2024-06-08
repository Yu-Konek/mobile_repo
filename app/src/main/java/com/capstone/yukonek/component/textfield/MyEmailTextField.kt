package com.capstone.yukonek.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import com.capstone.yukonek.R
import com.capstone.yukonek.component.textfield.MyCustomTextField

@Composable
fun MyEmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
) {
    MyCustomTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = onEmailChange,
        leadingIcon = painterResource(id = R.drawable.outline_attach_email_24),
        visualTransformation = VisualTransformation.None
    )
}