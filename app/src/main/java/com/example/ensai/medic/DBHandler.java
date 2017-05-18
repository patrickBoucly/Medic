package com.example.ensai.medic;

/**
 * Created by ensai on 18/05/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME ="bdd";
    // Contacts table name
    private static final String TABLE_PHARMACIE ="pharmacie";
    // Shops Table Columns names
    private static final String KEY_ID ="id";
    private static final String KEY_NAME ="name";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE ="CREATE TABLE " + TABLE_PHARMACIE +"("+ KEY_ID +"INTEGER PRIMARY KEY," + KEY_NAME +"TEXT"+" )";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMACIE);
// Creating tables again
        onCreate(db);
    }

    // Adding new shop
    public void addMedic(Medic medic) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, medic.getName());
        values.put(KEY_ID, medic.getIdMedic());

// Inserting Row
        db.insert(TABLE_PHARMACIE, null, values);
        db.close(); // Closing database connection
    }

    public Medic getMedic(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PHARMACIE, new String[]{KEY_ID,
                KEY_NAME}, KEY_ID + "=?",
        new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Medic monMedic = new Medic(cursor.getString(1),cursor.getInt(0));

        return monMedic;
    }
    // Getting All Shops
    public List<Medic> getAllShops() {
        List<Medic> medicList = new ArrayList<Medic>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PHARMACIE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Medic medic = new Medic(cursor.getString(1),cursor.getInt(0));

                medicList.add(medic);
            } while (cursor.moveToNext());
        }

        return medicList;
    }
    // Getting shops Count
    public int getMedicCount() {
        String countQuery = "SELECT * FROM " + TABLE_PHARMACIE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateMedic(Medic medic) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, medic.getName());

// updating row
        return db.update(TABLE_PHARMACIE, values, KEY_ID + " = ?",
        new String[]{String.valueOf(medic.getIdMedic())});
    }

    // Deleting a shop
    public void deleteShop(Medic shop) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PHARMACIE, KEY_ID + " = ?",
        new String[] { String.valueOf(shop.getIdMedic()) });
        db.close();
    }
}



