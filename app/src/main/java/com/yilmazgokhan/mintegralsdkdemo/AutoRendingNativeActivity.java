package com.yilmazgokhan.mintegralsdkdemo;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.out.AutoPlayMode;
import com.mbridge.msdk.out.MBMultiStateEnum;
import com.mbridge.msdk.out.MBNativeAdvancedHandler;
import com.mbridge.msdk.out.NativeAdvancedAdListener;
import com.yilmazgokhan.mintegralsdkdemo.databinding.ActivityAutoRendingNativeBinding;

import org.json.JSONException;
import org.json.JSONObject;

import static com.yilmazgokhan.mintegralsdkdemo.Constant.AUTO_RENDING_NATIVE_PLACEMENT_ID;
import static com.yilmazgokhan.mintegralsdkdemo.Constant.AUTO_RENDING_NATIVE_UNIT_ID;

public class AutoRendingNativeActivity extends AppCompatActivity {

    private ActivityAutoRendingNativeBinding binding;
    private MBNativeAdvancedHandler mbNativeAdvancedHandler;
    private ViewGroup mAdvancedNativeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAutoRendingNativeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            this.initAutoRendingNative();
            this.initClicks();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize Auto Rending Native Ads type
     *
     * @throws JSONException
     */
    private void initAutoRendingNative() throws JSONException {
        mbNativeAdvancedHandler = new MBNativeAdvancedHandler(this, AUTO_RENDING_NATIVE_PLACEMENT_ID, AUTO_RENDING_NATIVE_UNIT_ID);
        mbNativeAdvancedHandler.setNativeViewSize(320, 250);
        mbNativeAdvancedHandler.setCloseButtonState(MBMultiStateEnum.positive);
        mbNativeAdvancedHandler.setPlayMuteState(MBridgeConstans.REWARD_VIDEO_PLAY_MUTE);
        mbNativeAdvancedHandler.autoLoopPlay(AutoPlayMode.PLAY_WHEN_NETWORK_IS_AVAILABLE);

        String style = "{\n" +
                "    \"list\": [{\n" +
                "        \"target\": \"title\",\n" +
                "        \"values\": {\n" +
                "            \"paddingLeft\": 15,\n" +
                "            \"backgroundColor\": \"yellow\",\n" +
                "            \"fontSize\": 15,\n" +
                "            \"fontFamily\": \"Microsoft YaHei\",\n" +
                "            \"color\": \"red\"\n" +
                "        }\n" +
                "    }, {\n" +
                "        \"target\": \"mediaContent\",\n" +
                "        \"values\": {\n" +
                "            \"paddingTop\": 10,\n" +
                "            \"paddingRight\": 10,\n" +
                "            \"paddingBottom\": 10,\n" +
                "            \"paddingLeft\": 10\n" +
                "        }\n" +
                "    }]\n" +
                "}";
        JSONObject jsonObject = new JSONObject(style);
        mbNativeAdvancedHandler.setViewElementStyle(jsonObject);
        mAdvancedNativeView = mbNativeAdvancedHandler.getAdViewGroup();

        this.setAdListener();
    }

    /**
     * Initialize Auto Rending Native Ad listener
     */
    private void setAdListener() {
        mbNativeAdvancedHandler.setAdListener(new NativeAdvancedAdListener() {
            @Override
            public void onLoadSuccessed() {
                Toast.makeText(AutoRendingNativeActivity.this, "onLoadSuccessed:", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoadFailed(String msg) {
                Toast.makeText(AutoRendingNativeActivity.this, "onLoadFailed:", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLogImpression() {
                Toast.makeText(AutoRendingNativeActivity.this, "onLogImpression:", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClick() {
                Toast.makeText(AutoRendingNativeActivity.this, "onClick:", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLeaveApp() {
                Toast.makeText(AutoRendingNativeActivity.this, "onLeaveApp:", Toast.LENGTH_LONG).show();
            }

            @Override
            public void showFullScreen() {
                Toast.makeText(AutoRendingNativeActivity.this, "showFullScreen:", Toast.LENGTH_LONG).show();
            }

            @Override
            public void closeFullScreen() {
                Toast.makeText(AutoRendingNativeActivity.this, "closeFullScreen:", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClose() {
                Toast.makeText(AutoRendingNativeActivity.this, "onDismiss:", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Initialize clicks
     */
    private void initClicks() {
        binding.loadAutoRendingNative.setOnClickListener(view -> mbNativeAdvancedHandler.load());
        binding.showAutoRendingNative.setOnClickListener(view -> show());
    }

    /**
     * Show Auto Rending Native Ad
     */
    private void show() {
        this.binding.getRoot().addView(mAdvancedNativeView);
    }

    /**
     * Notify Mintegral SDK to show
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mbNativeAdvancedHandler != null) {
            mbNativeAdvancedHandler.onResume();
        }
    }

    /**
     * Notify Mintegral SDK to hide
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mbNativeAdvancedHandler != null) {
            mbNativeAdvancedHandler.onPause();
        }
    }

    /**
     * Notify Mintegral SDK to destroy
     */
    @Override
    protected void onDestroy() {
        if (mbNativeAdvancedHandler != null) {
            mbNativeAdvancedHandler.release();
        }
        super.onDestroy();
    }
}