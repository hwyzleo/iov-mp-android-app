package net.hwyz.iov.mp.app.module.my.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.BuildConfig
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.RouteName
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.theme.H5
import net.hwyz.iov.mp.app.utils.AppUserUtil
import net.hwyz.iov.mp.app.utils.RouteUtils
import net.hwyz.iov.mp.app.widget.bar.TopBackTitleBar
import net.hwyz.iov.mp.app.widget.button.RoundedCornerButton
import net.hwyz.iov.mp.app.widget.dialog.ConfirmDialog

/**
 * 我的 - 设置页面
 */
@Composable
fun MySettingView(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    isLogged: Boolean
) {
    val showDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            TopBackTitleBar(
                title = stringResource(R.string.setting),
                onBack = { navCtrl.popBackStack() }
            )
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                    }
            ) {
                Text(
                    text = stringResource(R.string.version),
                    fontSize = H5,
                    color = AppTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = BuildConfig.VERSION_NAME,
                    fontSize = H5,
                    color = AppTheme.colors.secondaryText
                )
            }
            Spacer(Modifier.height(20.dp))
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