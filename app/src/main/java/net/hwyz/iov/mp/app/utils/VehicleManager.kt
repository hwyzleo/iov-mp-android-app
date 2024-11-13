package net.hwyz.iov.mp.app.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.hwyz.iov.mp.app.data.bean.VehicleSaleOrder
import net.hwyz.iov.mp.app.data.store.DataStoreUtils

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
     * 初始化
     */
    fun initialize() {
        val savedVehicles = DataStoreUtils.readStringData("vehicles", "[]")
        val gson = Gson()
        vehicles = gson.fromJson(
            savedVehicles,
            object : TypeToken<MutableMap<String, VehiclePo>>() {}.type
        )
        currentVehicleId = DataStoreUtils.readStringData("currentVehicleId", "")
        if (currentVehicleId == "" && vehicles.isNotEmpty()) {
            setCurrentVehicleId(vehicles.keys.first())
        }
    }

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
        DataStoreUtils.saveSyncStringData("currentVehicleId", id)
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
        val gson = Gson()
        DataStoreUtils.saveSyncStringData("vehicles", gson.toJson(vehicles))
    }

    /**
     * 清除车辆
     */
    private fun clear() {
        vehicles.clear()
        DataStoreUtils.saveSyncStringData("vehicles", "[]")
    }
}

/**
 * 订单状态
 */
enum class OrderState(val code: Int) {
    // 心愿单
    WISHLIST(100),

    // 意向金待支付
    EARNEST_MONEY_UNPAID(200),

    // 意向金已支付
    EARNEST_MONEY_PAID(210),

    // 定金待支付
    DOWN_PAYMENT_UNPAID(300),

    // 定金已支付
    DOWN_PAYMENT_PAID(310),

    // 安排生产
    ARRANGE_PRODUCTION(400),

    // 待运输
    PREPARE_TRANSPORT(500),

    // 待交付
    PREPARE_DELIVER(600),

    // 已交付
    DELIVERED(650),

    // 已激活
    ACTIVATED(700)
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