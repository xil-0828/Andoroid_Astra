package com.xil.astra.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.xil.astra.data.auth.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() {
    val scope = rememberCoroutineScope()
    val authRepository = AuthRepository()

    Button(
        onClick = {
            scope.launch {
                authRepository.loginWithGoogle()
            }
        }
    ) {
        Text("Googleでログイン")
    }
}

