package net.hwyz.iov.mp.app.utils

import net.hwyz.iov.mp.app.data.store.DataStoreUtils
import java.util.UUID

/**
 * 设备工具类
 */
object DeviceUtil {

    fun getDeviceId(): String {
        var deviceId = DataStoreUtils.readStringData("deviceId")
        if (deviceId.isEmpty()) {
            deviceId = UUID.randomUUID().toString()
            DataStoreUtils.putSyncData("deviceId", deviceId)
        }
        return deviceId
    }

    /**
     * 初始化推送注册ID
     * @return 推送注册ID是否发生变化
     */
    fun initPushRegId(pushRegistrationId: String): Boolean {
        getPushRegId().let { oldId ->
            if (oldId != pushRegistrationId) {
                DataStoreUtils.putSyncData("pushRegistrationId", pushRegistrationId)
                return true
            }
        }
        return false
    }

    /**
     * 获取推送注册ID
     */
    fun getPushRegId(): String {
        return DataStoreUtils.readStringData("pushRegistrationId")
    }
}