package com.yilmazgokhan.mintegralsdkdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.interstitialvideo.out.InterstitialVideoListener;
import com.mbridge.msdk.interstitialvideo.out.MBInterstitialVideoHandler;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.INTERSTITIAL_VIDEO_PLACEMENT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.INTERSTITIAL_VIDEO_UNIT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.TAG;

public class InterstitialVideoAd {

    private Context context;
    private MBInterstitialVideoHandler mInterstitialVideoHandler;

    public InterstitialVideoAd(Context context) {
        this.context = context;
        this.initInterstitialVideo();
    }

    /**
     * Initialize Interstitial Video
     */
    private void initInterstitialVideo() {
        mInterstitialVideoHandler = new MBInterstitialVideoHandler(context, INTERSTITIAL_VIDEO_PLACEMENT_ID, INTERSTITIAL_VIDEO_UNIT_ID);

        this.setInterstitialVideoListener();
    }


    /**
     * Initialize Interstitial Video Ad listener
     */
    private void setInterstitialVideoListener() {
        mInterstitialVideoHandler.setInterstitialVideoListener(new InterstitialVideoListener() {

            @Override
            public void onLoadSuccess(String placementId, String unitId) {
                Log.e(TAG, "onLoadSuccess:" + Thread.currentThread());
                Toast.makeText(context, "onLoadSuccess()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoLoadSuccess(String placementId, String unitId) {
                Log.e(TAG, "onVideoLoadSuccess:" + Thread.currentThread());
                Toast.makeText(context, "onVideoLoadSuccess()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoLoadFail(String errorMsg) {
                Log.e(TAG, "onVideoLoadFail errorMsg:" + errorMsg);
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShowFail(String errorMsg) {
                Log.e(TAG, "onShowFail=" + errorMsg);
                Toast.makeText(context, "errorMsg:" + errorMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdShow() {
                Log.e(TAG, "onAdShow");
                Toast.makeText(context, "onAdShow", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClose(boolean isCompleteView) {
                Log.e(TAG, "onAdClose rewardinfo :" + "isCompleteView：" + isCompleteView);
                Toast.makeText(context, "onADClose:" + isCompleteView, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoAdClicked(String placementId, String unitId) {
                Log.e(TAG, "onVideoAdClicked");
                Toast.makeText(context, "onVideoAdClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoComplete(String placementId, String unitId) {
                Log.e(TAG, "onVideoComplete");
                Toast.makeText(context, "onVideoComplete", Toast.LENGTH_SHORT).show();
            }

            /**
             * If you called {@link MBInterstitialVideoHandler#setIVRewardEnable(int, int)}
             * will callback this method.
             * You can decide whether to give users a reward based on the return value.
             *
             * @param isComplete complete status.
             *                   This parameter indicates whether the video or playable has finished playing.
             *
             * @param rewardAlertStatus interstitialVideo reward  alert  window status.
             *                          This parameter is used to indicate the status of the alert dialog.
             *                          If the dialog is not shown, it will return {@link MBridgeConstans#IVREWARDALERT_STATUS_NOTSHOWN}
             *                          If the user clicks the dialog's continue button, it will return {@link MBridgeConstans#IVREWARDALERT_STATUS_CLICKCONTINUE}
             *                          If the user clicks the dialog's cancel button, it will return {@link MBridgeConstans#IVREWARDALERT_STATUS_CLICKCANCEL}
             *
             */
            @Override
            public void onAdCloseWithIVReward(boolean isComplete, int rewardAlertStatus) {

                Log.e(TAG, "onAdCloseWithIVReward");
                Log.e(TAG, isComplete ? "Video playback/playable is complete." : "Video playback/playable is not complete.");

                if (rewardAlertStatus == MBridgeConstans.IVREWARDALERT_STATUS_NOTSHOWN) {
                    Log.e(TAG, "The dialog is not show.");
                }

                if (rewardAlertStatus == MBridgeConstans.IVREWARDALERT_STATUS_CLICKCONTINUE) {
                    Log.e(TAG, "The dialog's continue button clicked.");
                }

                if (rewardAlertStatus == MBridgeConstans.IVREWARDALERT_STATUS_CLICKCANCEL) {
                    Log.e(TAG, "The dialog's cancel button clicked.");
                }
            }

            @Override
            public void onEndcardShow(String placementId, String unitId) {
                Log.e(TAG, "onEndcardShow");
                Toast.makeText(context, "onEndcardShow", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Load Interstitial Video Ad
     */
    public void load() {
        mInterstitialVideoHandler.load();
    }

    /**
     * Show Interstitial Video Ad
     */
    public void show() {
        mInterstitialVideoHandler.show();
    }
}
