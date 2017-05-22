package com.example.ensai.medic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ensai on 22/05/17.
 */

public class MySQLiteHelperCode extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "_id";
    public static final String TABLE_CODE ="code";
    private static final String DATABASE_NAME = "codes";
    public static final String COLUMN_CIS = "Code CIS";
    public static final String COLUMN_CIP = "Code CIP";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE_CODE="create table "
            + TABLE_CODE + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_CIS
            +" text not null,"+COLUMN_CIP+" text not null);";
    public MySQLiteHelperCode(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_CODE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CODE);
        onCreate(db);
    }

}
