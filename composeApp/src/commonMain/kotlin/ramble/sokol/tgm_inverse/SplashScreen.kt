package ramble.sokol.tgm_inverse

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import dev.inmo.tgbotapi.requests.bot.GetMyCommands.Companion.scope
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.web.dom.Main
import ramble.sokol.tgm_inverse.theme.background_splash
import tgminverse.composeapp.generated.resources.PressStart2P_Regular
import tgminverse.composeapp.generated.resources.Res
import tgminverse.composeapp.generated.resources.icon_logo_splash_screen
import tgminverse.composeapp.generated.resources.image_background_splash_screen
import tgminverse.composeapp.generated.resources.mont_regular



class SplashScreen : Screen {

    @Composable
    override fun Content() {
        val scope  = rememberCoroutineScope()
        val token = "7181082387:AAFTQy6Vz8jbRr_-xHcEN1jjt9sz88kdsEY"
        val chatId = "1132709306" // ID пользователя или группы
        val message = "Hello from Kotlin Compose Multiplatform!"
        val navigator = LocalNavigator.current
        val transition = rememberInfiniteTransition(label = "")
        val alpha by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 3000
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        LaunchedEffect(
            key1 = true
        ) {
            scope.launch {
                sendMessage(token, chatId, message)
            }
            delay(3000L)
            navigator?.push(MainMenuScreen())
//            if (settings.loadToken() != null){
//                navigator?.push(AreasScreen())
//            }else{
//                navigator?.push(LoginScreen())
//            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background_splash)
                .windowInsetsPadding(WindowInsets.safeDrawing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(Res.drawable.image_background_splash_screen),
                    contentDescription = "imageSplashScreen"
                )

                Image(
                    modifier = Modifier
                        .height(51.dp)
                        .fillMaxWidth()
                        .alpha(alpha = alpha),
                    painter = painterResource(Res.drawable.icon_logo_splash_screen),
                    contentDescription = "imageSplashScreen"
                )

            }

        }

    }

    @OptIn(InternalAPI::class)
    suspend fun sendMessage(token: String, chatId: String, message: String) {
        val client = HttpClient {

            install(HttpTimeout) {
                requestTimeoutMillis = 5000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                //add this accept() for accept Json Body or Raw Json as Request Body
                accept(ContentType.Application.Json)
            }
        }

        try {
            val response: String = client.post("https://api.telegram.org/bot$token/sendMessage") {
                contentType(ContentType.Application.Json)
                body = mapOf(
                    "chat_id" to chatId,
                    "text" to message
                )
            }.toString()
            println("Response: $response")
        } catch (e: Exception) {
            println("Error sending message: ${e.message}")
        } finally {
            client.close()
        }
    }

}