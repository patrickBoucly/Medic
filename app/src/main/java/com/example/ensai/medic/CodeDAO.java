package com.example.ensai.medic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ensai on 22/05/17.
 */

public class CodeDAO {
    public static final String SAVE = "INSERT INTO code VALUES (NULL, ?, ?);";
    public static final String SHEARCH = "SELECT cis from code where cip=?);";
    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelperCode dbHelper;
    private String[] allColumns = { MySQLiteHelperCode.COLUMN_ID,MySQLiteHelperCode.COLUMN_CIS,
            MySQLiteHelperCode.COLUMN_CIP};

    public CodeDAO(Context context) {
        dbHelper = new MySQLiteHelperCode(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void add(Code code){
        SQLiteStatement statement = database.compileStatement(SAVE);

        statement.bindString(1, code.getCis());
        statement.bindString(2, code.getCip());
        statement.execute();
        statement.close();

    }
    public String getCIS(String cip){
        String res="";
        String req="SELECT cis from code where cip="+cip;
        Cursor cursor = database.rawQuery(req, null);
        if (cursor.moveToFirst()) {
            do {
                res=cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return res;
    }
    public List<Code> getAll(){
        List<Code> res=null;
        String req="SELECT cis,cip from code";
       Log.i("path", database.getPath().toString());
        Cursor cursor = database.rawQuery(req, null);
        if (cursor.moveToFirst()) {
            do {
                res.add(new Code(cursor.getString(1),cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return res;
    }
}
