package com.xil.astra

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.xil.astra.ui.screen.LoginScreen
import com.xil.astra.ui.theme.AstraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AstraTheme {
                LoginScreen()
            }
        }
    }

    // ⭐ OAuth callback を受け取るために必須
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
