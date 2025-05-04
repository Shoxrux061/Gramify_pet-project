package uz.shoxrux.gramify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import uz.shoxrux.core.ui.theme.LocalAppColors
import uz.shoxrux.core.ui.theme.LocalAppTypography
import uz.shoxrux.gramify.ui.navHost.AppNavHost
import uz.shoxrux.gramify.ui.theme.Gramify_petprojectTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Gramify_petprojectTheme {

                val colors = LocalAppColors.current
                val typography = LocalAppTypography.current

                Scaffold(
                    contentWindowInsets = WindowInsets.statusBars,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.background)
                ) { paddingValues ->

                    Text("Hello world!", style = typography.headlineMedium)
                    /*  AppNavHost(paddingValues)*/
                }
            }
        }
    }
}