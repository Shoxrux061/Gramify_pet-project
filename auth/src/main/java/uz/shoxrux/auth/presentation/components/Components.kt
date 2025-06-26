package uz.shoxrux.auth.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.shoxrux.auth.R
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.ui.theme.LocalAppTypography

@Composable
fun AlternativeAuthMethods(onGooglePressed: () -> Unit, onFacebookPressed: () -> Unit) {

    val colors = LocalAppColors.current
    val typography = LocalAppTypography.current

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Card(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .clickable {
                    onGooglePressed.invoke()
                },
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(width = 1.dp, color = colors.gray),
            colors = CardDefaults.cardColors(colors.background)

        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Google",
                    style = typography.bodySmall
                )

            }

        }

        Spacer(modifier = Modifier.width(20.dp))

        Card(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .clickable {
                    onFacebookPressed.invoke()
                },
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(width = 1.dp, color = colors.gray),
            colors = CardDefaults.cardColors(colors.background)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_facebook),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Facebook",
                    style = typography.bodySmall
                )

            }

        }

    }

}