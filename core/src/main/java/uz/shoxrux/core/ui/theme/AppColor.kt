package uz.shoxrux.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColor(
    val background: Color,
    val brandPrimary: Color,
    val brandSecondary: Color,
    val brandTertiary: Color,
    val border: Color,
    val cardColor: Color,
    val white: Color,
    val black: Color,
    val gray: Color,
    val transparent: Color,
    val semiTransparent: Color,
    val transparentGray: Color,
    val divider: Color,
    val textHeadline: Color,
    val textTitle: Color,
    val textBody: Color
)

val LocalAppColors = staticCompositionLocalOf<AppColor> {
    error("No AppColor provided")
}