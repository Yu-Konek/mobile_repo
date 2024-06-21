package com.capstone.yukonek.component.textfield

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import java.lang.Error

@Composable
fun MyCustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    isError: Boolean,
    // icons param
    leadingIcon: Painter? = null,
    onTrailingIconClick: () -> Unit = {},
    trailingIcon: Painter? = null,
    visualTransformation: VisualTransformation,
    label: String
) {
    OutlinedTextField(
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.secondary
        ),
        visualTransformation = visualTransformation,
        value = value,
        textStyle = textStyle,
        label = { Text(label) },
        onValueChange = onValueChange,
        isError = isError,
        // icons
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = it, contentDescription = null
                )
            }
        },
        trailingIcon = trailingIcon?.let {
            {
                IconButton(onClick = onTrailingIconClick) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = it, contentDescription = null
                    )
                }
            }
        }
    )
}

