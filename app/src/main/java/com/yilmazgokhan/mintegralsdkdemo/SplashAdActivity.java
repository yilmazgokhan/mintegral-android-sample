package com.yilmazgokhan.mintegralsdkdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mbridge.msdk.out.MBSplashHandler;
import com.mbridge.msdk.out.MBSplashLoadListener;
import com.mbridge.msdk.out.MBSplashShowListener;
import com.yilmazgokhan.mintegralsdkdemo.databinding.ActivitySplashAdBinding;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.SPLASH_PLACEMENT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.SPLASH_UNIT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.TAG;

public class SplashAdActivity extends AppCompatActivity {

    private ActivitySplashAdBinding binding;
    private MBSplashHandler mbSplashHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashAdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.initSplash();
        this.initClicks();
    }

    /**
     * Initialize Splash Ad Type
     */
    private void initSplash() {
        mbSplashHandler = new MBSplashHandler(SPLASH_PLACEMENT_ID, SPLASH_UNIT_ID);
        mbSplashHandler.setLoadTimeOut(10000);
        Button button = new Button(this);
        button.setText("logo");
        mbSplashHandler.setLogoView(button, 100, 100);

        this.setSplashLoadListener();
        this.setSplashShowListener();
    }

    /**
     * Initialize Splash Ad load listener
     */
    private void setSplashLoadListener() {
        mbSplashHandler.setSplashLoadListener(new MBSplashLoadListener() {
            @Override
            public void onLoadSuccessed(int reqType) {
                Log.e(TAG, "onLoadSuccessed" + reqType);
                Toast.makeText(SplashAdActivity.this, "onLoadSuccessed:" + reqType, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoadFailed(String msg, int reqType) {
                Log.e(TAG, "onLoadFailed" + msg + reqType);
                Toast.makeText(SplashAdActivity.this, "onLoadFailed:" + reqType, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Initialize Splash Ad show listener
     */
    private void setSplashShowListener() {
        mbSplashHandler.setSplashShowListener(new MBSplashShowListener() {
            @Override
            public void onShowSuccessed() {
                Log.e(TAG, "onShowSuccessed");
            }

            @Override
            public void onShowFailed(String msg) {
                Log.e(TAG, "onShowFailed" + msg);
            }

            @Override
            public void onAdClicked() {
                Log.e(TAG, "onAdClicked");
            }

            @Override
            public void onDismiss(int type) {
                Log.e(TAG, "onDismiss" + type);
                finish();
            }

            @Override
            public void onAdTick(long millisUntilFinished) {
                Log.e(TAG, "onAdTick" + millisUntilFinished);
            }
        });
    }

    /**
     * Initialize clicks
     */
    private void initClicks() {
        binding.loadSplash.setOnClickListener(view -> mbSplashHandler.preLoad());
        binding.showSplash.setOnClickListener(view -> mbSplashHandler.show(binding.getRoot()));
    }

    /**
     * Notify Mintegral SDK to show
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mbSplashHandler != null) {
            mbSplashHandler.onResume();
        }
    }

    /**
     * Notify Mintegral SDK to hide
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mbSplashHandler != null) {
            mbSplashHandler.onPause();
        }
    }

    /**
     * Notify Mintegral SDK to destroy
     */
    @Override
    protected void onDestroy() {
        if (mbSplashHandler != null) {
            mbSplashHandler.onDestroy();
        }
        super.onDestroy();
    }
}