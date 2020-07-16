package com.swjtu.survey.contract;

import com.android.framekit.contract.BaseContract;
import com.swjtu.survey.bean.SurveyProjectBean;

import java.util.List;

public interface MainContract {
    interface View extends BaseContract.BaseView{
        void createNewProjectSus(String path,SurveyProjectBean surveyProjectBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        /**
         * 创建新工程，工程存放地址于SDCard中，根地址为system_survey/project，每次创建前检查该目录是否存在，不存在则新建
         */
        void createNewProject(String projectName);

     }
}
