package net.hwyz.iov.mp.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 应用主题
 */
@Stable
object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    enum class Theme {
        Light, Dark
    }
}

/**
 * 应用颜色集
 */
@Stable
class AppColors(
    themeUi: Color, // 主题风格颜色
    background: Color, // 背景颜色
    fontPrimary: Color, // 主要文本颜色
    fontSecondary: Color, // 次要文本颜色
) {
    var themeUi: Color by mutableStateOf(themeUi)
        internal set
    var background: Color by mutableStateOf(background)
        private set
    var fontPrimary: Color by mutableStateOf(fontPrimary)
        private set
    var fontSecondary: Color by mutableStateOf(fontSecondary)
        private set
}

/**
 * 白天应用颜色集
 */
private val LightAppColors = AppColors(
    themeUi = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
    fontPrimary = Color(0xFF1A171B),
    fontSecondary = Color(0xFF8E8E8E),
)

/**
 * 夜晚应用颜色集
 */
private val DarkAppColors = AppColors(
    themeUi = black1,
    background = black2,
    fontPrimary = black3,
    fontSecondary = black4,
)

var LocalAppColors = compositionLocalOf {
    LightAppColors
}


@Composable
fun AppTheme(
    theme: AppTheme.Theme = AppTheme.Theme.Light,
    content: @Composable () -> Unit
) {

    val targetColors = when (theme) {
        AppTheme.Theme.Light -> LightAppColors
        AppTheme.Theme.Dark -> DarkAppColors
    }

    val systemUiCtrl = rememberSystemUiController()
    systemUiCtrl.setStatusBarColor(targetColors.themeUi)
    systemUiCtrl.setNavigationBarColor(targetColors.themeUi)
    systemUiCtrl.setSystemBarsColor(targetColors.themeUi)

    ProvideWindowInsets {
        CompositionLocalProvider(LocalAppColors provides targetColors, content = content)
    }

}