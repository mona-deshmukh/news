package com.example.newsapp

data class News(var articles: ArrayList<Articles>? = null)

data class Articles(
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null
)