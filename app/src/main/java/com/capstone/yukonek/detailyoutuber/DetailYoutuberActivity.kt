package com.capstone.yukonek.detailyoutuber

import MyButton
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.capstone.yukonek.R
import com.capstone.yukonek.component.appbar.MyTopBar
import com.capstone.yukonek.detailyoutuber.data.MResponseDetailChannel
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.network.Result
import com.capstone.yukonek.ui.theme.YuKonekTheme
import com.capstone.yukonek.utilities.Utilities
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class DetailYoutuberActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewDetailYoutuber()
            }
        }
    }
}

@Preview
@Composable
fun MainViewDetailYoutuber(navController: NavHostController? = null, id: String? = null) {
    val viewModel: DetailYoutuberViewModel = viewModel(
        factory = DetailYoutuberViewModelFactory(
            LocalContext.current
        )
    )
    val detailYoutuber by viewModel.getDetailYoutuber(id = id ?: "")
        .observeAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(title = "Detail Youtuber", onBackClick = {
                navController?.popBackStack()
            })
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                when (detailYoutuber) {
                    is Result.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }

                    is Result.Success -> {
                        (detailYoutuber as Result.Success<MResponseDetailChannel>).data.items.let { items ->
                            items?.forEach { detail ->
                                item {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
//                                            Image(
//                                                painter = painterResource(id = R.drawable.thumbnail),
//                                                contentDescription = null,
//                                                modifier = Modifier
//                                                    .size(120.dp)
//                                                    .clip(RoundedCornerShape(48.dp))
//                                                    .clickable { },
//                                                Alignment.Center
//                                            )
                                        AsyncImage(
                                            model = detail?.snippet?.thumbnails?.medium?.url ?: "",
                                            contentDescription = "Thumbnail News",
                                            modifier = Modifier
                                                .size(120.dp)
                                                .clip(RoundedCornerShape(48.dp))
                                                .clickable { },

                                            )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = detail?.snippet?.title ?: "-",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                        Text(
                                            text = Utilities().formatSubscribers(
                                                detail?.statistics?.subscriberCount?.toInt() ?: 0
                                            ),
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(24.dp))
                                    Text(
                                        text = "Years Join YouTube",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                    Text(
                                        text = Utilities().convertDateString(
                                            detail?.snippet?.publishedAt ?: "-"
                                        ),
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "Genre",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                    Text(
                                        text = "Education",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "About",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                    Text(
                                        text = detail?.snippet?.description ?: "-",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Justify,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Column {
                                        Text(
                                            text = "LINK",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                        LinkRow(
                                            navController = navController,
                                            painter = painterResource(id = R.drawable.youtube),
                                            link = "www.youtube.com/${detail?.snippet?.customUrl ?: ""}"
                                        )
//                                            LinkRow(
//                                                painter = painterResource(id = R.drawable.instagram),
//                                                link = "www.instagram.com"
//                                            )
//                                            LinkRow(
//                                                painter = painterResource(id = R.drawable.gmail),
//                                                link = "mail.google.com"
//                                            )
                                    }
                                }
                            }
                        }

                    }

                    is Result.Error -> {
                        item {
                            Text(text = "Null")
                        }

                    }

                    else -> {
//                            do nothing
                    }
                }

            }
//                Box(
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .padding(24.dp)
//                ) {
//                    FavoriteButton()
//                }
        }
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63)
) {
    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

@Composable
fun LinkRow(
    navController: NavHostController?,
    painter: Painter,
    link: String,

    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            navController?.navigate(
                "${Screen.DETAIL_NEWS.name}/${
                    URLEncoder.encode(
                        "https://${link}",
                        StandardCharsets.UTF_8.toString()
                    )
                }"
            )
        }
    ) {
        Image(
            painter = painter,
            contentDescription = "youtube",
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = link,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
