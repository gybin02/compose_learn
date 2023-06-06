package com.example.compose050620.ui.movie

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.compose050620.ui.bean.Result

//使用MovieViewModel 填充页面
@Composable
fun MoviePage(viewModel: MovieViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "电影") },
                backgroundColor = MaterialTheme.colors.onPrimary
            )
        }

    ) { it ->
        when (val result = viewModel.movieState.value) {
            is Result.Success -> {
                val list = result.data ?: return@Scaffold
                LazyColumn(Modifier.padding(it)) {
                    items(list, itemContent = {
                        MovieItem(it)
                    })
                }
            }

            is Result.Error -> {
                Text(text = "准备失败")
            }

            is Result.Loading -> {
                Column {
                    //ProgressBar
                    CircularProgressIndicator()
                    Text(text = "加載中")
                }

            }
        }

    }
}

@Composable
fun MovieItem(it: Movie) {
    //显示图片，标题，和描述
    Row() {
        //显示网络图片
        AsyncImage(model = it.image, contentDescription = "电影封面")
        Column {
            Text(text = it.name)
            Text(text = it.desc)
        }
    }
}
