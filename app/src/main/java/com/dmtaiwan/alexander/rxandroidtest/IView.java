package com.dmtaiwan.alexander.rxandroidtest;

import com.dmtaiwan.alexander.rxandroidtest.models.RxResponse;

/**
 * Created by Alexander on 4/14/2016.
 */
public interface IView {


    void showStations(RxResponse rxResponse);

    void cacheFailed(String error);

    void networkFailed(String error);

}
