package com.example.newsapp.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.source_screen.NewsCardItem
import com.example.newsapp.ui.theme.Green_Card

@Composable
fun SearchScreen(navController: NavController,viewModel: SearchViewModel, modifier: Modifier = Modifier){
    val searchData by viewModel.searchNewsData.collectAsState()
    if (searchData.isEmpty()){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ){
            Image(
                painter = painterResource(id = R.drawable.empty_state),
                contentDescription = "No News for this keyword",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(4.dp, 12.dp)
            )
            Text(
                text = stringResource(id = R.string.search_empty_state),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Green_Card,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp, 12.dp)
            )
        }
    }
    else{
        LazyColumn {
            items(searchData){newsItem->
                println("search data from search screen ${newsItem?.title}")
                NewsCardItem(article = newsItem!!)
            }
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