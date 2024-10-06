package com.example.newsapp.news_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.model.ArticlesItem

class AricleDetailsViewModel : ViewModel() {
    // Mutable state to hold the article
     var articleInfo by mutableStateOf<ArticlesItem?>(null)
        private set

    // Function to set the article
    fun setArticle(article: ArticlesItem) {
        articleInfo = article
    }
}