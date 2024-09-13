package net.hwyz.iov.mp.app.ui.page.my

import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import net.hwyz.iov.mp.app.ui.page.RouteName
import net.hwyz.iov.mp.app.ui.widget.list.TitleList
import net.hwyz.iov.mp.app.utils.AppUserUtil
import net.hwyz.iov.mp.app.utils.RouteUtils

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
    MyScreen(
        navCtrl = navCtrl,
        intent = { intent: MyIntent -> viewModel.intent(intent) },
        viewState = viewStates
    )
}

@Composable
fun MyScreen(
    navCtrl: NavHostController,
    intent: (MyIntent) -> Unit,
    viewState: MyState
) {
    var isLogin = remember { mutableStateOf(viewState.isLogged) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if (!isLogin.value) {
            MyAvatar(
                nickname = "注册 / 登录",
                avatar = null
            ) {
                RouteUtils.navTo(
                    navCtrl = navCtrl,
                    destinationName = RouteName.LOGIN
                )
            }
        } else {
            MyAvatar(
                nickname = AppUserUtil.nickname,
                avatar = AppUserUtil.avatar
            ) {
                RouteUtils.navTo(
                    navCtrl = navCtrl,
                    destinationName = RouteName.PROFILE
                )
            }
        }
        Column {
            TitleList(iconRes = Icons.Default.List, title = "我的积分") {}
            TitleList(iconRes = Icons.Default.ShoppingCart, title = "我的订单") {}
        }
        Spacer(modifier = Modifier.padding(bottom = 20.dp))
        Column {
            TitleList(iconRes = Icons.Default.Settings, title = "设置") {}
            if (isLogin.value) {
                TitleList(iconRes = Icons.Default.Settings, title = "退出") {
                    AppUserUtil.onLogOut()
                    isLogin.value = false
                }
            }
        }
    }
}

@Composable
fun MyAvatar(
    nickname: String,
    avatar: String?,
    onClick: PressGestureScope.(Offset) -> Unit
) {
    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = onClick
                )
            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (avatar == null) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 80.dp)
                        .size(100.dp)
                )
            } else {
                AsyncImage(
                    model = avatar,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 80.dp)
                        .size(100.dp)
                        .clip(CircleShape)
                        .drawBehind {
                            drawCircle(
                                color = Color.Gray,
                                center = center,
                                radius = size.minDimension / 2
                            )
                        }
                )
            }
            Text(text = nickname, fontSize = 18.sp, modifier = Modifier.padding(5.dp))
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}

@Preview
@Composable
fun MyPagePreview() {
    val navCtrl = rememberNavController()
    var viewState = MyState()
    MyScreen(navCtrl, {}, viewState)
}