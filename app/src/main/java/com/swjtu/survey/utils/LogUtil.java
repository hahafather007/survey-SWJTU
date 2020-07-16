package com.swjtu.survey.utils;

import android.util.Log;

import com.swjtu.survey.Constants;

public class LogUtil {
    public static void i(String TAG, String msg) {
        if (Constants.isDebug) {
            String  tag = TAG;
            if (TAG == null) {
                tag = Constants.TAG;
            } else if(TAG.isEmpty() || TAG.equals(" ")){
                tag = Constants.TAG;
            }
            Log.i(tag, msg);
        }
    }
    public static void d(String TAG, String msg) {
        if (Constants.isDebug) {
            String  tag = TAG;
            if (TAG == null) {
                tag = Constants.TAG;
            } else if(TAG.isEmpty() || TAG.equals(" ")){
                tag = Constants.TAG;
            }
            Log.d(tag, msg);
        }
    }
    public static void w(String TAG, String msg) {
        if (Constants.isDebug) {
            String  tag = TAG;
            if (TAG == null) {
                tag = Constants.TAG;
            } else if(TAG.isEmpty() || TAG.equals(" ")){
                tag = Constants.TAG;
            }
            Log.w(tag, msg);
        }
    }
    public static void e(String TAG, String msg) {
        if (Constants.isDebug) {
            String  tag = TAG;
            if (TAG == null) {
                tag = Constants.TAG;
            } else if(TAG.isEmpty() || TAG.equals(" ")){
                tag = Constants.TAG;
            }
            Log.e(tag, msg);
        }
    }
}
