package com.example.newsapp

data class News(var articles: ArrayList<Article>? = null)

data class Article(
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null
)