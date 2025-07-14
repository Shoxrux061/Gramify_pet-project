package uz.shoxrux.main.presentation.screens.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.main.presentation.screens.main.home.screenWidthInDp

@Composable
fun PostItem(image: String) {

    Box(
        modifier = Modifier
            .size((screenWidthInDp() / 3).dp)
            .background(LocalAppColors.current.transparentGray)
    ) {

        AsyncImage(
            modifier = Modifier
                .padding(1.dp)
                .background(Color.Gray),
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}