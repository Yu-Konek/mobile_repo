package com.capstone.yukonek.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.R
import com.example.ui.theme.AppTypography

@Composable
fun CardListYoutuberColumn() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.thumbnail),
                contentDescription = "Thumbnail youtuber",
                modifier = Modifier.size(156.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Jerome Polin", style = AppTypography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondary
                ), shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    "Education",
                    style = AppTypography.labelMedium,
                    modifier = Modifier.padding(12.dp)
                )
            }

        }
    }
}