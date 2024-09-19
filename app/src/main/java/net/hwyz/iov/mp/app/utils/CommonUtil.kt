package net.hwyz.iov.mp.app.utils

object CommonUtil {

    fun genderStr(gender: String): String {
        when (gender) {
            "MALE" -> return "男"
            "FEMALE" -> return "女"
        }
        return "未知"
    }

    /**
     * 转换错误消息
     * @param error 原始错误信息
     */
    fun convertErrorMsg(error: String?): String {
        if (error == null) return "未知错误"
        return when (error.uppercase()) {
            "CONNECTION RESET" -> "网络异常"
            else -> error
        }
    }

}