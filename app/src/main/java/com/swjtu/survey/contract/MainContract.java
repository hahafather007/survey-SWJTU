package com.swjtu.survey.contract;

import com.android.framekit.contract.BaseContract;
import com.swjtu.survey.bean.SurveyProjectBean;

import java.util.List;

public interface MainContract {
    interface View extends BaseContract.BaseView{

    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        List<SurveyProjectBean> getHistoryProjects();
     }
}
