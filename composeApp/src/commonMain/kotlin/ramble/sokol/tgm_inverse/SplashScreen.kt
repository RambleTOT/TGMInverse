package ramble.sokol.tgm_inverse

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen


class SplashScreen : Screen {

    @Composable
    override fun Content() {
        Text(text = "SplashScreen")
    }

}