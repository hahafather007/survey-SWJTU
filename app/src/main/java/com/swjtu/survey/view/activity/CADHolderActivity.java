package com.swjtu.survey.view.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.MxDraw.MxDrawActivity;
import com.MxDraw.MxFunction;
import com.android.framekit.view.dialog.LoadingDialog;
import com.swjtu.survey.R;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.bean.sheet.MeasureCLBean;
import com.swjtu.survey.contract.ICadView;
import com.swjtu.survey.presenter.CadPresenter;
import com.swjtu.survey.view.dialog.CLMeasureDialog;
import com.swjtu.survey.view.dialog.CLMeasureInputDialog;
import com.swjtu.survey.view.dialog.CLMileageInputDialog;
import com.swjtu.survey.view.dialog.DefaultDialog;
import com.swjtu.survey.view.dialog.ProjectInitTipDialog;
import com.swjtu.survey.widget.CocosResizeLayout;
import com.swjtu.survey.widget.CocosSurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供cad浏览的界面，中线测量、断面测量、地形测量都在该界面打开
 */
public class CADHolderActivity extends MxDrawActivity implements View.OnClickListener, ICadView {
    private boolean isCadInit = false;
    private DrawerLayout drawer_holder;
    private ImageView addMileageBtn;
    private ImageView measureBtn;
    private ImageView saveActionBtn;
    private CadPresenter mPresenter;
    private SurveyProjectBean surveyProjectBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCAD();
        initView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showMeasureData();
            }
        },5000);
        surveyProjectBean = (SurveyProjectBean) getIntent().getSerializableExtra("survey_project");
        mPresenter = new CadPresenter();
        mPresenter.attachView(this);
    }

    private void initView() {
        addMileageBtn = findViewById(R.id.iv_add_cl_mileage);
        measureBtn = findViewById(R.id.iv_cl_measure_input);
        saveActionBtn = findViewById(R.id.iv_save_menu);
        addMileageBtn.setOnClickListener(this);
        measureBtn.setOnClickListener(this);
        saveActionBtn.setOnClickListener(this);
    }

    private void checkIsInit() {
        if (!isCadInit) {
            ProjectInitTipDialog initTipDialog = new ProjectInitTipDialog();
            initTipDialog.setListener(new ProjectInitTipDialog.OnDialogItemClickListener() {
                @Override
                public void onConfirmClick() {
                    //打开初始化界面

                }
            });
            initTipDialog.show(getSupportFragmentManager(),"InitTipDialog");
        }
    }

    private void loadCAD() {
        setContentView(R.layout.activity_cad_holder);
        drawer_holder = findViewById(R.id.drawer_holder);
        drawer_holder.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                drawer_holder.bringChildToFront(drawerView);
                drawer_holder.requestLayout();
            }
        });
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnStart();
            }
        });
        CocosResizeLayout frame = findViewById(R.id.rl_frame);
        CocosSurfaceView cadView = findViewById(R.id.view_cad);
        initInterfaceLayout(frame, null, cadView);
        MxFunction.asyncOpenBufferFile(Environment.getExternalStorageDirectory() + "/" + "Pictures" + "/" + "test.dwg");
        cadView.setZOrderOnTop(false);
//        View view = new View(this);
//        mWindowType = WindowManager.LayoutParams.TYPE_APPLICATION_MEDIA;
//        mLayout.flags &= ~WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
    }

    @Override
    public boolean returnStart() {
        return super.returnStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIsInit();
    }

    private void showMeasureData(){
        CLMeasureDialog measureDialog = new CLMeasureDialog();
        measureDialog.show(getSupportFragmentManager(),"cad_cl_measure");
    }

    private void showMileageInputDialog(){
        CLMileageInputDialog mileageInputDialog = new CLMileageInputDialog();
        mileageInputDialog.show(getSupportFragmentManager(),"mileage_input");
    }

    private void showMeasureInputDialog(){
        CLMeasureInputDialog measureInputDialog = new CLMeasureInputDialog();
        measureInputDialog.show(getSupportFragmentManager(),"measure_input");
    }

    LoadingDialog loadingDialog;
    private void showLoadingDialog() {
        loadingDialog = new LoadingDialog();
        loadingDialog.setCallBack(new LoadingDialog.OnDisMissCallBack() {
            @Override
            public void disMiss() {
                new DefaultDialog.Builder().title("保存成功，点击查看").onSureClickListener(new DefaultDialog.OnSureClickListener() {
                    @Override
                    public void sure() {

                    }

                    @Override
                    public void cancel() {

                    }
                }).build().show(getSupportFragmentManager(),"cl_tip");
            }
        });
        loadingDialog.show(getSupportFragmentManager(), "cl_measure_loading");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_cl_mileage:
                showMileageInputDialog();
                break;
            case R.id.iv_cl_measure_input:
                showMeasureInputDialog();
                break;
            case R.id.iv_save_menu:
                List<MeasureCLBean> datas = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    MeasureCLBean data = new MeasureCLBean();
                    data.setMileage("ZK");
                    data.setNum(i);
                    data.setMileageNum("ZK+"+i+"0");
                    data.setMileageX(i*10+"");
                    data.setMileageY(i*100+"");
                    data.setMileageH(i*1.0+"");
                    data.setMileageDetail("测试excel显示"+i);
                    datas.add(data);
                }
                mPresenter.saveCLData(datas);
                showLoadingDialog();
                break;
        }
    }

    @Override
    public void saveICMeasureSuccess() {
        loadingDialog.dismiss();
        //excel保存成功后提供查看功能
    }

    @Override
    public SurveyProjectBean getProjectInfo() {
        return surveyProjectBean;
    }
}
