package com.dmtaiwan.alexander.rxandroidtest;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dmtaiwan.alexander.rxandroidtest.models.AQStation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;


public class MainActivity extends AppCompatActivity implements IView{

    private Presenter mPresenter;
    private Subscription mSubscription;

    @Bind(R.id.test_text)
    TextView mTextView;

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new Presenter(this, this);
        mSubscription = mPresenter.displayStations();

    }



    @Override
    public void showStations(List<AQStation> aqStations) {
        mTextView.setText(aqStations.toString());
    }

    @Override
    public void loadingFailed(String error) {
        Snackbar.make(mCoordinatorLayout, error, Snackbar.LENGTH_LONG).show();
    }
}
