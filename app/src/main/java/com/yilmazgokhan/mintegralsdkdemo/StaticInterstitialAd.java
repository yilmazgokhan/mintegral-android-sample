package com.yilmazgokhan.mintegralsdkdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.out.InterstitialListener;
import com.mbridge.msdk.out.MBInterstitialHandler;

import java.util.HashMap;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.STATIC_INTERSTITIAL_PLACEMENT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.STATIC_INTERSTITIAL_UNIT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.TAG;

public class StaticInterstitialAd {

    private Context context;
    private MBInterstitialHandler mInterstitialHandler;

    public StaticInterstitialAd(Context context) {
        this.context = context;
        this.initStaticInterstitial();
    }

    /**
     * Initialize Static Interstitial
     */
    private void initStaticInterstitial() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(MBridgeConstans.PLACEMENT_ID, STATIC_INTERSTITIAL_PLACEMENT_ID);
        hashMap.put(MBridgeConstans.PROPERTIES_UNIT_ID, STATIC_INTERSTITIAL_UNIT_ID);
        mInterstitialHandler = new MBInterstitialHandler(context, hashMap);

        this.setInterstitialListener();
    }

    /**
     * Initialize Interstitial Ad listener
     */
    private void setInterstitialListener() {
        mInterstitialHandler.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialShowSuccess() {
                Log.e(TAG, "onInterstitialShowSuccess");
            }

            @Override
            public void onInterstitialShowFail(String errorMsg) {
                Log.e(TAG, "onInterstitialShowFail errorMsg:" + errorMsg);
            }

            @Override
            public void onInterstitialLoadSuccess() {
                Log.e(TAG, "onInterstitialLoadSuccess");
                Toast.makeText(context, "onInterstitialLoadSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialLoadFail(String errorMsg) {
                Log.e(TAG, "onInterstitialLoadFail errorMsg:" + errorMsg);
            }

            @Override
            public void onInterstitialClosed() {
                Log.e(TAG, "onInterstitialClosed");
            }

            @Override
            public void onInterstitialAdClick() {
                Log.e(TAG, "onInterstitialAdClick");
            }
        });
    }

    /**
     * Load Static Interstitial Ad
     */
    public void preload() {
        this.mInterstitialHandler.preload();
    }

    /**
     * Show Static Interstitial Ad
     */
    public void show() {
        this.mInterstitialHandler.show();
    }
}
