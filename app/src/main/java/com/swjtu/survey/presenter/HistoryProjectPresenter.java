package com.swjtu.survey.presenter;

import com.android.framekit.presenter.BasePresenter;
import com.android.framekit.repo.RepositoryManager;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.contract.HistoryProjectContract;
import com.swjtu.survey.repo.MainRepository;
import com.swjtu.survey.utils.LogUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryProjectPresenter extends BasePresenter<HistoryProjectContract.View> implements HistoryProjectContract.Presenter {
    @Override
    public void getHistoryProjects() {
        Observable.create(new ObservableOnSubscribe<ArrayList<SurveyProjectBean>>() {

            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext(RepositoryManager.Companion.getInstance().dispatchRepository(MainRepository.class).getHistoryProjects());
                LogUtil.i(null,"start-------");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<SurveyProjectBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<SurveyProjectBean> surveyProjectBeans) {
                        getMView().getHistoryProjectsSus(surveyProjectBeans);
                        LogUtil.i(null,"get-------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i(null,"err-------"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
