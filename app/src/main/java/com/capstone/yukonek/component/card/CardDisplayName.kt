package com.capstone.yukonek.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.R
import com.example.ui.theme.AppTypography

@Composable
fun CardDisplayName() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
            )

            Column(modifier = Modifier.padding(horizontal = 0.dp)) {
                Text(
                    "Hi,Zaghy Zalayetha",
                    style = AppTypography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    "Welcome back",
                    style = AppTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_category_education),
            contentDescription = "Foto Profil",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(32.dp)

        )
    }
}