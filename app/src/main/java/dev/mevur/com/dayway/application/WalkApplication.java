package dev.mevur.com.dayway.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by chengjiayi on 18/2/16.
 */

public class WalkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化地图
//        SDKInitializer.initialize(getApplicationContext());
        //初始化图片加载
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(getApplicationContext());
        ImageLoader.getInstance().init(configuration);

    }
}
