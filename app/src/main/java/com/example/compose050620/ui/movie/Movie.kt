package com.example.compose050620.ui.movie

//电影模型
data class Movie(
    var id: Int = 0,
    var name: String = "",
    var image: String = "",
    var desc: String = "",
    var score: String = "",
)