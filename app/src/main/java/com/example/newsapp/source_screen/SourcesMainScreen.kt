package com.example.newsapp.source_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun SourcesMainScreen(navController: NavController , modifier: Modifier){
    Text(text = "Welcome in source screen")
}

@Preview
@Composable
fun SourcesMainScreenPreview(){
    val navController = NavController(context = LocalContext.current)
    SourcesMainScreen(navController , modifier = Modifier.fillMaxSize())
}