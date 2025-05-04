package uz.shoxrux.gramify.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.shoxrux.core.ui.theme.AppColor
import uz.shoxrux.core.ui.theme.AppTypography
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.ui.theme.LocalAppTypography
import uz.shoxrux.gramify.R

private val DarkColorScheme = AppColor(
    background = Color(0xFF0A0E0F),
    brandPrimary = Color(0xFF006175),
    brandSecondary = Color(0xFF1A2B2D),
    brandTertiary = Color(0xFF66B9BA),
    border = Color(0xFF1E2A2A),
    cardColor = Color(0xFF1C2A2B),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    gray = Color(0xFF999999),
    transparent = Color(0x02FFFFFF),
    transparentGray = Color(0x40656565),
    divider = Color(0x40383838),
    semiTransparent = Color(0x4DFFFFFF),
    textTitle = Color(0xFFD4FBFF),
    textHeadline = Color(0xFFF0FBFC),
    textBody = Color(0xFFFCFFFF)
)

private val LightColorScheme = AppColor(
    background = Color(0xFFFFFFFF),
    brandPrimary = Color(0xFF006175),
    brandSecondary = Color(0xFFD2F1F3),
    brandTertiary = Color(0xFF7AD2D2),
    border = Color(0xFFE0E0E0),
    cardColor = Color(0xFFF4FCFD),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    gray = Color(0xFF666666),
    transparent = Color(0x02FFFFFF),
    transparentGray = Color(0x20656565),
    divider = Color(0x20666666),
    semiTransparent = Color(0x4D000000),
    textTitle = Color(0xFF151F1F),
    textHeadline = Color(0xFF091A1C),
    textBody = Color(0xFF0A1111)
)


@Composable
fun Gramify_petprojectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val materialColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    val appColors = if (darkTheme) DarkColorScheme else LightColorScheme


    val appTypography = AppTypography(
        headlineLarge = TextStyle(
            fontSize = 24.sp,
            color = appColors.textHeadline,
            fontFamily = FontFamily(Font(R.font.nunito_medium))
        ),
        headlineMedium = TextStyle(
            fontSize = 20.sp,
            color = appColors.textHeadline,
            fontFamily = FontFamily(Font(R.font.nunito_medium))
        ),
        headlineSmall = TextStyle(
            fontSize = 16.sp,
            color = appColors.textHeadline,
            fontFamily = FontFamily(Font(R.font.nunito_medium))
        ),

        titleLarge = TextStyle(
            fontSize = 22.sp,
            color = appColors.textTitle,
            fontFamily = FontFamily(Font(R.font.nunito_regular))
        ),
        titleMedium = TextStyle(
            fontSize = 18.sp,
            color = appColors.textTitle,
            fontFamily = FontFamily(Font(R.font.nunito_regular))
        ),
        titleSmall = TextStyle(
            fontSize = 14.sp,
            color = appColors.textTitle,
            fontFamily = FontFamily(Font(R.font.nunito_regular))
        ),
        bodyLarge = TextStyle(
            fontSize = 18.sp,
            color = appColors.textBody,
            fontFamily = FontFamily(Font(R.font.nunito_bold))
        ),
        bodyMedium = TextStyle(
            fontSize = 16.sp,
            color = appColors.textBody,
            fontFamily = FontFamily(Font(R.font.nunito_bold))
        ),
        bodySmall = TextStyle(
            fontSize = 14.sp,
            color = appColors.textBody,
            fontFamily = FontFamily(Font(R.font.nunito_bold))
        ),
        hintText = TextStyle(
            fontSize = 18.sp,
            color = appColors.gray,
            fontFamily = FontFamily(Font(R.font.nunito_regular))
        ),
        textButton = TextStyle(
            fontSize = 18.sp,
            color = appColors.brandTertiary,
            fontFamily = FontFamily(Font(R.font.nunito_semi_bold))
        )

    )
    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalAppTypography provides appTypography
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = Typography(),
            content = content
        )
    }
}
