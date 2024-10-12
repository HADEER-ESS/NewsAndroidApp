package com.example.newsapp.source_screen

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.news_details.AricleDetailsViewModel
import com.example.newsapp.ui.theme.Black_Main
import com.example.newsapp.ui.theme.Gray_Text_Main
import com.example.newsapp.ui.theme.White_Main

@Composable
fun NewsCardView(data : List<ArticlesItem?>, nav: NavController, viewModel: AricleDetailsViewModel){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White_Main)
            .padding(6.dp, 4.dp)
    ){
        items(data.size){
            Card(
                modifier = Modifier
                    .padding(0.dp, 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White_Main
                ),
                onClick = { navigateToDetailsScreen(data[it]!!, nav, viewModel)},
                shape = RectangleShape,
            ){
                NewsCardItem(data[it]!!)
            }
        }
    }
}

fun navigateToDetailsScreen(data : ArticlesItem, navController: NavController, viewModel: AricleDetailsViewModel ){
    println("render article Data $data")
    viewModel.setArticle(data)
    navController.navigate("newsDetails")
}

@Composable
fun NewsCardItem(article : ArticlesItem){
    val randomUrlImage = "https://ichef.bbci.co.uk/news/1024/branded_news/49ee/live/eb559330-819c-11ef-b732-9d507d15282c.jpg"
    AsyncImage(
        model = article.urlToImage ?: randomUrlImage ,
        contentDescription = "News Content Image",
        filterQuality = FilterQuality.High,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    )
        Text(
            text =  article.author ?: "Unknown",
            fontSize = 10.sp,
            color = Gray_Text_Main,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(4.dp,0.dp)
        )
        Text(
            text = article.title ?: "unknown",
            fontSize = 14.sp,
            color = Black_Main,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(4.dp)
        )
    Text(
        text = article.publishedAt?: "unknown",
        fontSize = 13.sp,
        color = Gray_Text_Main,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 1.dp)
    )
}

@Preview
@Composable
fun NewsCardItemPreview(){
    val data = listOf(
        ArticlesItem(
            urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/49ee/live/eb559330-819c-11ef-b732-9d507d15282c.jpg",
            title = "Sharp rise in vaping among young adults who never regularly smoked",
            url = "https://www.bbc.co.uk/news/articles/c20jeey047xo",
            publishedAt = "2024-10-03T18:22:14.1967737Z",
            author = "BBC News"
        ),
        ArticlesItem(
            urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/49ee/live/eb559330-819c-11ef-b732-9d507d15282c.jpg",
            title = "Sharp rise in vaping among young adults who never regularly smoked",
            url = "https://www.bbc.co.uk/news/articles/c20jeey047xo",
            publishedAt = "2024-10-03T18:22:14.1967737Z",
            author = "BBC News"
        ),
        ArticlesItem(
            urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/49ee/live/eb559330-819c-11ef-b732-9d507d15282c.jpg",
            title = "Sharp rise in vaping among young adults who never regularly smoked",
            url = "https://www.bbc.co.uk/news/articles/c20jeey047xo",
            publishedAt = "2024-10-03T18:22:14.1967737Z",
            author = "BBC News"
        )
    )

    val navVontroller = NavController(LocalContext.current)
    val model = AricleDetailsViewModel()
    NewsCardView(data = data, navVontroller, model)
}