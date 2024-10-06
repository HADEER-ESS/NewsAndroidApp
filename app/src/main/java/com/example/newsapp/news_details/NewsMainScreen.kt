package com.example.newsapp.news_details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.ui.theme.Black_Main
import com.example.newsapp.ui.theme.Gray_Text_Main
import com.example.newsapp.ui.theme.White_Main

@Composable
fun NewsMainDetailsScreen(navController: NavController, viewModel: AricleDetailsViewModel, modifier: Modifier){
    val data = viewModel.articleInfo
//    Box (modifier = modifier.fillMaxSize()){
        ArticleDetailsCard(data = data)
//    }

}

@Composable
fun ArticleDetailsCard(data:ArticlesItem?){
    val context = LocalContext.current // Get the current context
    LazyColumn(
        modifier = Modifier
            .paint(painterResource(id = R.drawable.app_pattern), contentScale = ContentScale.Crop)
            .fillMaxSize()
    ){
        item {
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(White_Main),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ){
                AsyncImage(
                    model = data?.urlToImage,
                    contentDescription = "Article image",
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                )
                Text(
                    text = data?.author ?: "UnKnown",
                    fontSize = 14.sp,
                    color = Gray_Text_Main,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(6.dp,2.dp)
                )
                Text(
                    text = data?.title ?: "",
                    fontSize = 20.sp,
                    color = Gray_Text_Main,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(6.dp,2.dp)
                )
                Text(
                    text = data?.publishedAt ?: "",
                    fontSize = 14.sp,
                    color = Gray_Text_Main,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(6.dp,2.dp)
                )
                Text(
                    text = data?.content!!,
                    fontSize = 22.sp,
                    color = Gray_Text_Main,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(6.dp,2.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp)
                        .clickable {
                            navigateToBrowser(context, data.url!!)
                                   },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ){
                    Text(
                        text = "View Full Article",
                        color = Black_Main,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.more_ic ),
                        contentDescription = "View more icon",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }

}

fun navigateToBrowser( context : Context, url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(browserIntent)

}

@Preview
@Composable
fun NewsMainScreenPreview(){
    val navController = NavController(LocalContext.current)
    val data = AricleDetailsViewModel()
    NewsMainDetailsScreen(navController,data, modifier = Modifier.fillMaxSize())
}