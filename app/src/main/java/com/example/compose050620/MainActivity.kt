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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose050620.ui.login.LoginPage
import com.example.compose050620.ui.lottie.LottiePage
import com.example.compose050620.ui.movie.MoviePage
import com.example.compose050620.ui.movie.MovieViewModel
import com.example.compose050620.ui.profile.UserProfile
import com.example.compose050620.ui.tab.TabPage
import com.example.compose050620.ui.tab.tabPage
import com.example.compose050620.ui.theme.Compose050620Theme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose050620Theme(content = {
                val movieViewModel = MovieViewModel()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Router.MAIN) {
                    composable(Router.MAIN) { MainPage(navController) }
                    composable(Router.PROFILE) { UserProfile() }
                    composable(Router.MOVIE_LIST) { MoviePage(movieViewModel) }
                    composable(Router.LOGIN, listOf(navArgument("msg") { defaultValue = "hello" })) { it ->
                        val arguments = it.arguments
                        val msg = arguments?.getString("msg") ?: ""
                        LoginPage(navController = navController, msg)
                    }
                    composable(Router.TAB_PAGE) {
                        TabPage(navController = navController)
                    }
                    tabPage(navController)
                    composable(Router.LOTTIE) {
                        LottiePage()
                    }
                }
            })
        }
    }
}

//创建所有的路由
object Router {
    const val MAIN = "main"
    const val LOGIN = "login"
    const val PROFILE = "profile"
    const val MOVIE_LIST = "movieList"
    const val MOVIE_DETAIL = "movieDetail"
    const val MOVIE_COMMENT = "movieComment"
    const val MOVIE_SEARCH = "movieSearch"
    const val MOVIE_COLLECTION = "movieCollection"
    const val MOVIE_FAVORITE = "movieFavorite"
    const val MOVIE_WATCHLIST = "movieWatchlist"

    const val TAB_PAGE = "tabPage"
    const val TAB_PAGE_1 = "tabPage1"
    const val TAB_PAGE_2 = "tabPage2"
    const val TAB_PAGE_3 = "tabPage3"
    const val TAB_PAGE_4 = "tabPage4"

    const val LOTTIE = "lottie"

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MainPage(navController: NavHostController) {
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
                    navController.navigate(Router.LOGIN)
                }) {
                    Text(text = "跳转到登录页面")
                }

                OutlinedButton(onClick = {
                    val bundle = Bundle().apply {
                        putString("msg", "New MSg")
                    }
//                    navController.navigate("destination", bundle)
//                    navController.navigate(Router.LOGIN)
                    val findNode = navController.graph.findNode(Router.LOGIN) ?: return@OutlinedButton
                    navController.navigate(findNode.id, bundle)
                }) {
                    Text(text = "跳转到登录页面-------long")
                }
                //按钮居左显示
                Button(
                    onClick = {
                        navController.navigate(Router.MOVIE_LIST)
                    },
                ) {
                    Text(
                        text = "文本靠左显示,电影列表",
                    )
                }

                Button(onClick = {
                    navController.navigate(Router.TAB_PAGE)
                }) {
                    Text(text = "点击进入Tab页面")
                }

                Button(onClick = {
                    navController.navigate(Router.LOTTIE)
                }) {
                    Text(text = "Lottie 动画")
                }

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
        MainPage(rememberNavController())
    }
}