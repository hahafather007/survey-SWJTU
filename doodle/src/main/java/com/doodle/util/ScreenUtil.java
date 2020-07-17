package com.doodle.util;

import android.content.Context;

public class ScreenUtil {
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2px(Context context, float dp) {
        return (int)(context.getResources().getDisplayMetrics().density * dp + 0.5F);
    }
}
