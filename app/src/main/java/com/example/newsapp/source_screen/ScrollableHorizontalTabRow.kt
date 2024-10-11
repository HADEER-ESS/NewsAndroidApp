package com.example.newsapp.source_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.NewsSourcesViewModel
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.SourceCustome
import com.example.newsapp.news_details.AricleDetailsViewModel
import com.example.newsapp.ui.theme.Green_Card
import com.example.newsapp.ui.theme.White_Main
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CreateScrollableHorizontalTabRow(sourceData : List<SourceCustome>, navController : NavController, viewModel: AricleDetailsViewModel){
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    var loadingRenderData = remember {
        mutableStateOf(true)
    }
    val newsBySourceViewModel = NewsSourcesViewModel()
    val scope = rememberCoroutineScope()
    var newsData  by remember {
        mutableStateOf<List<ArticlesItem?>>(emptyList())
    }

    LaunchedEffect(sourceData) {
        if(sourceData.isNotEmpty()){
            loadingRenderData.value = true
            newsData = newsBySourceViewModel.getNewsDataBySourceId(sourceData[0].id!!)
            loadingRenderData.value = false
        }
    }


    val selectdTabModifier = Modifier
        .background(Green_Card, CircleShape)
        .padding(2.dp)
        .border(2.dp, Green_Card, CircleShape)
    val unselectedTabModifier = Modifier
        .background(White_Main, CircleShape)
        .padding(2.dp)
        .border(2.dp, Green_Card, CircleShape)

    if(loadingRenderData.value){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator() // Loader shown while data is loading
        }
    }
    else{
        Column {
            ScrollableTabRow(
                selectedTabIndex = selectedIndex,
                edgePadding = 0.dp,
                indicator = {},
                divider = {},
            ) {
                sourceData.forEachIndexed { index, sourceName ->
                    Tab(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            scope.launch {
                                newsData = getNewsDataBySource(sourceName.id!!)
                            }
                        },
                        modifier = if(selectedIndex == index) selectdTabModifier else unselectedTabModifier
                    ) {
                        RowSourceItem(selectedIndex , index , sourceName.name!!)
                    }
                }
            }

            NewsCardView(newsData, navController,viewModel)
        }
    }



}

@Composable
fun RowSourceItem(selectedInted : Int, currentIntex : Int, sourceName : String){
    Text(
        text = sourceName,
        textAlign = TextAlign.Center,
        color = if(selectedInted == currentIntex) White_Main else Green_Card,
        fontSize = 14.sp,
        modifier = Modifier.padding(21.dp , 8.dp)
    )
}

suspend fun getNewsDataBySource(sourceName : String):List<ArticlesItem?>{
    return try {
        withContext(Dispatchers.IO){
            val responseData = ApiManager.getNewsFromCategorySelection().getNewsBySource(sourceName)
            responseData.execute().body()?.articles!!
        }
    }
    catch (e : Exception){
        e.printStackTrace()
        emptyList()
    }
}

@Preview
@Composable
fun RowSourceItemPreview(){
    val data = listOf(
        SourceCustome("abc-news", "ABC News"),
        SourceCustome("bbc-news", "BBC News"),
        SourceCustome("bbc-sport", "BBC Sport"),
    )
    val navController = NavController(LocalContext.current)
    val view = AricleDetailsViewModel()
    CreateScrollableHorizontalTabRow(sourceData = data, navController, view)
}