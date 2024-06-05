package com.capstone.yukonek.detailyoutuberforyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.yukonek.R
import com.capstone.yukonek.component.MyTopBar
import com.example.compose.YuKonekTheme
import com.example.ui.theme.AppTypography

class DetailYoutuberForYou : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(title = "Detail YouTubers For You")
        },
    ) { innerPadding->
        LazyColumn(modifier = Modifier.padding(innerPadding)){
            items(10){
                CardListYoutuber()
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            }

        }

    }
}

@Composable
fun CardListYoutuber() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(8.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.thumbnail),
                contentDescription = "thumbnail youtuber",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(112.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Jerome Polin",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
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
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "10,5 jt subscriber",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailYoutuberForYourPreview() {
    YuKonekTheme {
        MainView()
    }
}

@Preview()
@Composable
fun PreviewCardListYoutuber() {
    CardListYoutuber()
}
