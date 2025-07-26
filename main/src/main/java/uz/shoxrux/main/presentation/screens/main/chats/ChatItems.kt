package uz.shoxrux.main.presentation.screens.main.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.theme.LocalAppColors

@Composable
fun ChatItem(
    avatarUrl:String,
    userName:String,
    message:String,
    unreadCount:Int,
    sendTime:String
) {

    val colors = LocalAppColors.current

    Box(Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            AsyncImage(
                model = avatarUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.person_placholder),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colors.gray)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = userName,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                        fontSize = 18.sp,
                        color = colors.textTitle
                    )
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = message,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        fontSize = 16.sp,
                        color = colors.brandPrimary
                    )
                )

            }

            Column {

                Text(
                    text = sendTime,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        fontSize = 14.sp,
                        color = colors.semiTransparent
                    )
                )

                Spacer(Modifier.height(10.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_done),
                    tint = colors.black,
                    contentDescription = null
                )

            }

        }
    }
}

@Composable
fun NotifItem(
    countText: String
) {


    Box(
        modifier = Modifier
            .size(24.dp)
            .background(LocalAppColors.current.brandPrimary)

    ) {
        Text(
            text = countText,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                fontSize = 14.sp,
                color = LocalAppColors.current.white
            )
        )
    }

}