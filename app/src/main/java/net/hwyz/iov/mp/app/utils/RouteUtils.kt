package net.hwyz.iov.mp.app.utils

import android.net.Uri
import android.os.Parcelable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import timber.log.Timber

/**
 * 路由工具类
 *
 * @author hwyz_leo
 */
object RouteUtils {

    /**
     * 导航到某个页面
     */
    fun navTo(
        navCtrl: NavHostController,
        destinationName: String,
        args: Any? = null,
        backStackRouteName: String? = null,
        isLaunchSingleTop: Boolean = true,
        needToRestoreState: Boolean = true,
    ) {

        var singleArgument = ""
        if (args != null) {
            when (args) {
                is Parcelable -> {
                    singleArgument = String.format("/%s", Uri.encode(args.toJson()))
                }

                is String -> {
                    singleArgument = String.format("/%s", args)
                }

                is Int -> {
                    singleArgument = String.format("/%s", args)
                }

                is Float -> {
                    singleArgument = String.format("/%s", args)
                }

                is Double -> {
                    singleArgument = String.format("/%s", args)
                }

                is Boolean -> {
                    singleArgument = String.format("/%s", args)
                }

                is Long -> {
                    singleArgument = String.format("/%s", args)
                }
            }
        }
        Timber.d("导航到： $destinationName")
        navCtrl.navigate("$destinationName$singleArgument") {
            if (backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true }
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }

    fun NavHostController.back() {
        navigateUp()
    }

    private fun getPopUpId(navCtrl: NavHostController, routeName: String?): Int {
        val defaultId = navCtrl.graph.findStartDestination().id
        return if (routeName == null) {
            defaultId
        } else {
            navCtrl.findDestination(routeName)?.id ?: defaultId
        }
    }

}