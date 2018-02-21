package dev.mevur.com.dayway.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.mevur.com.dayway.R;
import dev.mevur.com.dayway.entity.Location;

/**
 * Created by chengjiayi on 18/2/17.
 */

public class DayWayDB {
    private static final String DB_NAME = "day_way";
    private static final int VERSION = 1;
    private static DayWayDB dayWayDB;
    private SQLiteDatabase db;

    private static String TABLE_DAYWAY = "dayway";

    private DayWayDB(Context context) {
        DBHelper helper = new DBHelper(context, DB_NAME, null, VERSION);
        db = helper.getWritableDatabase();
    }

    public synchronized static DayWayDB getInstance(Context context) {
        if (null == dayWayDB) {
            dayWayDB = new DayWayDB(context);
        }
        return dayWayDB;
    }

    public boolean insertLocation(Location location) {
        ContentValues values = new ContentValues();
        values.put("lat", location.getLat());
        values.put("lon", location.getLon());
        values.put("address", location.getAddress());
        values.put("time", location.getTime());
        values.put("date", location.getDate());
        return db.insert(TABLE_DAYWAY, null, values) == 1;
    }

    public List<Location> queryLocation(String selection, String[] args) {
        Cursor cursor = db.query(TABLE_DAYWAY, null, selection, args, null, null, null);
        List<Location> locations = null;
        if (cursor.moveToFirst()) {
            locations = new ArrayList<>();
            do {
                Location location = new Location();
                location.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                location.setDate(cursor.getString(cursor.getColumnIndex("date")));
                location.setTime(cursor.getString(cursor.getColumnIndex("time")));
                location.setLat(cursor.getDouble(cursor.getColumnIndex("lat")));
                location.setLon(cursor.getDouble(cursor.getColumnIndex("lon")));
                locations.add(location);
            } while (cursor.moveToNext());
        }
        return locations;
    }



}
