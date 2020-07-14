package com.swjtu.survey.view.activity;

import android.view.View;
import android.widget.TextView;

import com.android.framekit.view.activity.BaseActivity;
import com.swjtu.survey.R;
import com.swjtu.survey.contract.MainContract;
import com.swjtu.survey.presenter.MainPresenter;
import com.swjtu.survey.utils.ClickAction;
import com.swjtu.survey.utils.ToastUtils;
import com.swjtu.survey.view.dialog.MenuDialog;

public class ProjectActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View, ClickAction {
    private MenuDialog menuDialog;
    private long mExitTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project;
    }

    @Override
    protected void configView() {
        setOnClickListener(R.id.iv_project_return,R.id.iv_project_menu);
        TextView tv = findViewById(R.id.tv_project_name);
        tv.setText(getIntent().getStringExtra("name"));

        menuDialog = new MenuDialog();
        menuDialog.setOnItemClickListener(new MenuDialog.OnItemClickListener() {
            @Override
            public void center() {

            }

            @Override
            public void section() {

            }

            @Override
            public void terrain() {

            }

            @Override
            public void control() {

            }
        });

    }

    @Override
    protected void initData() {
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_project_return:
                finish();
                break;
            case R.id.iv_project_menu:
                if ((System.currentTimeMillis() - mExitTime) > 500) {
                    menuDialog.show(getSupportFragmentManager(),"");
                    mExitTime = System.currentTimeMillis();
                }

                break;
        }
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void complete() {

    }
}
