package uz.shoxrux.main.presentation.screens.main.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.components.AppButton

@Composable
fun ProfilePage(navController: NavController, viewModel: ProfileViewModel) {

    val profileData = viewModel.profileData.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    val colors = LocalAppColors.current

    Box(modifier = Modifier.fillMaxSize()) {

        if (profileData != null) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "My Profile",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.Center),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                            color = colors.textHeadline,
                            fontSize = 18.sp
                        )
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier
                                .size(70.dp)
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
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = profileData.fullName,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                                color = colors.textHeadline,
                                fontSize = 18.sp
                            )
                        )
                    }
                }

                item {
                    Text(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
                        text = profileData.bio,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.nunito_medium)),
                            color = colors.textBody,
                            fontSize = 16.sp
                        )
                    )
                }

                item {
                    AppButton(
                        onClick = {},
                        text = "Edit Profile",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(3) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = when (it) {
                                        0 -> profileData.posts.size.toString()
                                        1 -> "1"
                                        2 -> "3"
                                        else -> "error"
                                    }, style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                        color = colors.textBody,
                                        fontSize = 18.sp
                                    )
                                )
                                Text(
                                    text = when (it) {
                                        0 -> "Posts"
                                        1 -> "Followers"
                                        2 -> "Follows"
                                        else -> "error"
                                    },
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                        color = colors.textBody,
                                        fontSize = 18.sp
                                    )
                                )
                            }

                            if (it != 2) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp)
                                        .background(Color.Black)
                                )
                            }
                        }
                    }
                }

                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        userScrollEnabled = false,
                        modifier = Modifier
                            .heightIn(max = 10000.dp)
                            .padding(0.dp)
                    ) {
                        items(profileData.posts.size) {
                            PostItem(profileData.posts[it].imageUrl)
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        } else if (error != null) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
            ) {
                Text(
                    text = error,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                        color = colors.textTitle,
                        fontSize = 18.sp
                    )
                )
            }

        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}