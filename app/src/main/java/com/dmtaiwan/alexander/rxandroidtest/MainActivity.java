package com.dmtaiwan.alexander.rxandroidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dmtaiwan.alexander.rxandroidtest.models.AQStation;

import java.util.List;


public class MainActivity extends AppCompatActivity implements IView{


    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.test_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }



    @Override
    public void showStations(List<AQStation> aqStations) {

    }
}
