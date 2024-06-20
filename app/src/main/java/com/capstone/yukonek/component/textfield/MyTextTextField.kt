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
fun MyTextTextField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    textStyle: TextStyle,
    isError: Boolean,
    errorMessage: String?,
) {
    MyCustomTextField(
        value = text,
        label = label,
        textStyle = textStyle,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onTextChange,
        leadingIcon = painterResource(id = R.drawable.ic_person),
        visualTransformation = VisualTransformation.None,
        isError = isError
    )
    if (isError) {
        Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}