package com.xil.astra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.xil.astra.ui.screen.RootScreen
import com.xil.astra.ui.theme.AstraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AstraTheme {
                RootScreen()
            }
        }
    }
}
