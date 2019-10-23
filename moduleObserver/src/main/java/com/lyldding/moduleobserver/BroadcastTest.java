package com.lyldding.moduleobserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author https://github.com/lyldding
 */
public class BroadcastTest extends BroadcastReceiver {
    public static final String ACTION = "BroadcastTest";
    public static final String TYPE = "TYPE";
    private static final String TAG = "BroadcastTest";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "执行了广播 = " + intent.getIntExtra(TYPE, -1));
        Toast.makeText(context, "执行了广播 = " + intent.getIntExtra(TYPE, -1), Toast.LENGTH_SHORT).show();
    }
}
