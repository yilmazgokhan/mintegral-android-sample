package com.yilmazgokhan.mintegralsdkdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.interactiveads.out.InteractiveAdsListener;
import com.mbridge.msdk.interactiveads.out.MBInteractiveHandler;

import java.util.HashMap;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.INTERACTIVE_ADS_PLACEMENT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.INTERACTIVE_ADS_UNIT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.TAG;

public class InteractiveAd {

    private Context context;
    private MBInteractiveHandler mbInteractiveHandler;

    public InteractiveAd(Context context) {
        this.context = context;
        this.initInteractive();
    }

    /**
     * Initialize Interactive Ad
     */
    private void initInteractive() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(MBridgeConstans.PLACEMENT_ID, INTERACTIVE_ADS_PLACEMENT_ID);
        hashMap.put(MBridgeConstans.PROPERTIES_UNIT_ID, INTERACTIVE_ADS_UNIT_ID);
        mbInteractiveHandler = new MBInteractiveHandler(context, hashMap);

        this.setInteractiveAdsListener();
    }

    /**
     * Initialize Interactive Ad listener
     */
    private void setInteractiveAdsListener() {
        mbInteractiveHandler.setInteractiveAdsListener(new InteractiveAdsListener() {
            @Override
            public void onInteractivelLoadSuccess(int restype) {
                Log.e(TAG, "onInteractivelLoadSuccess");
                Toast.makeText(context, "onInteractivelLoadSuccess()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterActiveMaterialLoadSuccess() {
                Toast.makeText(context, "onInterActiveMaterialloadSuccess()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInteractiveLoadFail(String errorMsg) {
                Log.e(TAG, "onInteractiveLoadFail");
                Toast.makeText(context, "onInteractiveLoadFail()" + errorMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInteractiveShowSuccess() {
                Log.e(TAG, "onInteractiveShowSuccess");
                Toast.makeText(context, "onInteractiveShowSuccess()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInteractiveShowFail(String errorMsg) {
                Log.e(TAG, "onInteractiveShowFail " + errorMsg);
                Toast.makeText(context, "onInteractiveShowFail()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInteractiveClosed() {
                Log.e(TAG, "onInteractiveClosed");
            }

            @Override
            public void onInteractiveAdClick() {
                Log.e(TAG, "onInteractiveAdClick");

            }

            @Override
            public void onInteractivePlayingComplete(boolean b) {
                Log.e(TAG, "onInteractivePlayingComplete: ");
            }
        });
    }

    /**
     * Load Interactive Ad
     */
    public void load() {
        this.mbInteractiveHandler.load();
    }

    /**
     * Show Interactive Ad
     */
    public void show() {
        this.mbInteractiveHandler.show();
    }
}
