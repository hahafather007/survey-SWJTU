package com.android.framekit.view.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import com.android.framekit.R
import com.android.framekit.utils.DeviceUtil

class LoadingDialog : BaseDialog() {
    override fun getLayoutId(): Int = R.layout.dialog_loading

    override fun setStyle(): Int = R.style.DialogFragment

    override fun initView(view: View) {

    }

    override fun initData() {

    }

    override fun config(dialog: Dialog) {
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action == KeyEvent.ACTION_DOWN) {
                callBack?.disMiss()
                return@OnKeyListener false
            }
            false
        })
    }

    override fun initOnStart() {
        dialog?.window?.setGravity(
                Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL)
        val lp = dialog?.window?.attributes
        lp?.width = (DeviceUtil.getScreenWidth(context!!) * 0.28f).toInt()
        lp?.height = lp?.width
        lp?.dimAmount = 0f
        dialog?.window?.attributes = lp
    }

    interface OnDisMissCallBack {
        fun disMiss()
    }

    private var callBack: OnDisMissCallBack? = null

    fun setCallBack(callBack: OnDisMissCallBack) {
        this.callBack = callBack
    }
}