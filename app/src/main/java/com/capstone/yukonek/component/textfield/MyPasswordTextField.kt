package com.capstone.yukonek.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.capstone.yukonek.R
import androidx.compose.ui.text.TextStyle


object AppIcons {
    val EyeCrossed = R.drawable.ic_eyes_open
    val Eye = R.drawable.ic_eyes_closed
    val Lock = R.drawable.baseline_lock_24
}
@Composable
fun MyPasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
    hidePassword: Boolean,
    label: String,
    textStyle: TextStyle,
) {
    val trailingIcon =
        if (hidePassword) AppIcons.Eye
        else AppIcons.EyeCrossed

    val visualTransformation =
        if (hidePassword) PasswordVisualTransformation()
        else VisualTransformation.None

    MyCustomTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        label = label,
        textStyle = textStyle,
        onValueChange = onPasswordChange,
        leadingIcon = painterResource(id = AppIcons.Lock),
        trailingIcon = painterResource(id = trailingIcon),
        onTrailingIconClick = onTrailingIconClick,
        visualTransformation = visualTransformation
    )
}