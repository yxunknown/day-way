package dev.mevur.com.dayway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.a;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

import dev.mevur.com.dayway.service.LocationService;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;

    private LocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.map);
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
