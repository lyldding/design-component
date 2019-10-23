package com.lyldding.commonlib.utils;

import android.os.Handler;
import android.os.Looper;

public class MainLooper extends Handler {

    private MainLooper(Looper looper) {
        super(looper);
    }

    public static MainLooper getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static MainLooper INSTANCE = new MainLooper(Looper.getMainLooper());
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            runnable.run();
        } else {
            Holder.INSTANCE.post(runnable);
        }

    }
}
