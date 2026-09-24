package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.mycity.ui.MyCityApp
import com.example.mycity.ui.MyCityViewModel
import com.example.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MyCityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityTheme {
                MyCityApp(viewModel = viewModel)
            }
        }
    }
}