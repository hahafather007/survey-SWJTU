package com.swjtu.survey.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.framekit.view.activity.BaseActivity;
import com.swjtu.survey.R;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.contract.ProjectContract;
import com.swjtu.survey.presenter.ProjectPresenter;
import com.swjtu.survey.utils.ClickAction;
import com.swjtu.survey.view.dialog.MenuDialog;
import com.swjtu.survey.view.dialog.ProjectInitTipDialog;

public class ProjectActivity extends BaseActivity<ProjectContract.View, ProjectContract.Presenter> implements ProjectContract.View, ClickAction {
    private MenuDialog menuDialog;
    private DrawerLayout drawerLayout;
    private long mExitTime;
    private SurveyProjectBean surveyProjectBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project;
    }

    @Override
    protected void configView() {
        drawerLayout = findViewById(R.id.dl_project);
        setOnClickListener(R.id.iv_project_return,R.id.iv_project_menu);
        TextView tv = findViewById(R.id.tv_project_name);
        surveyProjectBean = (SurveyProjectBean) getIntent().getSerializableExtra("survey_project");
        tv.setText(surveyProjectBean.getProjectName());

        menuDialog = new MenuDialog();
        menuDialog.setOnItemClickListener(new MenuDialog.OnItemClickListener() {
            @Override
            public void center() {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }

            @Override
            public void section() {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }

            @Override
            public void terrain() {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }

            @Override
            public void control() {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        findViewById(R.id.rv_center_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.rv_prick_point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ProjectActivity.this,TiffGIsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIsInit();
    }

    /**
     * 检查是否初始化工程。未初始化则进行初始化
     */
    private void checkIsInit() {
        if (surveyProjectBean == null) {
            return;
        }
        if (!surveyProjectBean.isProjectInit()) {
            ProjectInitTipDialog initTipDialog = new ProjectInitTipDialog();
            initTipDialog.setListener(new ProjectInitTipDialog.OnDialogItemClickListener() {
                @Override
                public void onConfirmClick() {
                    //打开初始化界面
                    initTipDialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(ProjectActivity.this,ProjectInitActivity.class);
                    startActivity(intent);
                }
            });
            initTipDialog.show(getSupportFragmentManager(),"InitTipDialog");
        }
    }

    @Override
    protected void initData() {
    }

    @Override
    protected ProjectContract.Presenter initPresenter() {
        return new ProjectPresenter();
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
