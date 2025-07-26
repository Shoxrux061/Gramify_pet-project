package uz.shoxrux.main.presentation.screens.main.chats

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uz.shoxrux.core.ui.components.ErrorComponent
import uz.shoxrux.core.ui.components.LoadingBar
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.ui.theme.LocalAppTypography
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
            modifier = Modifier.fillMaxSize()
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

            AppSearchBar(
                value = searchValue.value,
                onValueChange = {
                    searchValue.value = it
                },
                hint = stringResource(R.string.search)
            )

            Spacer(Modifier.height(20.dp))

            if (!chats.isNullOrEmpty()) {
                LazyColumn {
                    items(chats.size){

                    }
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

@Composable
fun AppSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String
) {

    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (isFocused) {
                colors.textTitle
            } else {
                colors.brandPrimary
            }
        ),
        colors = CardDefaults.cardColors(colors.background)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            Icon(
                painter = painterResource(uz.shoxrux.core.R.drawable.ic_search),
                tint = colors.brandPrimary,
                contentDescription = null
            )

            Spacer(Modifier.width(10.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = value,
                textStyle = typography.bodyMedium,
                singleLine = true,
                placeholder = {
                    Text(
                        text = hint,
                        style = typography.hintText
                    )
                },
                onValueChange = {
                    onValueChange(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colors.background,
                    unfocusedContainerColor = colors.background,
                    focusedIndicatorColor = colors.transparent,
                    unfocusedIndicatorColor = colors.transparent
                ),
                interactionSource = interactionSource
            )

        }
    }
}