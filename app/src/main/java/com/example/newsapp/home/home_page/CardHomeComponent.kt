package com.example.newsapp.home.home_page

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.home.Category
import com.example.newsapp.ui.theme.Brown_Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.os.bundleOf
import androidx.navigation.NavController

@Composable
fun CardHomeComponent(cardData : Category, navController: NavController){
    val category = stringResource(id = cardData.title)
        Card(
            colors = CardDefaults.cardColors(
                containerColor = cardData.color
            ),
            onClick = { navigateToNewsSourceScreen(category, navController) },
            shape = RoundedCornerShape(25.dp,25.dp,0.dp,25.dp),
            border = BorderStroke(1.dp, Color.White),
            modifier = Modifier.size(148.dp, 171.dp)
        )
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = cardData.image),
                    contentDescription = "Card Image",
                    modifier = Modifier.size(132.dp,116.dp)
                )
                Text(
                    text = stringResource(id = cardData.title),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }

        }
}

fun navigateToNewsSourceScreen(categoryTitle : String, navController: NavController){
    println("new category title $categoryTitle")
    navController.navigate("sourcesPage/${categoryTitle}")
}

@Preview(showBackground = true)
@Composable
fun CardHomeComponentPreview(){
    val navController = NavController(context = LocalContext.current)
    CardHomeComponent(
        Category(1,1, 1, Brown_Card),
        navController
    )
}


