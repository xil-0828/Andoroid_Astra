package com.xil.astra.data.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth

object SupabaseProvider {

    val client = createSupabaseClient(
        supabaseUrl = "https://uwslzazuagjzbfnmfvft.supabase.co",
        supabaseKey = "YOUR_ANON_KEY"
    ) {
        install(Auth)
    }
}
