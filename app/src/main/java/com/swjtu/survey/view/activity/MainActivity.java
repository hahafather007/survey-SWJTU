package com.swjtu.survey.view.activity;

import com.android.framekit.view.activity.BaseActivity;
import com.swjtu.survey.R;
import com.swjtu.survey.contract.MainContract;
import com.swjtu.survey.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void configView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void complete() {

    }
}