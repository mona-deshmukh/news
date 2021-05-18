package com.example.newsapp.networkUtil

import com.example.newsapp.service.NewsApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var BASE_URL:String="https://newsapi.org/v2/"
    val getClient: NewsApiService
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val client = OkHttpClient.Builder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(NewsApiService::class.java)

        }
}