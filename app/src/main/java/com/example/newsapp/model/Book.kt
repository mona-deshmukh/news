package com.example.newsapp.model

data class BookResponse(var items: ArrayList<Book>)
data class Book(
    var selfLink: String?,
    var volumeInfo: VolumeInfo?
)

data class VolumeInfo(
    var title: String,
    var publisher: String,
    var infoLink: String?,
    var imageLinks: ImageLinks?,
    var description: String
)

data class ImageLinks(var thumbnail: String?)
