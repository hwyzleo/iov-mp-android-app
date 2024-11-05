package net.hwyz.iov.mp.app.utils

import net.hwyz.iov.mp.app.data.bean.VehicleSaleOrder

/**
 * 车辆管理类
 *
 * @author hwyz_leo
 */
object VehicleManager {
    // 车辆列表
    private var vehicles: MutableMap<String, VehiclePo> = mutableMapOf()

    // 当前车辆id
    private var currentVehicleId: String? = null

    /**
     * 是否拥有车辆
     */
    fun hasVehicle(): Boolean {
        for (vehiclePo in vehicles.values) {
            if (vehiclePo.type == VehicleType.ACTIVATED) {
                return true
            }
        }
        return false
    }

    /**
     * 是否拥有订单
     */
    fun hasOrder(): Boolean {
        return vehicles.isNotEmpty()
    }

    /**
     * 设置当前车辆
     */
    fun setCurrentVehicleId(id: String) {
        currentVehicleId = id
    }

    /**
     * 获取当前车辆ID
     */
    fun getCurrentVehicleId(): String? {
        if (currentVehicleId != null && vehicles.keys.contains(currentVehicleId)) {
            return currentVehicleId;
        }
        if (hasOrder()) {
            setCurrentVehicleId(vehicles.keys.first())
            return vehicles.keys.first()
        }
        return null
    }

    /**
     * 获取当前车辆
     */
    fun getCurrentVehicle(): VehiclePo? {
        if (currentVehicleId == null) {
            return null
        }
        if (vehicles[currentVehicleId] == null) {
            return null
        }
        return vehicles[currentVehicleId]
    }

    /**
     * 更新车辆信息
     */
    fun update(vehicleSaleOrderList: List<VehicleSaleOrder>) {
        clear()
        for (vehicleSaleOrder in vehicleSaleOrderList) {
            when (vehicleSaleOrder.orderState) {
                100 -> {
                    add(
                        vehicleSaleOrder.orderNum,
                        VehicleType.WISHLIST,
                        vehicleSaleOrder.displayName
                    )
                }

                700 -> {
                    add(
                        vehicleSaleOrder.orderNum,
                        VehicleType.ACTIVATED,
                        vehicleSaleOrder.displayName
                    )
                }

                else -> {
                    add(vehicleSaleOrder.orderNum, VehicleType.ORDER, vehicleSaleOrder.displayName)
                }
            }
        }
        if (vehicles.isNotEmpty() && (currentVehicleId == null || !vehicles.keys.contains(
                currentVehicleId
            ))
        ) {
            setCurrentVehicleId(vehicles.keys.first())
        }
    }

    /**
     * 添加车辆信息
     */
    fun add(orderNum: String, type: VehicleType, displayName: String) {
        vehicles[orderNum] = VehiclePo().apply {
            this.id = orderNum
            this.type = type
            this.displayName = displayName
        }
    }

    /**
     * 清除车辆
     */
    private fun clear() {
        vehicles.clear()
    }
}

/**
 * 车辆类型
 */
enum class VehicleType {
    ACTIVATED, ORDER, WISHLIST
}

/**
 * 车辆持久化对象
 */
class VehiclePo {
    var id: String = ""
    var type: VehicleType = VehicleType.ACTIVATED
    var vin: String = ""
    var displayName: String = ""
}