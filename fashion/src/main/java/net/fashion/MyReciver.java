package net.fashion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by liuzhijun on 2014/9/21.
 */
public class MyReciver extends BroadcastReceiver {

    private static int num = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(this.getClass().getName(), "broadcast new message" + num++);
    }
}
