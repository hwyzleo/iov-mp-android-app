package net.hwyz.iov.mp.app.push;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class PushMessageReceiver extends JPushMessageReceiver {

    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        Log.i("onNotifyMessageArrived", "onNotifyMessageArrived");
        super.onNotifyMessageArrived(context, notificationMessage);
        EventBus.getDefault().post(new PushEvent.NotificationArrived(notificationMessage));
    }

}
