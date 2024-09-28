package com.example.newsapp.home

import androidx.compose.ui.graphics.Color
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Brown_Card
import com.example.newsapp.ui.theme.Dark_Blue_Card
import com.example.newsapp.ui.theme.Light_Blue_Card
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.Pink_Card
import com.example.newsapp.ui.theme.Red_card
import com.example.newsapp.ui.theme.Yellow_Card

object CategoryDataList {
    val categoryData = listOf(
//        Brown
        Category(
            1,
            "Bussiness",
            R.drawable.bussines,
            Brown_Card
        ),
        Category(
            2,
            "Entertainment",
            R.drawable.sports,
            Dark_Blue_Card
        ),
        Category(
            3,
            "General",
            R.drawable.environment,
            Red_card
        ),
        Category(
            4,
            "Health",
            R.drawable.health,
            Pink_Card
        ),
        Category(
            5,
            "Science",
            R.drawable.science,
            Yellow_Card
        ),
        Category(
            6,
            "Sports",
            R.drawable.sports,
            Red_card
        ),
        Category(
            7,
            "Technology",
            R.drawable.politics,
            Light_Blue_Card
        ),
    )
}