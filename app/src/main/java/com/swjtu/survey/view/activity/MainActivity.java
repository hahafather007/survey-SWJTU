package com.swjtu.survey.view.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.framekit.view.activity.BaseActivity;
import com.swjtu.survey.R;
import com.swjtu.survey.contract.MainContract;
import com.swjtu.survey.presenter.MainPresenter;
import com.swjtu.survey.utils.ClickAction;
import com.swjtu.survey.utils.ToastUtils;
import com.swjtu.survey.view.dialog.DefaultDialog;
import com.swjtu.survey.view.dialog.NewProjectDialog;

public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View, ClickAction {
    private DrawerLayout drawerLayout;
    private NewProjectDialog newProjectDialog = null;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void configView() {
        setOnClickListener(R.id.iv_main_menu,R.id.tv_main_new_project,R.id.tv_main_input_project,R.id.tv_main_history_project
                ,R.id.tv_main_output_project,R.id.tv_main_login_out,R.id.fl_main_clear,R.id.fl_main_manual,R.id.fl_main_about);

        drawerLayout = findViewById(R.id.dl_main);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_main_menu:
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.tv_main_new_project:
                if (newProjectDialog == null) {
                    newProjectDialog = new NewProjectDialog();
                    newProjectDialog.setOnCreateProjectListener(new NewProjectDialog.OnCreateProjectListener() {
                        @Override
                        public void create(String projectName) {
                            newProjectDialog.cancelTextContent();
                            Intent intent = new Intent();
                            intent.putExtra("name",projectName);
                            intent.setClass(MainActivity.this,ProjectActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                newProjectDialog.show(getSupportFragmentManager(),"");
                break;
            case R.id.tv_main_input_project:
                break;
            case R.id.tv_main_history_project:
                break;
            case R.id.tv_main_output_project:
                break;
            case R.id.tv_main_login_out:
                new DefaultDialog.Builder().tipsContent("确认退出？").onSureClickListener(new DefaultDialog.OnSureClickListener() {
                    @Override
                    public void sure() {
                        MainActivity.this.finish();
                        System.exit(0);
                    }

                    @Override
                    public void cancel() {

                    }
                }).build().show(getSupportFragmentManager(),"");
                break;
            case R.id.fl_main_clear:
                break;
            case R.id.fl_main_about:
                break;
            case R.id.fl_main_manual:
                break;
        }
    }

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showToast("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();//避免闪屏
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}