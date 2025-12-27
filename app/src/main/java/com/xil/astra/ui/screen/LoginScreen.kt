package com.xil.astra.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.xil.astra.data.auth.AuthManager
import com.xil.astra.data.auth.AuthResult
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() {

    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {
                authManager.signInWithGoogle()
                    .collect { result ->
                        if (result is AuthResult.Error) {
                            println("Login failed: ${result.message}")
                        }
                    }
            }
        }
    ) {
        Text("Googleでログイン")
    }
}
