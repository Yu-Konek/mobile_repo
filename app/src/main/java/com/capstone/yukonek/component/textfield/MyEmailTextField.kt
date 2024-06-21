package com.capstone.yukonek.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.R

@Composable
fun MyEmailTextField(
    label: String,
    email: String,
    onEmailChange: (String) -> Unit,
    textStyle: TextStyle,
    isError: Boolean,
    errorMessage: String?
) {
    MyCustomTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = onEmailChange,
        label = label,
        textStyle = textStyle,
        isError = isError,
        leadingIcon = painterResource(id = R.drawable.outline_attach_email_24),
        visualTransformation = VisualTransformation.None
    )
    if (isError){
        Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}