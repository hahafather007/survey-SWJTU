package com.swjtu.survey.presenter;

import com.android.framekit.repo.RepositoryManager;
import com.swjtu.survey.bean.sheet.MeasureCLBean;
import com.swjtu.survey.contract.ICadView;
import com.swjtu.survey.repo.CadRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CadPresenter<T extends ICadView> {
    private T mViw;


    public void attachView(T attachView){
        mViw = attachView;
    }

    public void detachView(){
        mViw = null;
    }

    public void saveCLData(List<MeasureCLBean> clMeasures){
        Observable.create(new ObservableOnSubscribe<Boolean>() {

            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext(RepositoryManager.Companion.getInstance().dispatchRepository(CadRepository.class).saveCLData(mViw.getProjectInfo().getLocalPath(),clMeasures));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean isSuccess) {
                        mViw.saveICMeasureSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
