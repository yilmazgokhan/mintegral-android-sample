package com.yilmazgokhan.mintegralsdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mbridge.msdk.MBridgeSDK;
import com.mbridge.msdk.out.MBridgeSDKFactory;
import com.mbridge.msdk.out.SDKInitStatusListener;
import com.yilmazgokhan.mintegralsdkdemo.databinding.ActivityMainBinding;

import java.util.Map;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.APP_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.APP_KEY;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.TAG;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RewardedVideoAd rewardedVideoAd;
    private StaticInterstitialAd staticInterstitialAd;
    private InterstitialVideoAd interstitialVideoAd;
    private InteractiveAd interactiveAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.initSDK();
        this.initAdTypes();
        this.initClicks();
    }

    /**
     * Initialize Mintegral SDK
     */
    private void initSDK() {
        MBridgeSDK sdk = MBridgeSDKFactory.getMBridgeSDK();
        Map<String, String> map = sdk.getMBConfigurationMap(APP_ID, APP_KEY);

        sdk.init(map, this, new SDKInitStatusListener() {
            @Override
            public void onInitSuccess() {
                Log.d(TAG, "initSDK - onInitSuccess: ");
            }

            @Override
            public void onInitFail() {
                Log.d(TAG, "initSDK - onInitFail: ");
            }
        });
    }

    /**
     * Initialize Mintegral SDK's Ads types
     */
    private void initAdTypes() {
        rewardedVideoAd = new RewardedVideoAd(this);
        staticInterstitialAd = new StaticInterstitialAd(this);
        interstitialVideoAd = new InterstitialVideoAd(this);
        interactiveAd = new InteractiveAd(this);
    }

    /**
     * Initialize clicks
     */
    private void initClicks() {
        //region Rewarded Ads
        binding.loadRewarded.setOnClickListener(view -> rewardedVideoAd.load());
        binding.showRewarded.setOnClickListener(view -> {
            if (rewardedVideoAd.isReady())
                rewardedVideoAd.show();
        });
        //endregion

        //region Static Interstitial
        binding.loadStaticInterstitial.setOnClickListener(view -> staticInterstitialAd.preload());
        binding.showStaticInterstitial.setOnClickListener(view -> staticInterstitialAd.show());
        //endregion

        //region Interstitial Video
        binding.loadInterstitialVideo.setOnClickListener(view -> interstitialVideoAd.load());
        binding.showInterstitialVideo.setOnClickListener(view -> interstitialVideoAd.show());
        //endregion

        //region Interactive
        binding.loadInteractive.setOnClickListener(view -> interactiveAd.load());
        binding.showInteractive.setOnClickListener(view -> interactiveAd.show());
        //endregion

        //region Splash
        binding.openSplash.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SplashAdActivity.class)));
        //endregion

        //region Auto Rending Native
        binding.openAutoRendingNative.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AutoRendingNativeActivity.class)));
        //endregion

        //region Banner
        binding.openBanner.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, BannerActivity.class)));
        //endregion
    }
}