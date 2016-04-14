package com.dmtaiwan.alexander.rxandroidtest;

import com.dmtaiwan.alexander.rxandroidtest.models.AQStation;

import java.util.List;

import rx.Observable;

/**
 * Created by Alexander on 4/14/2016.
 */
public interface IInteractor {
    Observable<List<AQStation>> getStationsNetwork();

    Observable<List<AQStation>> getStationsDisk();
}
