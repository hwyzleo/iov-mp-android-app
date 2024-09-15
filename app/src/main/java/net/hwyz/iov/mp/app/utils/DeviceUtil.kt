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
}