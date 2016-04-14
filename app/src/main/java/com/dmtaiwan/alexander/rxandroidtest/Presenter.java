package com.dmtaiwan.alexander.rxandroidtest;

import android.content.Context;
import android.util.Log;

import com.dmtaiwan.alexander.rxandroidtest.models.RxResponse;

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
    public Subscription displayCacheData() {
        return interactor.getCacheData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RxResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("CACHE FAILED", "CACHE FAILED");
                        view.cacheFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(RxResponse rxResponse) {
                        view.showStations(rxResponse);
                    }
                });
    }

    @Override
    public Subscription displayNetworkData() {
        return interactor.getNetworkData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RxResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("NETWORK FAILED", "NETWORK FAILED");

                        view.networkFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(RxResponse rxResponse) {
                        view.showStations(rxResponse);
                    }
                });
    }
}
