package com.xil.astra.ui.screen

import androidx.compose.runtime.*
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RootScreen() {

    var session by remember { mutableStateOf<UserSession?>(null) }

    LaunchedEffect(Unit) {
        session = SupabaseProvider.client.auth.currentSessionOrNull()

        SupabaseProvider.client.auth.sessionStatus
            .collectLatest {
                session =
                    SupabaseProvider.client.auth.currentSessionOrNull()
            }
    }

    if (session == null) {
        LoginScreen()
    } else {

    }
}
