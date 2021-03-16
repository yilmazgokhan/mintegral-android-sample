package com.yilmazgokhan.mintegralsdkdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mbridge.msdk.out.BannerAdListener;
import com.mbridge.msdk.out.BannerSize;
import com.mbridge.msdk.out.MBBannerView;
import com.yilmazgokhan.mintegralsdkdemo.databinding.ActivityBannerBinding;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.BANNER_PLACEMENT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.BANNER_UNIT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.TAG;

public class BannerActivity extends AppCompatActivity {

    private ActivityBannerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.initBanner();
    }

    /**
     * Initialize & show Banner Ads type
     */
    private void initBanner() {
        MBBannerView mbBannerView = findViewById(R.id.mb_banner_view);
        mbBannerView.init(new BannerSize(BannerSize.DEV_SET_TYPE, 320, 90), BANNER_PLACEMENT_ID, BANNER_UNIT_ID);
        mbBannerView.setAllowShowCloseBtn(true);
        mbBannerView.setRefreshTime(15);

        this.setBannerAdListener(mbBannerView);
    }

    /**
     * Initialize Banner Ad listener
     *
     * @param mbBannerView
     */
    private void setBannerAdListener(MBBannerView mbBannerView) {
        mbBannerView.setBannerAdListener(new BannerAdListener() {
            @Override
            public void onLoadFailed(String msg) {
                Log.d(TAG, "initBanner - onLoadFailed: " + msg);
            }

            @Override
            public void onLoadSuccessed() {
                Log.d(TAG, "initBanner - onLoadSuccessed: ");
            }

            @Override
            public void onClick() {
                Log.d(TAG, "initBanner - onClick: ");
            }

            @Override
            public void onLeaveApp() {
                Log.d(TAG, "initBanner - onLeaveApp: ");
            }

            @Override
            public void showFullScreen() {
                Log.d(TAG, "initBanner - showFullScreen: ");
            }

            @Override
            public void closeFullScreen() {
                Log.d(TAG, "initBanner - closeFullScreen: ");
            }

            @Override
            public void onLogImpression() {
                Log.d(TAG, "initBanner - onLogImpression: ");
            }

            @Override
            public void onCloseBanner() {
                Log.d(TAG, "initBanner - onCloseBanner: ");
            }
        });
        mbBannerView.load();
    }
}