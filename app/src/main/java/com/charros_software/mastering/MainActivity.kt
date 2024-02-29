package com.charros_software.mastering

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.charros_software.mastering.navigation.AppNavigation
import com.charros_software.mastering.ui.theme.MasteringTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasteringTheme {
                AppNavigation()
            }
        }
    }
}
