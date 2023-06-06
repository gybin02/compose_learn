package com.example.compose050620.ui.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose050620.ui.bean.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _movieState = mutableStateOf<Result<List<Movie>>>(Result.Loading())
    val movieState = _movieState

    init {
        getMovie()
    }

    //获取电影
    fun getMovie() {
        viewModelScope.launch {
            delay(3000)
            //构造出4调Movie数据
            val movies = mutableListOf<Movie>()
            for (i in 1..4) {
                movies.add(Movie(i, "男士$i", "https://www.baidu.com/img/bd_logo1.png"))
            }
            _movieState.value = Result.Success(movies)

        }
    }

}