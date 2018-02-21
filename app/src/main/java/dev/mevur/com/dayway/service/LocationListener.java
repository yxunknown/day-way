package dev.mevur.com.dayway.service;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.a;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import dev.mevur.com.dayway.database.DayWayDB;
import dev.mevur.com.dayway.entity.Location;

/**
 * Created by chengjiayi on 18/2/16.
 */

public class LocationListener extends a {

    private Context context;
    private DayWayDB db;
    private static String TAG = "LocationListener";

    public LocationListener(Context context) {
        this.context = context;
        db = DayWayDB.getInstance(this.context);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        Location location = new Location();
        location.setLon(bdLocation.getLongitude());
        location.setLat(bdLocation.getLatitude());
        location.setAddress(bdLocation.getAddrStr());
        location.setTime(getTime());
        location.setDate(getDate());
        db.insertLocation(location);
        Log.i(TAG, "onReceiveLocation: " + location);
    }

    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        Long time = System.currentTimeMillis();
        return format.format(new Date(time));
    }
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Long time = System.currentTimeMillis();
        return format.format(new Date(time));

    }
}
