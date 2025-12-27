package com.xil.astra.data.auth

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.MessageDigest
import java.util.UUID

sealed interface AuthResult {
    data object Success : AuthResult
    data class Error(val message: String?) : AuthResult
}

class AuthManager(
    private val context: Context
) {

    private val credentialManager = CredentialManager.create(context)

    // nonce（必須）
    private fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val digest = MessageDigest
            .getInstance("SHA-256")
            .digest(rawNonce.toByteArray())

        return digest.joinToString("") { "%02x".format(it) }
    }

    fun signInWithGoogle(): Flow<AuthResult> = flow {
        try {
            val nonce = createNonce()

            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId(
                    "744793862440-rf3qn1bh3pn6triu3e2kio1fdm9s47ja.apps.googleusercontent.com"
                )
                .setNonce(nonce)
                .setFilterByAuthorizedAccounts(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            val googleCredential = GoogleIdTokenCredential
                .createFrom(result.credential.data)

            val idToken = googleCredential.idToken

            SupabaseProvider.client.auth.signInWith(IDToken) {
                provider = Google
                this.idToken = idToken
            }

            emit(AuthResult.Success)

        } catch (e: Exception) {
            Log.e("AUTH", "Google login failed", e)
            emit(AuthResult.Error(e.message))
        }
    }

    suspend fun logout() {
        SupabaseProvider.client.auth.signOut()
    }
}
