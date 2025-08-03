package uz.shoxrux.main.presentation.screens.main.reels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.R
import uz.shoxrux.main.domain.model.wiki.Page

@Composable
fun ReelsItem(page: Page?) {

    val colors = LocalAppColors.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = page?.original?.source,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color(0x9A1C2A2B))
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 20.dp)
            ) {
                IconButton(

                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_not_liked),
                        contentDescription = null,
                        tint = colors.textHeadline
                    )
                }
                IconButton(

                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = null,
                        tint = colors.textHeadline
                    )
                }
            }

            Text(
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = colors.textHeadline,
                    fontFamily = FontFamily(Font(R.font.nunito_medium))
                ),
                text = page?.title ?: "",
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            Text(
                overflow = TextOverflow.Ellipsis,
                maxLines = 15,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colors.textBody,
                    fontFamily = FontFamily(Font(R.font.nunito_regular))
                ),
                text = page?.extract ?: "",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(20.dp))

            Button(
                colors = ButtonDefaults.buttonColors(colors.transparentGray),
                border = BorderStroke(
                    width = 1.dp,
                    color = colors.brandPrimary
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        vertical = 5.dp,
                        horizontal = 10.dp
                    ),
                onClick = {

                }
            ) {
                Text(
                    text = "Read more",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                        color = colors.brandTertiary
                    )
                )
            }
        }
    }
}