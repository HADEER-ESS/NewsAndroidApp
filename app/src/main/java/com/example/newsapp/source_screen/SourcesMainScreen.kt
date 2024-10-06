package com.example.newsapp.source_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.CategorySourceResponse
import com.example.newsapp.api.model.NewsSourceByCategoryResponse
import com.example.newsapp.api.model.NewsSourceResponse
import com.example.newsapp.api.model.SourceCustome
import com.example.newsapp.news_details.AricleDetailsViewModel
import com.example.newsapp.ui.theme.Green_Card
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.White_Main
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SourcesMainScreen(navController: NavController, categoryType:String?, viewModel:AricleDetailsViewModel , modifier: Modifier = Modifier){
    var newsSourceName by remember {
        mutableStateOf<List<SourceCustome>>(emptyList())
    }
    LaunchedEffect(Unit) {
        ApiManager.getNewsFromCategorySelection().getSourcesBasedOnCategory(categoryType!!)
            .enqueue(object : Callback<NewsSourceByCategoryResponse> {
                override fun onResponse(
                    call: Call<NewsSourceByCategoryResponse>,
                    response: Response<NewsSourceByCategoryResponse>
                ) {
                    val responseData = response.body()?.sources
                    var data = mutableListOf<SourceCustome>()
                    for(source in responseData!!){
                        data.add(SourceCustome(source?.id!! , source.name!!))
                    }
                    println("customize data $data")
                    newsSourceName =  data.distinct()
                }
                override fun onFailure(call: Call<NewsSourceByCategoryResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("Response from source " , "res ${t.message}")
                }

            })
    }
    Box(
        modifier = Modifier
            .paint(painterResource(id = R.drawable.app_pattern), contentScale = ContentScale.Crop)
            .fillMaxSize()
            .fillMaxSize()
            .padding(0.dp, 20.dp)
    ){
        CreateScrollableHorizontalTabRow(newsSourceName, navController, viewModel)

    }

}

@Preview(apiLevel = 33, showSystemUi = true, showBackground = true)
@Composable
fun SourcesMainScreenPreview(){
    val navController = NavController(context = LocalContext.current)
    val data = AricleDetailsViewModel()
    SourcesMainScreen(navController ,"sport",data, modifier = Modifier.fillMaxSize())
}