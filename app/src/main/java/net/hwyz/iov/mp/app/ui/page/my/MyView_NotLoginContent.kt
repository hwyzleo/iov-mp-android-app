package net.hwyz.iov.mp.app.ui.page.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.ui.page.RouteName
import net.hwyz.iov.mp.app.ui.widget.button.RoundedCornerButton
import net.hwyz.iov.mp.app.ui.widget.list.TitleList
import net.hwyz.iov.mp.app.utils.RouteUtils

@Composable
fun MyViewNotLogin(
    navCtrl: NavHostController,
    intent: (MyIntent) -> Unit,
    viewState: MyState
) {
    Box(modifier = Modifier.background(Color.White)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_message),
                    contentDescription = "消息",
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_setting),
                    contentDescription = "设置",
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.padding(end = 20.dp))
            }
            Row {
                Text(
                    text = "Hi，\n欢迎您的到来",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(20.dp, 40.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.my_place_holder),
                    contentDescription = "默认头像",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(0.dp, 30.dp, 20.dp, 0.dp)
                )
            }
            RoundedCornerButton(
                text = "登录 / 注册",
                modifier = Modifier.padding(20.dp, 40.dp, 20.dp, 20.dp)
            ) {
                RouteUtils.navTo(
                    navCtrl = navCtrl,
                    destinationName = RouteName.LOGIN
                )
            }
            Column {
                TitleList(iconRes = Icons.Default.List, title = "我的积分") {}
                TitleList(iconRes = Icons.Default.ShoppingCart, title = "我的订单") {}
            }
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            Column {
                TitleList(iconRes = Icons.Default.Settings, title = "设置") {}
            }
        }
    }
}

@Preview
@Composable
fun MyPageNotLoginPreview() {
    val navCtrl = rememberNavController()
    var viewState = MyState()
    MyViewNotLogin(navCtrl, {}, viewState)
}