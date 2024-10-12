package com.example.newsapp.api

import com.example.newsapp.api.model.CategorySourceResponse
import com.example.newsapp.api.model.NewsSourceByCategoryResponse
import com.example.newsapp.api.model.NewsSourceResponse
import com.example.newsapp.api.model.SearchNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Where will place all urls for api (Methods)
interface NewsService {

    @GET("top-headlines/sources")
    fun getSourcesBasedOnCategory(
        @Query("category") category: String,
        @Query("apiKey") api_key : String = "cfbdb104ea384558b5df3642c9b44917"
    ): Call<NewsSourceByCategoryResponse>

    @GET("top-headlines")
    fun getNewsBySource(
        @Query("sources") source : String,
        @Query("apiKey") api_key : String = "cfbdb104ea384558b5df3642c9b44917"
    ):Call<NewsSourceResponse>

    @GET("everything")
    fun searchOnNews(
        @Query("q") query : String,
        @Query("apiKey") api_key : String = "cfbdb104ea384558b5df3642c9b44917"
    ): Call<SearchNewsResponse>
}