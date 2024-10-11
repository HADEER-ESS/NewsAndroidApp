package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.constants.ApplicationTitle
import com.example.newsapp.drawer.DrawerItemSheet
import com.example.newsapp.home.ApplicationTapBar
import com.example.newsapp.home.CategoryDataList
import com.example.newsapp.home.home_page.CardHomeComponent
import com.example.newsapp.news_details.AricleDetailsViewModel
import com.example.newsapp.news_details.NewsMainDetailsScreen
import com.example.newsapp.setting.SettingsScreenView
import com.example.newsapp.source_screen.SourcesMainScreen
import com.example.newsapp.ui.theme.Gray_Text_Main
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val currentBackstackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackstackEntry?.destination
            val newsCategory = currentBackstackEntry?.arguments?.getString("newsCategory")


            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            println("current distination is ${currentDestination?.route}")
            val title = when(currentDestination?.route){
                ApplicationTitle.HOME_TITLE -> "News App"
                "${ApplicationTitle.SOURCES_ROUTES}/{newsCategory}" -> newsCategory ?: "Category"
                ApplicationTitle.DETAILS_ROUTES -> "News Title"
                ApplicationTitle.SETTING_ROUTES -> "Settings"
                else -> "News App"
            }
            LaunchedEffect(key1 = navController.currentBackStackEntryAsState().value?.destination?.route) {
//                title.value = when(navController.currentBackStackEntryAsState().value?.destination?.route){
//                    "homePage" -> "News App"
//                    else -> ""
//                }
                
            }
            NewsAppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = { DrawerItemSheet(navController, drawerState, scope) }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            ApplicationTapBar(title = title,drawerState, modifier = Modifier)
                        }
                    ) { innerPadding ->
                        MainScreensSet(navController,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize())
                    }
                }

            }
        }
    }
}



@Composable
fun MainScreensSet(navController: NavHostController, modifier: Modifier){
    val articleData : AricleDetailsViewModel = viewModel()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ApplicationTitle.HOME_TITLE
    ) {
        composable(ApplicationTitle.HOME_TITLE) {
            HomePage(navController, modifier = modifier)
        }
        composable(
            route = "${ApplicationTitle.SOURCES_ROUTES}/{newsCategory}",
            arguments = listOf(navArgument("newsCategory"){
                type = NavType.StringType
            })
        ) {
            SourcesMainScreen(navController,it.arguments?.getString("newsCategory"), articleData, modifier = modifier)
        }
        composable(ApplicationTitle.DETAILS_ROUTES)
        {
            NewsMainDetailsScreen(navController,articleData, modifier= modifier)
        }
        composable(ApplicationTitle.SETTING_ROUTES)
        {
            SettingsScreenView(navController, modifier = modifier)
        }
    }
}

@Composable
fun HomePage( navController:NavController, modifier: Modifier = Modifier) {
    val categoryCardData = CategoryDataList.categoryData
    Column(
        modifier = Modifier
            .paint(painterResource(id = R.drawable.app_pattern), contentScale = ContentScale.Crop)
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = "Pick your category\n of interest",
            color = Gray_Text_Main ,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(35.dp, 36.dp,0.dp, 20.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier
                .weight(1f)
                .padding(18.dp, 9.dp)
        ) {
            items(categoryCardData.size){
                item -> CardHomeComponent(cardData = categoryCardData[item], navController)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePagePreview() {
    NewsAppTheme {
        HomePage(navController = NavController(LocalContext.current))
    }
}