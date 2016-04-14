package com.dmtaiwan.alexander.rxandroidtest;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.dmtaiwan.alexander.rxandroidtest.models.RxResponse;

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


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubscription = mPresenter.displayCacheData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSubscription.unsubscribe();
    }

    @Override
    public void showStations(RxResponse rxResponse) {
        handleResponse(rxResponse);
    }

    private void handleResponse(RxResponse rxResponse) {
        switch (rxResponse.getResponseType()) {
            case RxResponse.CACHE_CALL:
                mTextView.setText("CACHE DATA");
                mSubscription = mPresenter.displayNetworkData();
                break;
            case RxResponse.NETWORK_CALL:
                mTextView.setText("NETWORK DATA");
                break;
        }
    }

    @Override
    public void cacheFailed(String error) {
        Snackbar.make(mCoordinatorLayout, error, Snackbar.LENGTH_LONG).show();
        mSubscription = mPresenter.displayNetworkData();
    }

    @Override
    public void networkFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();

    }
}
