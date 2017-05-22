package com.example.ensai.medic;

/**
 * Created by ensai on 19/05/17.
 */

import java.io.BufferedReader;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;



public class MedicDAO {
    public static final String SAVE = "INSERT INTO pharmacie VALUES (NULL, ?, ?);";
    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_CIS,
            MySQLiteHelper.COLUMN_Name};

    public MedicDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public void add(String name,String cis){

        SQLiteStatement statement = database.compileStatement(SAVE);

        statement.bindString(1, name);
        statement.bindString(2, cis);

        statement.execute();

        statement.close();

    }

    /*
    public Medic createMedic(String name,String cis) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CIS, cis);
        values.put(MySQLiteHelper.COLUMN_Name, name);
       /* String res="";
        for(String key :values.keySet()){
            res+=key+"    ";
        }

        Log.i("key     ",""+res);


        long insertId = database.insert(MySQLiteHelper.TABLE_PHARMACIE, null,
                values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PHARMACIE,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Medic newMedic = cursorToMedic(cursor);
        cursor.close();
        return newMedic;
    }*/


    public void deleteMedicCIS(Medic medic) {
        String cis = medic.getCodeCIS();
        System.out.println("Medicament ayant le code CIS suivant effacé :" + cis);
        database.delete(MySQLiteHelper.TABLE_PHARMACIE, MySQLiteHelper.COLUMN_CIS
                + " = " + cis, null);
    }



    public List<Medic> getAllMedics() {
        List<Medic> medics = new ArrayList<Medic>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PHARMACIE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Medic medic = cursorToMedic(cursor);
            medics.add(medic);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return medics;
    }

    private Medic cursorToMedic(Cursor cursor) {
        Medic medic = new Medic(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        return medic;
    }
    public static List<String> readLines(BufferedReader reader) throws Exception {

        List<String> results = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            results.add(line);
            line = reader.readLine();
        }
        return results;
    }



}