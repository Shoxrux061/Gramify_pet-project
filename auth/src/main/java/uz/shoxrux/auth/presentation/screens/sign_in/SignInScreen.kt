package uz.shoxrux.auth.presentation.screens.sign_in

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.shoxrux.auth.domain.model.SignUpModel
import uz.shoxrux.auth.presentation.components.AlternativeAuthMethods
import uz.shoxrux.auth.presentation.navigation.REGISTER_ROUTE
import uz.shoxrux.auth.presentation.screens.sign_up.createAccount
import uz.shoxrux.core.ui.components.AppButton
import uz.shoxrux.core.ui.components.AppTextField
import uz.shoxrux.core.ui.components.ErrorComponent
import uz.shoxrux.core.ui.components.LoadingBar
import uz.shoxrux.core.ui.components.OrDivider
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.ui.theme.LocalAppTypography

@Composable
fun SignInScreen(navController: NavController) {

    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    val viewModel = hiltViewModel<SignInViewModel>()

    val isSuccess = viewModel.isSuccess.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val isSubmitted = remember { mutableStateOf(false) }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {

        }
    }

    LaunchedEffect(error) {
        if (error != null) {
            Toast.makeText(navController.context, error, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        if (error != null) {
            ErrorComponent(
                error = error
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
        ) {

            Text(
                text = "GRAMIFY",
                style = typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Sign in",
                style = typography.headerLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))


            AppTextField(
                hint = "E-Mail",
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                isEmpty = isSubmitted.value && email.value.isBlank()
            )

            Spacer(modifier = Modifier.height(20.dp))

            AppTextField(
                hint = "Password",
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                isEmpty = isSubmitted.value && password.value.isBlank()
            )

            AppButton(
                modifier = Modifier.padding(vertical = 30.dp),
                onClick = {
                    isSubmitted.value = true
                    if (
                        email.value.isNotEmpty()
                        && password.value.isNotEmpty()
                    ) {
                        viewModel.signIn(login = email.value, password = password.value)
                    }
                },
                text = "Sign In"
            )

            OrDivider(text = "Or Sign in with")

            Spacer(modifier = Modifier.height(30.dp))

            AlternativeAuthMethods(
                onFacebookPressed = {

                },
                onGooglePressed = {

                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        navController.navigate(REGISTER_ROUTE) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account yet?",
                    style = typography.bodySmall
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "Sign Up",
                    style = typography.textButtonSmall
                )
            }
        }

        if (isLoading) {
            LoadingBar()
        }

    }
}