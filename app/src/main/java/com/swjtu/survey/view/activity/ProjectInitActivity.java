package com.swjtu.survey.view.activity;

import android.view.View;

import com.android.framekit.view.activity.BaseActivity;
import com.swjtu.survey.R;
import com.swjtu.survey.contract.ProjectInitContract;
import com.swjtu.survey.presenter.ProjectInitPresenter;

public class ProjectInitActivity extends BaseActivity<ProjectInitContract.View,ProjectInitContract.Presenter> implements ProjectInitContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_init;
    }

    @Override
    protected void configView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    

    @Override
    protected void initData() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void complete() {

    }

    @Override
    protected ProjectInitContract.Presenter initPresenter() {
        return new ProjectInitPresenter();
    }
}
