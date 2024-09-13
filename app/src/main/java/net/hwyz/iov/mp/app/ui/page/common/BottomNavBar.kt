package net.hwyz.iov.mp.app.ui.page.common

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
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
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        tint = AppTheme.colors.textPrimary
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
    var icon: ImageVector
) {
    object Explore : BottomNavRoute(RouteName.EXPLORE, R.string.community, Icons.Default.Lock)
    object Service : BottomNavRoute(RouteName.SERVICE, R.string.service, Icons.Default.Lock)
    object Vehicle : BottomNavRoute(RouteName.VEHICLE, R.string.vehicle, Icons.Default.Lock)
    object Mall : BottomNavRoute(RouteName.MALL, R.string.mall, Icons.Default.Lock)
    object My : BottomNavRoute(RouteName.MY, R.string.my, Icons.Default.Person)
}