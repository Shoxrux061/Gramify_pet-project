package uz.shoxrux.core.ui.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.ui.theme.LocalAppTypography

@Composable
fun AppTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEmpty: Boolean
) {
    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.background),
        border = BorderStroke(
            width = 1.dp,
            color = if (isFocused) {
                colors.textTitle
            } else if (isEmpty) {
                colors.error1
            } else {
                colors.brandTertiary
            }
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
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

@Composable
fun AppButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {

    val typography = LocalAppTypography.current
    val colors = LocalAppColors.current

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(colors.brandPrimary),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            style = typography.buttonText
        )
    }

}


@Composable
fun OrDivider(
    text: String
) {

    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(colors.textBody)
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = text,
            style = typography.bodySmall
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(colors.textBody)
        )


    }

}

@Composable
fun LoadingBar() {

    val colors = LocalAppColors.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.semiTransparent)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(55.dp),
            color = colors.brandSecondary
        )
    }

}


@Composable
fun ErrorComponent(
    title: String = "",
    error: String = "",
) {

    val colors = LocalAppColors.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .padding(20.dp)
    ) {

        Column {
            Text(
                text = title,
                style = TextStyle(
                    color = colors.textTitle,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_regular))
                )
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = error,
                style = TextStyle(
                    color = colors.textBody,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_bold))
                )
            )

        }
    }
}

@Composable
fun AppLargeTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEmpty: Boolean
) {
    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(colors.background),
        border = BorderStroke(
            width = 1.dp,
            color = if (isFocused) {
                colors.textTitle
            } else if (isEmpty) {
                colors.error1
            } else {
                colors.brandTertiary
            }
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                value = value,
                textStyle = typography.bodyMedium,
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