package dev.mevur.com.dayway.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chengjiayi on 18/2/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String dbName, CursorFactory factory, int version) {
        super(context, dbName, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS dayway (" +
                "id integer primary key autoincrement," +
                "time text," +
                "date text," +
                "lat varchar(30)," +
                "lon varchar(30)," +
                "address text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
