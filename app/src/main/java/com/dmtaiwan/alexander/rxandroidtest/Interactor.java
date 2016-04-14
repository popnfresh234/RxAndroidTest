package com.dmtaiwan.alexander.rxandroidtest;

import com.dmtaiwan.alexander.rxandroidtest.models.AQStation;

import java.util.List;

import rx.Observable;

/**
 * Created by Alexander on 4/14/2016.
 */
public class Interactor implements IInteractor{


    @Override
    public Observable<List<AQStation>> getStationsNetwork() {
        return null;
    }

    @Override
    public Observable<List<AQStation>> getStationsDisk() {
        return null;
    }
}
