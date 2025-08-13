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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.ui.theme.LocalAppTypography
import uz.shoxrux.main.domain.model.CommentModel

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
    isLiked: Boolean,
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
                    painter = if (isLiked)
                        painterResource(R.drawable.ic_liked) else painterResource(R.drawable.ic_not_liked),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentBottomSheet(
    state: OpenedCommentState,
    onDismiss: () -> Unit,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    val imeInsets = WindowInsets.ime.asPaddingValues()
    val colors = LocalAppColors.current
    val sheetState = rememberModalBottomSheetState()

    if (state.isOpened) {
        ModalBottomSheet(
            containerColor = colors.background,
            sheetState = sheetState,
            onDismissRequest = { onDismiss() },
        ) {
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = colors.brandPrimary)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
                ) {
                    items(state.comments.size) { index ->
                        CommentItem(state.comments[index])
                        Spacer(Modifier.height(30.dp))
                    }

                    if(state.comments.isEmpty()){
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ){

                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "No comments",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                        fontSize = 16.sp,
                                        color = colors.textHeadline
                                    )
                                )

                            }
                        }
                    }

                    item {
                        CommentTextField(
                            value = state.commentInput,
                            onValueChange = onValueChange,
                            onFocusChanged = {},
                            onSend = onSend
                        )
                    }

                    item {
                        Box(modifier = Modifier.height(imeInsets.calculateTopPadding()))
                    }
                }
            }
        }
    }
}

@Composable
fun CommentItem(
    comment: CommentModel
) {

    val colors = LocalAppColors.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Image(

            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.person_placholder),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Text(
                maxLines = 1,
                text = comment.authorName,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    color = colors.textHeadline,
                    fontSize = 14.sp
                )
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = comment.content,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                    color = colors.textBody,
                    fontSize = 16.sp
                )
            )


        }

    }

}

@Composable
fun CommentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onSend: () -> Unit
) {
    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    val focusModifier = Modifier.onFocusChanged { onFocusChanged(it.isFocused) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.person_placholder),
            contentDescription = null
        )

        TextField(
            modifier = focusModifier
                .weight(1f),
            value = value,
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                color = colors.textBody,
                fontSize = 16.sp
            ),
            placeholder = {
                Text(
                    text = "Type anything...",
                    style = typography.hintText
                )
            },
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors.background,
                unfocusedContainerColor = colors.background,
                focusedIndicatorColor = colors.transparent,
                unfocusedIndicatorColor = colors.transparent
            )
        )

        IconButton(
            onClick = {
                if (value.isNotBlank()) {
                    onSend.invoke()
                }
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_send),
                contentDescription = null,
                tint = colors.brandPrimary
            )
        }

    }

}

@Composable
fun screenWidthInDp(): Int {
    val configuration = LocalConfiguration.current
    return (configuration.screenWidthDp)
}