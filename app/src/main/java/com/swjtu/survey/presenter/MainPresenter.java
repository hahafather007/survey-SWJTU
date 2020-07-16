package com.swjtu.survey.presenter;

import com.android.framekit.presenter.BasePresenter;
import com.android.framekit.repo.RepositoryManager;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.contract.MainContract;
import com.swjtu.survey.repo.MainRepository;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void createNewProject(String projectName) {
        String path = RepositoryManager.Companion.getInstance().dispatchRepository(MainRepository.class).createNewProject(projectName);
        if (path != null) {
           SurveyProjectBean surveyProjectBean =  RepositoryManager.Companion.getInstance().dispatchRepository(MainRepository.class).createProjectSignature(path, projectName);
            if (getMView() != null) {
                getMView().createNewProjectSus(path, surveyProjectBean);
            }
        }
    }
}
