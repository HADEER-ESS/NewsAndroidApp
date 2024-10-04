package com.example.newsapp.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Where I can Access API Service Interface
object ApiManager {
    lateinit var retrofit:Retrofit

    init {
        initRetrofit()
    }

//    HTTP logging interface use ok_http_client
    private fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
//        how I want to console (log) the result from api call
        val httpLoggingInterceptor = HttpLoggingInterceptor{
            message -> Log.e("API" , message)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()

        return okHttpClient
    }

    private fun initRetrofit(){
        retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    fun getNewsFromCategorySelection() : NewsService{
        return retrofit.create(NewsService::class.java)
    }
}