package com.example.newsapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Green_Card
import com.example.newsapp.ui.theme.White_Main
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationTapBar(title : String, drawerState: DrawerState, modifier: Modifier){
    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = White_Main,
                fontSize = 22.sp,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),
        modifier = Modifier.background(Green_Card, RoundedCornerShape(0.dp, 0.dp,50.dp,50.dp)),
        navigationIcon = {
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
    )
}

@Preview
@Composable
fun ApplicationTabBarPreview(){
    ApplicationTapBar(title = "Home", drawerState = DrawerState(DrawerValue.Closed), modifier = Modifier)
}