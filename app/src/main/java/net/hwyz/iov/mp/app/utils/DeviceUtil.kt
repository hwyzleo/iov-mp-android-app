package net.hwyz.iov.mp.app.utils

import net.hwyz.iov.mp.app.data.store.DataStoreUtils
import java.util.UUID

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