import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth

object SupabaseProvider {

    val client = createSupabaseClient(
        supabaseUrl = "https://uwslzazuagjzbfnmfvft.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InV3c2x6YXp1YWdqemJmbm1mdmZ0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM3NDQyNTIsImV4cCI6MjA3OTMyMDI1Mn0.Rr9mBZkqtQXBX41Qmc7UTjeEKA1m_zi3GuFQu1jH7pM"
    ) {
        install(Auth) {
            scheme = "com.xil.astra"
            host = "login-callback"
        }
    }
}
