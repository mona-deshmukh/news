package com.example.newsapp.service

import com.example.newsapp.News
import retrofit2.Call
import retrofit2.http.GET

const val API_KEY = "4593c7e64b9e4ab4b52115d1b6bedcd1"
interface NewsApiService {
    @GET("top-headlines?country=in&apiKey=$API_KEY")
    fun getTopHeadlines(): Call<News>

    @GET("everything?q=entertainment&from=2021-05-19&sortBy=popularity&apiKey=$API_KEY")
    fun getEntertainmentNews(): Call<News>
}