package com.example.newsapp.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.SearchNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

   private val _searchNewsData = MutableStateFlow<List<ArticlesItem?>>(emptyList())
    val searchNewsData: StateFlow<List<ArticlesItem?>> = _searchNewsData.asStateFlow()


    fun fetchNews(searchItem : String){
        viewModelScope.launch {
            val newsData = getNewsBySearchItem(searchItem)
            println("data is get and set in temporary variable $newsData")
            _searchNewsData.value = newsData
            println("set in permanent variable ${_searchNewsData.value}")
        }
    }

    private suspend fun getNewsBySearchItem(text : String): List<ArticlesItem?> {
        return try {
            withContext(Dispatchers.IO){
                val response = ApiManager.getNewsFromCategorySelection().searchOnNews(text)
                response.execute().body()?.articles!!
            }
        }
        catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }
}