package com.example.newsapp.news_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun NewsMainDetailsScreen(navController: NavController, modifier: Modifier){

}

@Preview
@Composable
fun NewsMainScreenPreview(){
    val navController = NavController(LocalContext.current)
    NewsMainDetailsScreen(navController, modifier = Modifier.fillMaxSize())
}