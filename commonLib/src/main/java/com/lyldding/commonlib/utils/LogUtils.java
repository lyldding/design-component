package com.lyldding.commonlib.utils;

import android.util.Log;

import com.lyldding.commonlib.BuildConfig;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/16
 */
public class LogUtils {
    private static final String TAG = "test_log";

    private LogUtils() {
    }

    private static boolean isDebuggable() {
        return BuildConfig.IS_DEBUG;
    }

    private static String createLog(String log) {
        StackTraceElement element = new Throwable().getStackTrace()[2];
        StringBuffer buffer = new StringBuffer();
        buffer.append(element.getClassName());
        buffer.append(".");
        buffer.append(element.getMethodName());
        buffer.append("(").append(element.getFileName()).append(":").append(element.getLineNumber()).append(") \n");
        buffer.append(log);
        return buffer.toString();
    }

    public static void e(String message) {
        if (isDebuggable()) {
            Log.e(TAG, createLog(message));
        }
    }


    public static void i(String message) {
        if (isDebuggable()) {
            Log.i(TAG, createLog(message));
        }
    }

    public static void d(String message) {
        if (isDebuggable()) {
            Log.d(TAG, createLog(message));
        }
    }

    public static void v(String message) {
        if (isDebuggable()) {
            Log.v(TAG, createLog(message));
        }
    }

    public static void w(String message) {
        if (isDebuggable()) {
            Log.w(TAG, createLog(message));
        }
    }
}