package com.example.compose050620.ui.lottie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import androidx.compose.ui.platform.LocalContext
import com.example.compose050620.R

//测试Lottie 显示的页面

@Composable
fun LottiePage() {
    //LottieAnimationView
    //居中显示
    Box(contentAlignment = Alignment.Center) {
        LottieSection(R.raw.bag)
    }
}

@Composable
fun LottieSection(animId: Int) {
    val context = LocalContext.current
    val customView = remember { LottieAnimationView(context) }
    // Adds view to Compose
    AndroidView(
        { customView },
        modifier = Modifier.background(Color.Black)
    ) { view ->
        // View's been inflated - add logic here if necessary
        with(view) {
            setAnimation(animId)
            playAnimation()
            repeatMode = LottieDrawable.REVERSE
        }
    }
}
