package uz.shoxrux.main.presentation.screens.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.theme.AppTypography
import uz.shoxrux.core.ui.theme.LocalAppColors

@Composable
fun StoryItem(
    modifier: Modifier = Modifier
) {

    val colors = LocalAppColors.current

    Box(
        modifier = modifier
            .height(65.dp)
    ) {
        Card(
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape),
            shape = RoundedCornerShape(100),
            border = BorderStroke(
                width = 2.dp,
                color = colors.brandPrimary
            )
        ) {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .size(28.dp)
                .background(colors.background)
                .padding(4.dp)
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(R.drawable.ic_add_story),
                contentDescription = null,
            )
        }
    }
}


@Composable
fun HomePageItem(
    imageUrl: String,
    likeCount: String,
    commentCount: String,
    sharesCount: String,
    title: String,
    postTime: String,
    onLikeClicked: () -> Unit,
    onCommentClicked: () -> Unit,
    onShareClicked: () -> Unit
) {

    val colors = LocalAppColors.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.background)
            .padding(vertical = 10.dp)
    ) {


        Row(verticalAlignment = Alignment.CenterVertically) {

            Card(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.person_placholder),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(1.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Username",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.nunito_semi_bold))
                )
            )

        }


        Spacer(modifier = Modifier.height(10.dp))


        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenWidthInDp().dp),
            contentDescription = "",
            model = imageUrl,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(colors.transparentGray)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)

            ) {
                Icon(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .clickable {
                            onLikeClicked.invoke()
                        }
                        .padding(8.dp),
                    tint = colors.textTitle,
                    painter = painterResource(R.drawable.ic_like),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = likeCount,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        fontSize = 14.sp,
                        color = colors.textTitle
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                Icon(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .clickable {
                            onCommentClicked.invoke()
                        }
                        .padding(8.dp),
                    tint = colors.textTitle,
                    painter = painterResource(R.drawable.ic_chat),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = commentCount,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        fontSize = 14.sp,
                        color = colors.textTitle
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)

            ) {
                Icon(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .clickable {
                            onShareClicked.invoke()
                        }
                        .padding(8.dp),
                    tint = colors.textTitle,
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = sharesCount,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        fontSize = 14.sp,
                        color = colors.textTitle
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(colors.transparentGray)
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 5.dp),
            text = title,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            color = colors.textHeadline,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = postTime,
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontSize = 12.sp,
            color = colors.textTitle.copy(0.8f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
fun screenWidthInDp(): Int {
    val configuration = LocalConfiguration.current
    return (configuration.screenWidthDp)
}