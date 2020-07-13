package com.swjtu.survey.utils;

import android.util.Log;

import com.swjtu.survey.Constants;

public class LogUtil {
    public static void i(String TAG, String msg) {
        if (Constants.isDebug) {
            Log.i(Constants.TAG, msg);
        }
    }
    public static void d(String TAG, String msg) {
        if (Constants.isDebug) {
            Log.d(Constants.TAG, msg);
        }
    }
    public static void w(String TAG, String msg) {
        if (Constants.isDebug) {
            Log.w(Constants.TAG, msg);
        }
    }
    public static void e(String TAG, String msg) {
        if (Constants.isDebug) {
            Log.e(Constants.TAG, msg);
        }
    }
}
