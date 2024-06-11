package com.capstone.yukonek.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.capstone.yukonek.R
import com.capstone.yukonek.ui.theme.YuKonekTheme
import com.example.ui.theme.AppTypography

@Composable
fun CardEntertainmentNews(title:String,description:String,image:String,onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        onClick = onClick
    ) {
        if(image != ""){
            AsyncImage(
                model = image,
                contentDescription = "Thumbnail News",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(211.dp),
                contentScale = ContentScale.FillWidth
            )
        }else{
            Image(
                painter = painterResource(id = R.drawable.broken_image_news),
                contentDescription = "Thumbnail News",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(211.dp),
                contentScale = ContentScale.FillWidth


            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = AppTypography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            description,
            style = AppTypography.bodyMedium
        )
    }
}

@Preview
@Composable
fun PreviewCardEntertainmentNews() {
    YuKonekTheme {
        CardEntertainmentNews(title = "Jess dan Sisca Kohl Bucin Masuk Video Populer YouTube Indonesia 2022", description = "Video kebucinan Jess No Limit bersama Sisca Kohl yang kini resmi menjadi istrinya menjadi salah satu video terpopuler di YouTube Indonesia pada 2022. Dalam video itu, pasangan.", image = "", onClick = {})
    }
}