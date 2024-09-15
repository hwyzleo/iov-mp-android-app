package net.hwyz.iov.mp.app.ui.page.my

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.hwyz.iov.mp.app.utils.AppUserUtil

/**
 * 我的页面
 */
@Composable
fun MyView(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: MyViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    viewStates.isLogged = AppUserUtil.isLogged
    LaunchedEffect(Unit) {
        viewModel.intent(MyIntent.OnLaunched)
    }
    val isLogin = remember { mutableStateOf(viewStates.isLogged) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if (!isLogin.value) {
            MyViewNotLogin(
                navCtrl = navCtrl,
                intent = { intent: MyIntent -> viewModel.intent(intent) },
                viewState = viewStates
            )
        } else {
            MyViewLogin(
                navCtrl = navCtrl,
                intent = { intent: MyIntent -> viewModel.intent(intent) },
                viewState = viewStates,
                nickname = AppUserUtil.nickname,
                avatar = AppUserUtil.avatar
            )
        }
    }
}