package net.hwyz.iov.mp.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.component.bar.AppSnackBar
import net.hwyz.iov.mp.app.component.bar.BottomNavBar
import net.hwyz.iov.mp.app.component.view.EmptyView
import net.hwyz.iov.mp.app.module.login.LoginPage
import net.hwyz.iov.mp.app.module.marketing.page.MarketingIndexPage
import net.hwyz.iov.mp.app.module.marketing.page.VehicleModelConfigPage
import net.hwyz.iov.mp.app.module.marketing.page.VehicleOrderDetailPage
import net.hwyz.iov.mp.app.module.my.page.MyPage
import net.hwyz.iov.mp.app.module.my.page.MySettingPage
import net.hwyz.iov.mp.app.module.my.profile.ProfileView
import net.hwyz.iov.mp.app.module.vehicle.VehiclePage
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.utils.UserManager
import net.hwyz.iov.mp.app.utils.VehicleManager

/**
 * 主页
 *
 * @author hwyz_leo
 */
@Composable
@ExperimentalComposeUiApi
fun HomeEntry() {
    var isSplash by remember { mutableStateOf(true) }
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()

    AppTheme {
        if (isSplash) {
            SplashView { isSplash = false }
        } else {
            Scaffold(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                bottomBar = {
                    when (currentDestination?.route) {
                        RouteName.EXPLORE -> BottomNavBar(navCtrl = navCtrl)
                        RouteName.SERVICE -> BottomNavBar(navCtrl = navCtrl)
                        RouteName.VEHICLE -> BottomNavBar(navCtrl = navCtrl)
                        RouteName.MALL -> BottomNavBar(navCtrl = navCtrl)
                        RouteName.MY -> BottomNavBar(navCtrl = navCtrl)
                    }
                },
                content = { padding ->
                    var homeIndex = remember { 0 }
                    var categoryIndex = remember { 0 }

                    NavHost(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .padding(padding),
                        navController = navCtrl,
                        startDestination = RouteName.EXPLORE
                    ) {
                        // 探索
                        composable(route = RouteName.EXPLORE) {
                            EmptyView(
                                tips = "待解锁",
                                imageVector = Icons.Default.Lock
                            ) {
                            }
                        }
                        // 服务
                        composable(route = RouteName.SERVICE) {
                            EmptyView(
                                tips = "待解锁",
                                imageVector = Icons.Default.Lock
                            ) {
                            }
                        }
                        // 爱车
                        composable(route = RouteName.VEHICLE) {
                            if(VehicleManager.hasVehicle()) {
                                VehiclePage(navCtrl, scaffoldState)
                            } else {
                                MarketingIndexPage(navCtrl, scaffoldState)
                            }
                        }
                        // 商城
                        composable(route = RouteName.MALL) {
                            EmptyView(
                                tips = "待解锁",
                                imageVector = Icons.Default.Lock
                            ) {
                            }
                        }
                        // 我的
                        composable(route = RouteName.MY) {
                            MyPage(navCtrl, scaffoldState)
                        }
                        // 我的 - 设置
                        composable(route = RouteName.MY_SETTING) {
                            MySettingPage(navCtrl, scaffoldState, UserManager.isLogged)
                        }
                        // 用户资料
                        composable(route = RouteName.PROFILE) {
                            ProfileView(navCtrl, scaffoldState)
                        }
                        // 登录
                        composable(route = RouteName.LOGIN) {
                            LoginPage(navCtrl, scaffoldState)
                        }
                        // 车型配置
                        composable(route = RouteName.MODEL_CONFIG) {
                            VehicleModelConfigPage(navCtrl, scaffoldState)
                        }
                        // 订单详情
                        composable(route = RouteName.ORDER_DETAIL) {
                            VehicleOrderDetailPage(navCtrl, scaffoldState)
                        }
                    }
                },
                snackbarHost = {
                    SnackbarHost(
                        hostState = scaffoldState.snackbarHostState
                    ) { data ->
                        println("actionLabel = ${data.actionLabel}")
                        AppSnackBar(data = data)
                    }
                }
            )
        }
    }

}

object RouteName {
    const val EXPLORE = "explore"
    const val SERVICE = "service"
    const val VEHICLE = "vehicle"
    const val MALL = "mall"
    const val MY = "my"
    const val MY_SETTING = "my_setting"
    const val PROFILE = "profile"
    const val LOGIN = "login"
    const val MODEL_CONFIG = "model_config"
    const val ORDER_DETAIL = "order_detail"
}
