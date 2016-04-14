package com.dmtaiwan.alexander.rxandroidtest;

import android.content.Context;
import android.util.Log;

import com.dmtaiwan.alexander.rxandroidtest.models.AQStation;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Alexander on 4/14/2016.
 */
public class Presenter implements IPresenter {

    private IInteractor interactor;
    private IView view;


    public Presenter(IView view, Context context) {
        this.view = view;
        interactor = new Interactor(context);
    }

    @Override
    public Subscription displayStations() {

        return interactor.getStationsDisk()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> {

                })
                .subscribe(new Subscriber<List<AQStation>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("OnCompleted", "OnCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError", "onError");
                        view.loadingFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<AQStation> stations) {
                        view.showStations(stations);
                    }
                });
    }
}
