package uz.shoxrux.main.presentation.screens.main.post

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.components.AppButton
import uz.shoxrux.core.ui.components.AppLargeTextField
import uz.shoxrux.core.ui.components.ErrorComponent
import uz.shoxrux.core.ui.components.LoadingBar
import uz.shoxrux.core.utils.bitmapToByteArray
import uz.shoxrux.main.domain.model.post.PostModel

@Composable
fun PostPage(
    navController: NavHostController,
    viewModel: PostViewModel
) {

    val colors = LocalAppColors.current

    val captionText = viewModel.captionText.collectAsState()

    val selectedImageUri = viewModel.selectedImageUri.collectAsState()

    val isSuccess = viewModel.isSuccess.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        if (it == null) return@rememberLauncherForActivityResult
        viewModel.setUri(it)
    }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            viewModel.clear()
            Toast.makeText(navController.context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

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
                    .clickable {
                        launcher.launch(
                            PickVisualMediaRequest(
                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                    .aspectRatio(1f),
            ) {

                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = rememberAsyncImagePainter(selectedImageUri.value),
                    contentDescription = null
                )

                Image(
                    painter = painterResource(R.drawable.image_grid),
                    contentDescription = null
                )
            }

            Spacer(Modifier.weight(1f))

            AppLargeTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                hint = "Add caption...",
                value = captionText.value,
                onValueChange = {
                    viewModel.updateCaptionText(it)
                },
                isEmpty = false
            )

            Spacer(Modifier.weight(1f))

            AppButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    if (captionText.value.isNotBlank() && selectedImageUri.value != null) {
                        val postModel = PostModel(
                            content = captionText.value
                        )
                        val byteArray =
                            bitmapToByteArray(navController.context, uri = selectedImageUri.value!!)
                        viewModel.addPost(
                            postModel = postModel,
                            byteArray = byteArray
                        )
                    }
                },
                text = "Post"
            )

            Spacer(Modifier.height(20.dp))

        }

        if (isLoading) {
            LoadingBar()
        } else if (error != null) {
            ErrorComponent(error)
        }

    }
}