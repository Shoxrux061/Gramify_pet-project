package uz.shoxrux.main.presentation.screens.main.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.components.AppButton

@Composable
fun PostPage() {

    val colors = LocalAppColors.current

    val captionText = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null
                    )
                }

                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = stringResource(R.string.create_post),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                        color = colors.textHeadline,
                        fontSize = 18.sp
                    )
                )

            }

            Spacer(Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f),
                ){

                Image(
                    painter = painterResource(R.drawable.image_grid),
                    contentDescription = null
                )
            }

            Spacer(Modifier.weight(1f))

            /*AppLargeTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                hint = "Add caption...",
                value = captionText.value,
                onValueChange = {
                    captionText.value = it
                },
                isEmpty = false
            )*/

            Spacer(Modifier.weight(1f))

            AppButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {

                },
                text = "Post"
            )

            Spacer(Modifier.height(20.dp))


        }
    }
}