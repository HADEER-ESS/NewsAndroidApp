package com.example.newsapp.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.newsapp.source_screen.NewsCardItem

@Composable
fun SearchScreen(navController: NavController,viewModel: SearchViewModel, modifier: Modifier = Modifier){
    val searchData by viewModel.searchNewsData.collectAsState()

    LazyColumn {
        items(searchData){newsItem->
            println("search data from search screen ${newsItem?.title}")
            NewsCardItem(article = newsItem!!)
        }
    }
}


@Preview
@Composable
fun SearchScreenPreview(){
    val navController = NavController(LocalContext.current)
    val viewModel:SearchViewModel = viewModel()
    SearchScreen(navController, viewModel)
}