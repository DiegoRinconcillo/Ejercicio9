package com.mexiti.picphotoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mexiti.picphotoapp.ui.PicApp
import com.mexiti.picphotoapp.ui.theme.CatPhotoAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatPhotoAppTheme {
                // A surface container using the 'background' color from the theme
                PicApp()
            }
        }
    }
}

