package com.yilmazgokhan.mintegralsdkdemo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mbridge.msdk.out.MBRewardVideoHandler;
import com.mbridge.msdk.out.RewardVideoListener;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.REWARDED_VIDEO_PLACEMENT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.REWARDED_VIDEO_VIDEO_UNIT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.TAG;

public class RewardedVideoAd {

    private Context context;
    private MBRewardVideoHandler mMBRewardVideoHandler;

    public RewardedVideoAd(Context context) {
        this.context = context;
        this.initRewarded();
    }

    /**
     * Initialize Rewarded Ads type
     */
    private void initRewarded() {
        mMBRewardVideoHandler = new MBRewardVideoHandler(context, REWARDED_VIDEO_PLACEMENT_ID, REWARDED_VIDEO_VIDEO_UNIT_ID);

        this.setRewardVideoListener();
    }

    /**
     * Initialize Rewarded Video Ad listener
     */
    private void setRewardVideoListener() {
        mMBRewardVideoHandler.setRewardVideoListener(new RewardVideoListener() {
            @Override
            public void onLoadSuccess(String placementId, String unitId) {
                Log.e(TAG, "initRewarded - onLoadSuccess: " + (TextUtils.isEmpty(placementId) ? "" : placementId) + "  " + unitId);
                Toast.makeText(context, "initRewarded - onLoadSuccess()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoLoadSuccess(String placementId, String unitId) {
                Log.e(TAG, "initRewarded - onVideoLoadSuccess: " + (TextUtils.isEmpty(placementId) ? "" : placementId) + "  " + unitId);
                Toast.makeText(context, "initRewarded - onVideoLoadSuccess()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoLoadFail(String errorMsg) {
                Log.e(TAG, "initRewarded - onVideoLoadFail errorMsg:" + errorMsg);
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShowFail(String errorMsg) {
                Log.e(TAG, "initRewarded - onShowFail=" + errorMsg);
                Toast.makeText(context, "initRewarded - errorMsg:" + errorMsg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdShow() {
                Log.e(TAG, "initRewarded - onAdShow");
                Toast.makeText(context, "initRewarded - onAdShow", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClose(boolean isCompleteView, String RewardName, float RewardAmout) {
                Log.e(TAG, "initRewarded - onAdClose rewardinfo :" + "RewardName:" + RewardName + "RewardAmout:" + RewardAmout + " isCompleteView：" + isCompleteView);
                Toast.makeText(context, "initRewarded - onADClose:" + isCompleteView + ",rName:" + RewardName + "，RewardAmout:" + RewardAmout, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoAdClicked(String placementId, String unitId) {
                Log.e(TAG, "initRewarded - onVideoAdClicked : " + (TextUtils.isEmpty(placementId) ? "" : placementId) + "  " + unitId);
                Toast.makeText(context, "initRewarded - onVideoAdClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoComplete(String placementId, String unitId) {
                Log.e(TAG, "initRewarded - onVideoComplete : " + (TextUtils.isEmpty(placementId) ? "" : placementId) + "  " + unitId);
                Toast.makeText(context, "initRewarded - onVideoComplete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEndcardShow(String placementId, String unitId) {
                Log.e(TAG, "initRewarded - onEndcardShow : " + (TextUtils.isEmpty(placementId) ? "" : placementId) + "  " + unitId);
                Toast.makeText(context, "initRewarded - onEndcardShow", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Does Rewarded Video Ad ready?
     *
     * @return
     */
    public boolean isReady() {
        return mMBRewardVideoHandler.isReady();
    }

    /**
     * Load Rewarded Video
     */
    public void load() {
        this.mMBRewardVideoHandler.load();
    }

    /**
     * Show Rewarded Video
     */
    public void show() {
        mMBRewardVideoHandler.show("mRewardId", "mUserId");
    }
}
