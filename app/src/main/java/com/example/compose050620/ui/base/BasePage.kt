package com.example.compose050620.ui.base

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//通用的页面基类，包含loading状态，错误状态，空数据状态
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenericPage(
    title: String,
    viewModel: GenericViewModel,
    onNavigateUp: () -> Unit,
    content: @Composable () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            when (state) {
                is GenericState.Loading -> {
                    LoadingState()
                }

                is GenericState.Empty -> {
                    EmptyState()
                }

                is GenericState.Error -> {
                    ErrorState((state as GenericState.Error).message)
                }

                is GenericState.Data -> {
                    content()
                }
            }
        }
    )
}

@Composable
fun LoadingState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "No data available")
    }
}

@Composable
fun ErrorState(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Error: $message")
    }
}

class GenericViewModel : ViewModel() {
    private val _state = MutableStateFlow<GenericState>(GenericState.Loading)
    val state: StateFlow<GenericState> = _state

    fun loadData() {
        viewModelScope.launch {
            try {
                val data = fetchData()
                if (data.isEmpty()) {
                    _state.value = GenericState.Empty
                } else {
                    _state.value = GenericState.Data(data)
                }
            } catch (e: Exception) {
                _state.value = GenericState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun fetchData(): List<String> {
        // Perform network request and return data
        delay(1000)
        return listOf("Item 1", "Item 2", "Item 3")
    }
}

sealed class GenericState {
    object Loading : GenericState()
    object Empty : GenericState()
    data class Error(val message: String) : GenericState()
    data class Data(val data: List<String>) : GenericState()
}

data class ItemData(val title: String, val description: String)

@Composable
fun GenericPageChild(navController: NavController) {
    val scope = rememberCoroutineScope()
    val viewModel: GenericViewModel = viewModel()

    GenericPage(
        title = "Child Page",
        viewModel = viewModel,
        onNavigateUp = { navController.popBackStack() },
        content = {
            LazyColumn {
                val value = viewModel.state.value as GenericState.Data
                items(value.data.size) { item ->
                    Text(text = value.data[item], modifier = Modifier.padding(16.dp))
                }
            }
        }
    )

    LaunchedEffect(key1 = viewModel) {
        viewModel.loadData()
    }
}


//@Composable
//fun ChildScreen(navController: NavController) {
//    val viewModel: GenericViewModel<ItemData> = viewModel()
//    val state = viewModel.state.collectAsState()
//
//    GenericPage(
//        title = "Child Screen",
//        viewModel = viewModel,
//        onNavigateUp = { navController.popBackStack() },
//        content = {
//                val data = (state.value as ChildState.Success).data
//                Column {
//                    Text(text = data.name)
//                    Text(text = data.description)
//                }
//            }
//        }
//    )
//
//    LaunchedEffect(true) {
//        viewModel.loadData()
//    }
//}

