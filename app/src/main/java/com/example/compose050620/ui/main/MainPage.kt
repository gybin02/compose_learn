package com.example.compose050620.ui.main

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.example.compose050620.Greeting
import com.example.compose050620.MainActivity2
import com.example.compose050620.ui.theme.Compose050620Theme


enum class Page(val path: String) {
    MAIN("main"),
    LOGIN("login"),
    PROFILE("profile")
}

//创建主页面
@Composable
fun MainPage() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //居中显示
            Text(text = "主页面", style = MaterialTheme.typography.h1)
            Button(onClick = {
                gotoPage(Page.LOGIN.path)
            }) {
                Text(text = "跳转到登录页面")
            }
            //调到Profile页面
            OutlinedButton(onClick = {
                gotoPage(Page.PROFILE.path)
            }) {
                Text(text = "跳转到Profile页面")
            }
        }
    }
}


fun gotoPage(target: String) {
    //TODO
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose050620Theme {
        MainPage()
    }
}

