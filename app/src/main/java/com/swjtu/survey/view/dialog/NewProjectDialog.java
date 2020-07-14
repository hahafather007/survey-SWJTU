package com.swjtu.survey.view.dialog;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.framekit.view.dialog.BaseDialog;
import com.swjtu.survey.R;
import com.swjtu.survey.utils.ToastUtils;

public class NewProjectDialog extends BaseDialog {
    private TextView tvSure,tvCancel;
    private EditText etInput;

    public void cancelTextContent(){
        etInput.setText("");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_new_project;
    }

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    protected void initView(View view) {
        tvSure = view.findViewById(R.id.tv_dialog_sure);
        tvCancel = view.findViewById(R.id.tv_dialog_cancel);
        etInput = view.findViewById(R.id.et_dialog_input);

        tvCancel.setOnClickListener(v -> NewProjectDialog.this.dismiss());

        tvSure.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etInput.getText().toString())) {
                ToastUtils.showToast("请输入工程名");
                return;
            }
            if (onCreateProjectListener != null) {
                NewProjectDialog.this.dismiss();
                onCreateProjectListener.create(etInput.getText().toString());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void config(Dialog dialog) {
        setCancelable(false);
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
        lp.width = lp.height;
        lp.height = lp.width;
        lp.dimAmount = 0.5f;
        window.setAttributes(lp);
    }

    public interface OnCreateProjectListener{
        void create(String projectName);
    }

    private OnCreateProjectListener onCreateProjectListener;

    public void setOnCreateProjectListener(OnCreateProjectListener onCreateProjectListener) {
        this.onCreateProjectListener = onCreateProjectListener;
    }
}
