package com.capstone.yukonek.detailnews

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailNewsWebViews(url: String) {
    val viewModel: DetailNewsViewModel =
        viewModel(factory = DetailNewsViewModelFactory(LocalContext.current))
//    val url by viewModel.getUrlNews().collectAsState(initial = "")
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }

        },
        update = { webView ->
            webView.loadUrl(
                url
            )

        })
}