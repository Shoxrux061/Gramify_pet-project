package uz.shoxrux.main.presentation.screens.main.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uz.shoxrux.core.ui.components.AppTextField
import uz.shoxrux.core.ui.components.ErrorComponent
import uz.shoxrux.core.ui.components.LoadingBar
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.main.R

@Composable
fun ChatsPage(navController: NavHostController, viewModel: ChatsViewModel) {

    val chats = viewModel.chats.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    val colors = LocalAppColors.current
    val searchValue = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.chats),
                style = TextStyle(
                    fontFamily = FontFamily(Font(uz.shoxrux.core.R.font.nunito_semi_bold)),
                    fontSize = 18.sp,
                    color = colors.textHeadline
                )
            )

            Spacer(Modifier.height(20.dp))

            AppTextField(
                value = searchValue.value,
                onValueChange = {
                    searchValue.value = it
                },
                hint = stringResource(R.string.search),
                isEmpty = false
            )

            Spacer(Modifier.height(20.dp))

            if (!chats.isNullOrEmpty()) {
                LazyColumn {
                    items(chats.size) {
                        ChatItem(
                            avatarUrl = chats[it].partnerAvatarUrl ?: "",
                            userName = chats[it].partnerName,
                            message = chats[it].lastMessage,
                            unreadCount = chats[it].unreadCount,
                            sendTime = chats[it].lastMessageTimestamp.toString()
                        )
                    }
                }
            } else {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "No chats yet.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(uz.shoxrux.core.R.font.nunito_semi_bold)),
                            color = colors.textHeadline,
                            fontSize = 18.sp
                        )
                    )

                }

            }

        }

        if (isLoading) {
            LoadingBar()
        } else if (error != null) {
            ErrorComponent(error)
        }

    }
}