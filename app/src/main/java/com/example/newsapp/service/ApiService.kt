package com.example.newsapp.service

import com.example.newsapp.News
import com.example.newsapp.model.BookResponse
import retrofit2.Call
import retrofit2.http.GET

const val NEWS_API_KEY = "4593c7e64b9e4ab4b52115d1b6bedcd1"
const val BOOKS_API_KEY = "AIzaSyBJehk3g62TjUTwVTbthZAAZziWplK_G6o"
interface ApiService {
    @GET("top-headlines?country=in&apiKey=$NEWS_API_KEY")
    fun getTopHeadlines(): Call<News>

    @GET("volumes?q=Science&key=${BOOKS_API_KEY}")
    fun getScienceBooks(): Call<BookResponse>
}