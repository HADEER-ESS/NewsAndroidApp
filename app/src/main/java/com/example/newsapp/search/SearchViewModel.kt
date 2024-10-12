package com.example.newsapp.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.ArticlesItem
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
            _searchNewsData.value = newsData
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