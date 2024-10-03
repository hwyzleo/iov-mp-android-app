package net.hwyz.iov.mp.app.module.my.page

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
import androidx.compose.foundation.text.BasicTextField
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
import net.hwyz.iov.mp.app.component.bar.TopBackTitleBar
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.component.dialog.ConfirmDialog
import net.hwyz.iov.mp.app.utils.GlobalStateManager

/**
 * 我的 - 设置页面
 */
@Composable
fun MySettingPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    isLogged: Boolean
) {
    val showDialog = remember { mutableStateOf(false) }
    val mockCount = remember { mutableStateOf(0) }
    val showMock = remember { mutableStateOf(false) }
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
                        mockCount.value = mockCount.value + 1
                        if (GlobalStateManager.isMock && mockCount.value > 10) {
                            showMock.value = true
                            GlobalStateManager.isMock = false
                        }
                    }
            ) {
                Text(
                    text = stringResource(R.string.version),
                    fontSize = H5,
                    color = AppTheme.colors.fontPrimary
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = BuildConfig.VERSION_NAME,
                    fontSize = H5,
                    color = AppTheme.colors.fontSecondary
                )
                if (GlobalStateManager.isMock) {
                    Text(
                        text = "(Mock)",
                        fontSize = H5,
                        color = AppTheme.colors.fontSecondary
                    )
                }
            }
            if (showMock.value) {
                Spacer(Modifier.height(10.dp))
                Row {
                    BasicTextField(value = GlobalStateManager.apiUrl, onValueChange = {})
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(20.dp))
            if (isLogged) {
                Spacer(modifier = Modifier.height(20.dp))
                RoundedCornerButton(
                    text = stringResource(id = R.string.logout)
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
    MySettingPage(rememberNavController(), rememberScaffoldState(), true)
}