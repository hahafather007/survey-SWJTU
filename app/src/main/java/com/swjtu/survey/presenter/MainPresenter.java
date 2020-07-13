package com.swjtu.survey.presenter;

import com.android.framekit.repo.RepositoryManager;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.contract.MainContract;
import com.swjtu.survey.repo.MainRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    @Override
    public void attachView(MainContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public List<SurveyProjectBean> getHistoryProjects() {
        return RepositoryManager.Companion.getInstance().dispatchRepository(MainRepository.class).getHistoryProjects();
    }
}
