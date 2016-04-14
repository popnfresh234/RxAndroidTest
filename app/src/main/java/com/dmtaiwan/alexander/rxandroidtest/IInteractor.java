package com.dmtaiwan.alexander.rxandroidtest;

import com.dmtaiwan.alexander.rxandroidtest.models.RxResponse;

import rx.Observable;

/**
 * Created by Alexander on 4/14/2016.
 */
public interface IInteractor {
    Observable<RxResponse> getNetworkData();

    Observable<RxResponse> getCacheData();
}
