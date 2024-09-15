package net.hwyz.iov.mp.app.ui.page.my.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.ui.page.RouteName
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.bar.TopTitleBar
import net.hwyz.iov.mp.app.ui.widget.button.RoundedCornerButton
import net.hwyz.iov.mp.app.ui.widget.dialog.ConfirmDialog
import net.hwyz.iov.mp.app.utils.AppUserUtil
import net.hwyz.iov.mp.app.utils.RouteUtils

/**
 * 我的 - 设置页面
 */
@Composable
fun MySettingView(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    isLogged: Boolean
) {
    var showDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            TopTitleBar(title = "设置", onBack = { navCtrl.popBackStack() })
            if (isLogged) {
                Spacer(modifier = Modifier.height(20.dp))
                RoundedCornerButton(
                    text = "退出登录"
                ) {
                    showDialog.value = true
                }
                ConfirmDialog(
                    showDialog = showDialog.value,
                    onDismiss = { showDialog.value = false },
                    onConfirm = {
                        AppUserUtil.onLogOut()
                        RouteUtils.navTo(
                            navCtrl = navCtrl,
                            destinationName = RouteName.MY
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MySettingViewPreview() {
    MySettingView(rememberNavController(), rememberScaffoldState(), true)
}