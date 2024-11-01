package net.hwyz.iov.mp.app.module.my.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.RouteName
import net.hwyz.iov.mp.app.component.button.ImageButton
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.module.my.intent.MyIntent
import net.hwyz.iov.mp.app.module.my.state.MyState
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.utils.RouteUtils

/**
 * 我的页面 - 未登录
 *
 * @author hwyz_leo
 */
@Composable
fun MyPageNotLogin(
    navCtrl: NavHostController,
    intent: (MyIntent) -> Unit,
    viewState: MyState
) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.icon_bell),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(end = 20.dp))
                ImageButton(
                    painter = painterResource(id = R.drawable.icon_setting),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                ) {
                    RouteUtils.navTo(
                        navCtrl = navCtrl,
                        destinationName = RouteName.MY_SETTING
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.not_login_welcome),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.weight(1f))
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.my_place_holder),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            RoundedCornerButton(
                text = stringResource(id = R.string.login_register)
            ) {
                RouteUtils.navTo(
                    navCtrl = navCtrl,
                    destinationName = RouteName.LOGIN
                )
            }
        }
    }
}

@Preview
@Composable
fun MyPageNotLoginPreview() {
    val navCtrl = rememberNavController()
    var viewState = MyState()
    MyPageNotLogin(navCtrl, {}, viewState)
}