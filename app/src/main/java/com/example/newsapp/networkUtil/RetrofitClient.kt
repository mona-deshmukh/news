package com.example.newsapp.networkUtil

import com.example.newsapp.service.ApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var NEWS_BASE_URL:String="https://newsapi.org/v2/"
    private var BOOKS_BASE_URL:String="https://www.googleapis.com/books/v1/"
    val getNewsClient: ApiService
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val client = OkHttpClient.Builder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl(NEWS_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiService::class.java)

        }

    val getBooksClient: ApiService
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val client = OkHttpClient.Builder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BOOKS_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiService::class.java)

        }

}