package ramble.sokol.tgm_inverse.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import ramble.sokol.tgm_inverse.theme.background_line_item
import ramble.sokol.tgm_inverse.theme.background_line_item_white
import tgminverse.composeapp.generated.resources.Res
import tgminverse.composeapp.generated.resources.icon_active
import tgminverse.composeapp.generated.resources.mont_regular
import tgminverse.composeapp.generated.resources.test_photo

@Composable
fun TasksGetPayment(
    title: String,
    price: String,
) {

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(background_line_item),
        contentAlignment = Alignment.TopCenter
    ){

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){

                Image(
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp),
                    painter = painterResource(Res.drawable.test_photo),
                    contentDescription = "iconActive"
                )

                Spacer(modifier = Modifier.padding(horizontal = 6.dp))

                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.mont_regular)),
                        fontWeight = FontWeight(700),
                        color = Color.White,
                    )
                )

            }

            Spacer(modifier = Modifier.padding(top = 14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .clip(RoundedCornerShape(27.dp))
                    .background(background_line_item_white)
            )

            Spacer(modifier = Modifier.padding(top = 14.dp))

            ButtonCollect(
                text = "Collect +${price} BB"
            ){

            }

        }

    }

}