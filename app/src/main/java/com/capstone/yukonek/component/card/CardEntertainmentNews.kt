package com.capstone.yukonek.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.R
import com.example.ui.theme.AppTypography

@Composable
fun CardEntertainmentNews() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.thumbnail_news),
            contentDescription = "Thumbnail News",
            modifier = Modifier
                .fillMaxWidth()
                .height(211.dp),
            contentScale = ContentScale.FillWidth


        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Jess dan Sisca Kohl Bucin Masuk Video Populer YouTube Indonesia 2022",
            style = AppTypography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Video kebucinan Jess No Limit bersama Sisca Kohl yang kini resmi menjadi istrinya menjadi salah satu video terpopuler di YouTube Indonesia pada 2022. Dalam video itu, pasangan.",
            style = AppTypography.bodyMedium
        )
    }
}