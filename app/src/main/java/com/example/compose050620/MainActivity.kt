package com.example.compose050620

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose050620.ui.theme.Compose050620Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose050620Theme(content = { MainPage() })
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MainPage() {
    Scaffold {
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally  //居中显示
            ) {
                Text(
                    text = "Hello Android!",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Button(onClick = {
//            startActivity(Intent(this, MainActivity2::class.java))
                }) {
                    Text(text = "跳转到登录页面")
                }

                OutlinedButton(onClick = {
                }) {
                    Text(text = "跳转到登录页面-------long")
                }
                Text(
                    text = "文本靠左显示",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )

                Text(
                    text = "文本靠右显示",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )

                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "间距20dp"
                )

                Row(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "文本1",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "文本2",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "文本3",
                        modifier = Modifier.weight(1f)
                    )
                }

                //Row 左边文字weight =1 右边固定 文字
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "文本1",
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "文本3",
                        style = MaterialTheme.typography.h1
                    )
                }

            }


            Text(
                text = "右上角",
                textAlign = TextAlign.End,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, top = 10.dp)

            )

//            Text(text = "屏幕居中", modifier = Modifier.align(Alignment.Center))
//            Text(text = "右边中间", modifier = Modifier.align(Alignment.CenterEnd))
//            Text("左边中间", modifier = Modifier.align(Alignment.CenterStart))
//            Text("右下角", modifier = Modifier.align(Alignment.BottomEnd))
//            Text("左下角", modifier = Modifier.align(Alignment.BottomStart))


        }


    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose050620Theme {
        MainPage()
    }
}