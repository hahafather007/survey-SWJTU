package com.swjtu.survey.contract;

import com.android.framekit.contract.BaseContract;
import com.swjtu.survey.bean.SurveyProjectBean;

import java.util.List;

public interface HistoryProjectContract {
    interface View extends BaseContract.BaseView{
        void getHistoryProjectsSus(List<SurveyProjectBean> surveys);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getHistoryProjects();
    }
}
