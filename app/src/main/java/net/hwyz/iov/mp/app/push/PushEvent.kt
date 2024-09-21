package net.hwyz.iov.mp.app.push

import cn.jpush.android.api.NotificationMessage
import net.hwyz.iov.mp.app.base.MviIntent

sealed class PushEvent : MviIntent {
    data class NotificationArrived(val message: NotificationMessage) : PushEvent()
}