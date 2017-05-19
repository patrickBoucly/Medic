package com.example.ensai.medic;

/**
 * Created by ensai on 19/05/17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_PHARMACIE ="pharmacie";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_Name = "Nom";
    public static final String COLUMN_CIS = "Code CIS";
    private static final String DATABASE_NAME = "ma pharmacie";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PHARMACIE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_Name
            + " text not null, " +COLUMN_CIS+" text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMACIE);
        onCreate(db);
    }
}