package com.swjtu.survey.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.framekit.view.dialog.BaseDialog;
import com.swjtu.survey.R;

public class MenuDialog extends BaseDialog {
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_menu;
    }

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.tv_dialog_menu_center).setOnClickListener(v -> onItemClickListener.center());
        view.findViewById(R.id.tv_dialog_menu_control).setOnClickListener(v -> onItemClickListener.control());
        view.findViewById(R.id.tv_dialog_menu_section).setOnClickListener(v -> onItemClickListener.section());
        view.findViewById(R.id.tv_dialog_menu_terrain).setOnClickListener(v -> onItemClickListener.terrain());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void config(Dialog dialog) {

    }

    private int dip2px(float dpValue) {
        final float scale = getActivity().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void initOnStart() {
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        if (window != null)
            window.setGravity(Gravity.RIGHT|Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = lp.height;
        lp.height = lp.width;
        lp.x = dip2px(16);
        lp.y = dip2px(44);
        lp.dimAmount = 0.5f;
        window.setAttributes(lp);
    }

    public interface OnItemClickListener{
        void center();
        void section();
        void terrain();
        void control();
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
