package com.example.newsapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.constants.ApplicationTitle
import com.example.newsapp.search.SearchViewModel
import com.example.newsapp.ui.theme.Green_Card
import com.example.newsapp.ui.theme.White_Main
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationTapBar(
    title : String,
    drawerState: DrawerState,
    navController: NavController,
    viewModel: SearchViewModel,
    isSearch:Boolean,
    closeSearch : ()->Unit,
    openSearch : ()->Unit,
    modifier: Modifier){
    val scope = rememberCoroutineScope()
    var searchValue by remember {
        mutableStateOf("")
    }


    CenterAlignedTopAppBar(
        title = {
            if (isSearch){
                TextField(
                    value = searchValue,
                    onValueChange = {
                        searchValue = it
                    },
                    placeholder = { Text(
                        text = "Search Article",
                        color = Green_Card
                    ) },
                    trailingIcon = {
                        Image(
                            painter = if(searchValue.isNotEmpty()) painterResource(id = R.drawable.green_done_ic) else painterResource(id = R.drawable.green_search_ic),
                            contentDescription = "Search Icon",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(horizontal = 5.dp)
                                .clickable {
                                    if (searchValue.isNotEmpty()) {
                                        viewModel.fetchNews(searchValue)
                                    }
                                }
                        )
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.green_close_ic),
                            contentDescription = "Close Icon",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(horizontal = 5.dp)
                                .clickable {
                                    closeSearch()
                                    searchValue = ""
                                    navController.navigate(ApplicationTitle.HOME_TITLE)
                                }
                        )
                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp, 0.dp)
                )
            }
            else{
                Text(
                    text = title,
                    color = White_Main,
                    fontSize = 22.sp,
                )
            }

        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),
        modifier = Modifier
            .background(Green_Card, RoundedCornerShape(0.dp, 0.dp,50.dp,50.dp)),
        navigationIcon = {
            if(!isSearch){
                Image(
                    painter = painterResource(id = R.drawable.menu_ic),
                    contentDescription = "Drawer Icon",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                )
            }
        },
        actions = {
            if(!isSearch){
                Image(
                    painter = painterResource(id = R.drawable.search_ic),
                    contentDescription = "Search Icon",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable {
                            openSearch()
                            navController.navigate(ApplicationTitle.SEARCH_ROUTES)
                        }
                )
            }

        }
    )
}

@Preview
@Composable
fun ApplicationTabBarPreview(){
    val navController = NavController(LocalContext.current)
    val viewModel:SearchViewModel = viewModel()
    var isSearch = true
    ApplicationTapBar(title = "Home", drawerState = DrawerState(DrawerValue.Closed),navController,viewModel, isSearch, closeSearch = {isSearch= false}, openSearch = {isSearch=true}, modifier = Modifier)
}