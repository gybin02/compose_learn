package com.example.compose050620.ui.tab


import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose050620.Router

const val TAG = "TabPage"

//使用底部导航组件，实现页面
data class NavigationItem(
    val title: String, val icon: ImageVector, val route: String
)

val items = listOf(
    NavigationItem("home", Icons.Default.Home, Router.TAB_PAGE_1),
    NavigationItem("favorites", Icons.Default.Favorite, Router.TAB_PAGE_2),
    NavigationItem("profile", Icons.Default.Person, Router.TAB_PAGE_3),
    NavigationItem("search", Icons.Default.Search, Router.TAB_PAGE_4)
)


@Composable
fun TabPage(navController: NavHostController) {
    Scaffold(bottomBar = {
        bottomBar(navController = navController)
    })
    { paddingValues ->
        Log.d(TAG, "TabPage: $paddingValues")
        //TODO 这样写有问题， 一个APP 只能有一个NavHost
//        NavHost(navController = navController,Router.TAB_PAGE_1, modifier = Modifier.padding(paddingValues)){
//            tabPage(navController)
//        }
    }
}

@Composable
fun bottomBar(navController: NavHostController) {
    BottomNavigation(
//        backgroundColor = Color.White, elevation = 8.dp
    ) {
        val route = navController.currentBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive = false
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}

//@Composable
//fun navHost(navController: NavHostController, paddingValues: PaddingValues) {
//    NavHost(navController = navController, startDestination = Router.TAB_PAGE_1, modifier = Modifier.padding(paddingValues)) {
//        tabPage(navController)
//
//    }
//}

fun NavGraphBuilder.tabPage(navController: NavHostController) {
    composable(Router.TAB_PAGE_1) {
        val route1 = it.destination.route
        TabPage1(navController = navController, route = route1)
    }
    composable(Router.TAB_PAGE_2) {
        val route1 = it.destination.route
        TabPage1(navController = navController, route = route1)
    }
    composable(Router.TAB_PAGE_3) {
        val route1 = it.destination.route
        TabPage1(navController = navController, route = route1)
    }
    composable(Router.TAB_PAGE_4) {
        val route1 = it.destination.route
        TabPage4(navController = navController, route = route1)
    }
}

@Composable
fun TabPage1(navController: NavController, route: String?) {
    Column {
        Text(text = "text $route")
        Button(onClick = {
//            navController.popBackStack()
            navController.navigate(Router.TAB_PAGE_2)
        }) {
            Text(text = "返回")
        }
    }
}

@Composable
fun TabPage4(navController: NavController, route: String?) {
    Column {
        Text(text = "text $route")
        Button(onClick = {
//            navController.popBackStack()
            navController.navigate(Router.LOGIN)
        }) {
            Text(text = "返回")
        }
    }
}

