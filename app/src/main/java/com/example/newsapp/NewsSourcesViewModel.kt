package com.example.newsapp

import androidx.lifecycle.ViewModel
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsSourcesViewModel : ViewModel() {

    suspend fun getNewsDataBySourceId(sourceId: String): List<ArticlesItem?> {
        return try {
            withContext(Dispatchers.IO){
                val response = ApiManager.getNewsFromCategorySelection().getNewsBySource(sourceId)
                response.execute().body()?.articles!!
            }
        }
        catch(err : Exception){
            err.printStackTrace()
            emptyList()
        }
    }
}