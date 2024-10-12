package com.example.newsapp.search

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController, modifier: Modifier = Modifier){}


@Preview
@Composable
fun SearchScreenPreview(){
    val navController = NavController(LocalContext.current)
    SearchScreen(navController)
}