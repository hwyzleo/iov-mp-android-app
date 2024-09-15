package net.hwyz.iov.mp.app.ui.page.common

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.ui.page.RouteName
import net.hwyz.iov.mp.app.ui.theme.AppTheme

/**
 * 底部导航栏
 */
@Composable
fun BottomNavBar(navCtrl: NavHostController) {
    val bottomNavList = listOf(
        BottomNavRoute.Explore,
        BottomNavRoute.Service,
        BottomNavRoute.Vehicle,
        BottomNavRoute.Mall,
        BottomNavRoute.My
    )
    BottomNavigation {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavList.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.background(AppTheme.colors.themeUi),
                icon = {
                    Image(
                        painter = painterResource(screen.id),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.stringId),
                        color = AppTheme.colors.textPrimary
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                onClick = {
                    Log.d(
                        "test",
                        "BottomNavView当前路由 ===> ${currentDestination?.hierarchy?.toList()}"
                    )
                    Log.d("test", "当前路由栈 ===> ${navCtrl.graph.nodes}")
                    if (currentDestination?.route != screen.routeName) {
                        navCtrl.navigate(screen.routeName) {
                            popUpTo(navCtrl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }
}

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var id: Int
) {
    object Explore : BottomNavRoute(RouteName.EXPLORE, R.string.community, R.drawable.icon_lock)
    object Service : BottomNavRoute(RouteName.SERVICE, R.string.service, R.drawable.icon_lock)
    object Vehicle : BottomNavRoute(RouteName.VEHICLE, R.string.vehicle, R.drawable.icon_vehicle)
    object Mall : BottomNavRoute(RouteName.MALL, R.string.mall, R.drawable.icon_lock)
    object My : BottomNavRoute(RouteName.MY, R.string.my, R.drawable.icon_person)
}