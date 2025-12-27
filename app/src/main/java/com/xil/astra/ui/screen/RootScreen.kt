import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.xil.astra.ui.screen.LoginScreen
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.auth.auth
@Composable
fun RootScreen() {
    val lifecycleOwner = LocalLifecycleOwner.current
    var session by remember { mutableStateOf<UserSession?>(null) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                session = SupabaseProvider.client.auth.currentSessionOrNull()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    if (session == null) {
        LoginScreen()
    } else {

    }
}
