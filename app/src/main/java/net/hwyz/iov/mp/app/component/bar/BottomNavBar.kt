package net.hwyz.iov.mp.app.component.bar

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
import net.hwyz.iov.mp.app.RouteName
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.utils.VehicleManager

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
                    if (currentDestination?.hierarchy?.any { it.route == screen.routeName } == true) {
                        Image(
                            painter = painterResource(screen.id2),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(screen.id),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                label = {
                    if (screen == BottomNavRoute.Vehicle) {
                        if (VehicleManager.hasVehicle()) {
                            Text(
                                text = stringResource(R.string.vehicle),
                                color = AppTheme.colors.fontPrimary
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.buy_vehicle),
                                color = AppTheme.colors.fontPrimary
                            )
                        }
                    } else {
                        Text(
                            text = stringResource(screen.stringId),
                            color = AppTheme.colors.fontPrimary
                        )
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                onClick = {
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
    var id: Int,
    var id2: Int
) {
    object Explore : BottomNavRoute(
        RouteName.EXPLORE,
        R.string.community,
        R.drawable.icon_explore,
        R.drawable.icon_explore_fill
    )

    object Service : BottomNavRoute(
        RouteName.SERVICE,
        R.string.service,
        R.drawable.icon_service,
        R.drawable.icon_service_fill
    )

    object Vehicle : BottomNavRoute(
        RouteName.VEHICLE,
        R.string.vehicle,
        R.drawable.icon_vehicle,
        R.drawable.icon_vehicle_fill
    )

    object Mall : BottomNavRoute(
        RouteName.MALL,
        R.string.mall,
        R.drawable.icon_mall,
        R.drawable.icon_mall_fill
    )

    object My : BottomNavRoute(
        RouteName.MY,
        R.string.my,
        R.drawable.icon_person,
        R.drawable.icon_person_fill
    )
}