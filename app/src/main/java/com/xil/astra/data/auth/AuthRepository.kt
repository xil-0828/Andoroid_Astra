package com.xil.astra.data.auth

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google

class AuthRepository {

    suspend fun loginWithGoogle() {
        SupabaseProvider.client.auth.signInWith(Google)
    }

    fun currentSession() =
        SupabaseProvider.client.auth.currentSessionOrNull()

    suspend fun logout() {
        SupabaseProvider.client.auth.signOut()
    }
}
