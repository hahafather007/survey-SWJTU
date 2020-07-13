package com.android.framekit.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager

/**
 * Be sure DeviceUtil is a singleton. It not needs thread safe and just
 * to get info of device. This singleton's memory loaded when fist use it.
 * so object declare is well done.
 */
object DeviceUtil {
    /**
     * Get the device real height, include NavigationBar height.
     * <p>
     *     Note: api getRealMetrics needs android level 17
     * </p>
     */
    @SuppressLint("NewApi")
    fun getScreenRealHeight(context: Context): Int {
        val windowManager: WindowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    fun getScreenHeight(context: Context): Int {
        val windowManager: WindowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    /**
     * Get device width,in pixels.
     */
    fun getScreenWidth(context: Context): Int {
        val windowManager: WindowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun dip2px(context: Context, dps: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dps * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxs: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxs / scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    fun getLocationStartY(view: View, context: Context): Int {
        val location: IntArray = IntArray(2)
        view.getLocationOnScreen(location)
        return location[1] - getStatusBarHeight(context)
    }

    fun getLocationEndY(view: View, context: Context): Int {
        val location: IntArray = IntArray(2)
        view.getLocationOnScreen(location)
        return location[1] - getStatusBarHeight(context) + view.height
    }

    fun getLocationStartX(view: View, context: Context): Int {
        val location: IntArray = IntArray(2)
        view.getLocationOnScreen(location)
        return location[0]
    }

    fun getLocationEndX(view: View, context: Context): Int {
        val location: IntArray = IntArray(2)
        view.getLocationOnScreen(location)
        return location[0] + view.width
    }
}
