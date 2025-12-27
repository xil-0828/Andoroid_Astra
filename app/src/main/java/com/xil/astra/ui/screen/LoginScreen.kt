package com.xil.astra.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.xil.astra.data.auth.AuthManager
import com.xil.astra.data.auth.AuthResult
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() {

    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
}