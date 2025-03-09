package com.nastirlex.bank.presentation.login

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val screenState by viewModel.screenState.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
//        TextField(
//            value = screenState.currentState.email,
//            onValueChange = { viewModel.onEmailChange(it) }
//        )
//        TextField(
//            value = screenState.currentState.password,
//            onValueChange = { viewModel.onPasswordChange(it) }
//        )
//        TextButton(onClick = { /*TODO*/ }) {
//            Text(text = "Войти")
//        }

        val mUrl = "http://10.0.2.2/oauth/#/customer"

        AndroidView(factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.allowContentAccess = true
                settings.allowUniversalAccessFromFileURLs = true
                settings.allowFileAccess = true

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                webViewClient = WebViewClient()
                loadUrl(mUrl, mapOf())


            }
        }, update = {
            it.settings.javaScriptEnabled = true
            it.settings.javaScriptCanOpenWindowsAutomatically = true
            it.settings.allowContentAccess = true
            it.settings.allowUniversalAccessFromFileURLs = true
            it.settings.allowFileAccess = true
            it.loadUrl(mUrl)
        })
    }
}