package com.example.newsapp.home

import com.example.newsapp.R
import com.example.newsapp.ui.theme.Brown_Card
import com.example.newsapp.ui.theme.Dark_Blue_Card
import com.example.newsapp.ui.theme.Light_Blue_Card
import com.example.newsapp.ui.theme.Pink_Card
import com.example.newsapp.ui.theme.Red_card
import com.example.newsapp.ui.theme.Yellow_Card

object CategoryDataList {
    val categoryData = listOf(
//        Brown
        Category(
            1,
            R.string.business_category,
            R.drawable.bussines,
            Brown_Card
        ),
        Category(
            2,
            R.string.entertainment_category,
            R.drawable.sports,
            Dark_Blue_Card
        ),
        Category(
            3,
            R.string.general_category,
            R.drawable.environment,
            Red_card
        ),
        Category(
            4,
            R.string.health_category,
            R.drawable.health,
            Pink_Card
        ),
        Category(
            5,
            R.string.science_category,
            R.drawable.science,
            Yellow_Card
        ),
        Category(
            6,
            R.string.sport_category,
            R.drawable.sports,
            Red_card
        ),
        Category(
            7,
            R.string.technology_category,
            R.drawable.politics,
            Light_Blue_Card
        ),
    )
}