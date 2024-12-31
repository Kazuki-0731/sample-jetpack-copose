package com.example.sample_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.sample_jetpack_compose.ui.App
import com.example.sample_jetpack_compose.ui.CatsApp

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val appContainer = (application as App).container
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            CatsApp(appContainer, widthSizeClass)
        }
    }
}