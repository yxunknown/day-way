package dev.mevur.com.dayway;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.api.entity.OnEntityListener;
import com.baidu.trace.api.track.AddPointResponse;
import com.baidu.trace.api.track.AddPointsResponse;
import com.baidu.trace.api.track.ClearCacheTrackResponse;
import com.baidu.trace.api.track.DistanceResponse;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.LatestPoint;
import com.baidu.trace.api.track.LatestPointResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.QueryCacheTrackResponse;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;
import com.baidu.trace.model.StatusCodes;
import com.baidu.trace.model.TraceLocation;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingActivity extends AppCompatActivity {

    // 轨迹服务ID
    long serviceId = 160262;
    // 设备标识
    String entityName = "myTrace";
    // 是否需要对象存储服务，默认为：false，关闭对象存储服务。
    // 注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，
    // 且需导入bos-android-sdk-1.0.2.jar。
    boolean isNeedObjectStorage = false;
    // 初始化轨迹服务
    Trace mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
    // 初始化轨迹服务客户端
    LBSTraceClient mTraceClient = new LBSTraceClient(getApplicationContext());




    private ImageView loadImg;
    private ImageLoader imageLoader;
    @BindView(R.id.points)
    TextView point;

    @BindView(R.id.distance)
    TextView distance;

    @BindView(R.id.preview)
    Button preview;

    @BindView(R.id.start)
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }

    public void init() {

        // 定位周期(单位:秒)
        int gatherInterval = 30;
        // 打包回传周期(单位:秒)
        int packInterval = 600;
        //设置定位周期和数据打包上传周期
        mTraceClient.setInterval(gatherInterval, packInterval);

    }


    @OnClick(R.id.start)
    public void start(View view) {
        Snackbar.make(view, "sar", Snackbar.LENGTH_SHORT).show();
    }

    //初始化监听器
    private class TraceListener implements OnTraceListener {
        private final String TAG = "Trace Listener";
        @Override
        public void onBindServiceCallback(int i, String s) {
            Log.d(TAG, "onBindServiceCallback() called with: i = [" + i + "], s = [" + s + "]");
        }

        @Override
        public void onStartTraceCallback(int i, String s) {
            Log.d(TAG, "onStartTraceCallback() called with: i = [" + i + "], s = [" + s + "]");
        }

        @Override
        public void onStopTraceCallback(int i, String s) {
            Log.d(TAG, "onStopTraceCallback() called with: i = [" + i + "], s = [" + s + "]");
        }

        @Override
        public void onStartGatherCallback(int i, String s) {
            Log.d(TAG, "onStartGatherCallback() called with: i = [" + i + "], s = [" + s + "]");
        }

        @Override
        public void onStopGatherCallback(int i, String s) {
            Log.d(TAG, "onStopGatherCallback() called with: i = [" + i + "], s = [" + s + "]");
        }

        @Override
        public void onPushCallback(byte b, PushMessage pushMessage) {
            Log.d(TAG, "onPushCallback() called with: b = [" + b + "], pushMessage = [" + pushMessage + "]");
        }

        @Override
        public void onInitBOSCallback(int i, String s) {
            Log.d(TAG, "onInitBOSCallback() called with: i = [" + i + "], s = [" + s + "]");
        }
    }

    private class TrackListener extends OnTrackListener {
        private final String TAG = "Track Listener";

        @Override
        public void onLatestPointCallback(LatestPointResponse latestPointResponse) {
            super.onLatestPointCallback(latestPointResponse);
            if (StatusCodes.SUCCESS != latestPointResponse.getStatus()) {
                return;
            }
            LatestPoint point = latestPointResponse.getLatestPoint();
            System.out.println(point);
            Log.d(TAG, "onLatestPointCallback() called with: latestPointResponse = [" + latestPointResponse + "]");
        }

    }
    private class EntityListener extends OnEntityListener {
        @Override
        public void onReceiveLocation(TraceLocation traceLocation) {
            super.onReceiveLocation(traceLocation);
            System.out.println(traceLocation);
        }
    }



}
