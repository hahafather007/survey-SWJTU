package com.swjtu.survey.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.framekit.utils.DeviceUtil;
import com.android.framekit.view.dialog.BaseDialog;
import com.swjtu.survey.R;

public class CADOpenDialog extends BaseDialog {
    private OnDialogItemClickListener listener;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_cad_open_tip;
    }

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onConfirmClick();
                }
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void config(Dialog dialog) {
        if (dialog != null) {
            dialog.setCancelable(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    @Override
    protected void initOnStart() {
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        if (window != null)
            window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DeviceUtil.INSTANCE.dip2px(getContext(),320);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 0.5f;
        window.setAttributes(lp);
    }

    public interface OnDialogItemClickListener {
        void onConfirmClick();
    }

    public OnDialogItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnDialogItemClickListener listener) {
        this.listener = listener;
    }
}
