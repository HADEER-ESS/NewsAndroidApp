package com.example.newsapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationTapBar(title : String, modifier: Modifier){
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
                modifier = Modifier.padding(12.dp)
            )
        }
    )
}

@Preview
@Composable
fun ApplicationTabBarPreview(){
    ApplicationTapBar(title = "Home", modifier = Modifier)
}