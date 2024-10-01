package net.hwyz.iov.mp.app

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.lifecycleScope
import cn.jiguang.api.utils.JCollectionAuth
import cn.jpush.android.api.JPushInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import net.hwyz.iov.mp.app.data.bean.UpdateClientConfigRequest
import net.hwyz.iov.mp.app.data.http.TspApi
import net.hwyz.iov.mp.app.utils.DeviceUtil
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @Inject
    lateinit var tspApi: TspApi

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContext(this)
        setContent { HomeEntry() }
        requestPermissions()

    }

    /**
     * 请求权限
     */
    private fun requestPermissions() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }
        permissionLauncher.launch(permissions.toTypedArray())
    }

    private fun initContext(context: Context) {
        JPushInterface.setDebugMode(true);
        var isPrivacyReady: Boolean = true // app根据是否已弹窗获取隐私授权来赋值
        if (!isPrivacyReady) {
            JCollectionAuth.setAuth(this, false); // 后续的初始化与启用推送服务过程将被拦截，即不会开启推送业务
        }
        JPushInterface.init(this)
        JCollectionAuth.setAuth(this, true); //如初始化被拦截过，将重试初始化过程
        lifecycleScope.launch {
            try {
                if (!DeviceUtil.initPushRegId(JPushInterface.getRegistrationID(context))) {
                    val response =
                        tspApi.updateClientConfig(UpdateClientConfigRequest(DeviceUtil.getPushRegId()))
                    if (response.code != 0) {
                        throw Exception(response.message)
                    }
                }
            } catch (e: Exception) {
                Log.w("init", e.message ?: "推送注册ID更新失败")
            }
        }

    }

}