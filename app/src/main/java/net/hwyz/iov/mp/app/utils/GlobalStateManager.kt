package net.hwyz.iov.mp.app.utils

/**
 * 全局状态管理
 *
 * @author hwyz_leo
 */
object GlobalStateManager {
    // 是否Mock数据
    var isMock: Boolean = true
    // Mock订单状态
    var mockOrderState: OrderState = OrderState.WISHLIST
    // API地址
    var apiUrl: String = "http://192.168.2.223:8081"
    // 参数传递
    var parameters: Map<String, Any> = mutableMapOf()
}