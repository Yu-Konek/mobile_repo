package com.capstone.yukonek.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.capstone.yukonek.R

@Composable
fun MyTextTextField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    textStyle: TextStyle
) {
    MyCustomTextField(
        value = text,
        label = label,
        textStyle = textStyle,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onTextChange,
        leadingIcon = painterResource(id = R.drawable.ic_person),
        visualTransformation = VisualTransformation.None
    )
}