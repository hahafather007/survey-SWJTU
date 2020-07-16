package com.swjtu.survey.view.activity;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.framekit.view.activity.BaseActivity;
import com.swjtu.survey.R;
import com.swjtu.survey.adapter.HistoryProjectAdapter;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.contract.HistoryProjectContract;
import com.swjtu.survey.presenter.HistoryProjectPresenter;

import java.util.ArrayList;
import java.util.List;

public class HistoryProjectActivity extends BaseActivity<HistoryProjectContract.View,HistoryProjectContract.Presenter> implements HistoryProjectContract.View {
    private HistoryProjectAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_project;
    }

    @Override
    protected void configView() {
        RecyclerView rvHistoryProject = findViewById(R.id.rv_history_project);
        rvHistoryProject.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new HistoryProjectAdapter(this);
        adapter.setListener(new HistoryProjectAdapter.OnProjectItemClickListener() {
            @Override
            public void onItemClick(SurveyProjectBean surveyProjectBean) {
                Intent intent = new Intent();
                intent.putExtra("survey_project", surveyProjectBean);
                intent.setClass(HistoryProjectActivity.this, ProjectActivity.class);
                startActivity(intent);
            }
        });
        rvHistoryProject.setAdapter(adapter);

        if (getMPresenter()!=null) {
            getMPresenter().getHistoryProjects();
        }

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
    public void getHistoryProjectsSus(List<SurveyProjectBean> surveys) {
        adapter.updateData((ArrayList<SurveyProjectBean>) surveys);
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void complete() {

    }

    @Override
    protected HistoryProjectContract.Presenter initPresenter() {
        return new HistoryProjectPresenter();
    }
}
