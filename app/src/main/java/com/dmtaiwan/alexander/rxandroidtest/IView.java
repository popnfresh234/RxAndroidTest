package com.dmtaiwan.alexander.rxandroidtest;

import com.dmtaiwan.alexander.rxandroidtest.models.AQStation;

import java.util.List;

/**
 * Created by Alexander on 4/14/2016.
 */
public interface IView {
    void showStations(List<AQStation> aqStations);
}
