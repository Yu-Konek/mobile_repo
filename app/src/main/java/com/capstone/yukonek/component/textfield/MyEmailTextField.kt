package com.capstone.yukonek.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.capstone.yukonek.R

@Composable
fun MyEmailTextField(
    label: String,
    email: String,
    onEmailChange: (String) -> Unit,
    textStyle: TextStyle
) {
    MyCustomTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = onEmailChange,
        label = label,
        textStyle = textStyle,
        leadingIcon = painterResource(id = R.drawable.outline_attach_email_24),
        visualTransformation = VisualTransformation.None
    )
}