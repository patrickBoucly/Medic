package com.example.ensai.medic;

/**
 * Created by ensai on 19/05/17.
 */
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private String[] allColumnsMedic = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_CIS,
            MySQLiteHelper.COLUMN_Name};
    public static final String SAVE_CODE = "INSERT INTO code VALUES (NULL, ?, ?);";
    public static final String SEARCH_ALL_CODE= "SELECT * from code;";
    private String[] allColumns_code = {MySQLiteHelper.COLUMN_CIS,
            MySQLiteHelper.COLUMN_CIP};
    public static final String TABLE_PHARMACIE ="pharmacie";
    public static final String SAVE_MEDIC = "INSERT INTO " + TABLE_PHARMACIE +" VALUES (NULL, ?, ?);";
    public static final String COLUMN_Name = "Nom";
    private static final String DATABASE_NAME = "pharmacie";
    public static final String COLUMN_ID = "_id";
    private static final int DATABASE_VERSION = 5;
    public static final String COLUMN_CIS = "Code CIS";
    public static final String TABLE_CODE ="code";
    public static final String COLUMN_CIP = "Code CIP";
      // Commande sql pour la création de la base de données


    private static final String DATABASE_CREATE_PHARMACIE = "create table pharmacie(id integer primary key autoincrement, nom text not null, cis text not null, FOREIGN KEY (cis) REFERENCES code(cis));";


    private static final String DATABASE_CREATE_CODE="create table code(idcode integer primary key autoincrement,cis text not null, cip text not null);";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("const","avt db");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.v("INFO1","avt db");
        database.execSQL(DATABASE_CREATE_PHARMACIE);
        database.execSQL(DATABASE_CREATE_CODE);
        Log.v("INFO1","creating db");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("INFO1","onup");
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMACIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CODE);
        onCreate(db);
    }




    /*CRUD*/



    /*Medic*/

    public void add(String name,String cis){
        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteStatement statement = db.compileStatement(SAVE_MEDIC);

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
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(MySQLiteHelper.TABLE_PHARMACIE, MySQLiteHelper.COLUMN_CIS
                + " = " + cis, null);
    }



    public List<Medic> getAllMedics() {
        List<Medic> medics = new ArrayList<Medic>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_PHARMACIE,
                allColumnsMedic, null, null, null, null, null);

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



/*Code*/

    public void add(Code code){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(SAVE_CODE);

        statement.bindString(1, code.getCis());
        statement.bindString(2, code.getCip());
        statement.execute();
        statement.close();

    }
    public String getCIS(String cip){
        SQLiteDatabase db = this.getWritableDatabase();
        String res="";
        String req="SELECT cis from code where cip="+cip;


        Cursor cursor = db.rawQuery(req, null);
        if (cursor.moveToFirst()) {
            do {
                res=cursor.getString(1);
            } while (cursor.moveToNext());
        }
        this.close();
        return res;
    }
    public List<Code> getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Code> res=null;
        Cursor cursor = db.rawQuery(SEARCH_ALL_CODE, null);
        if (cursor.moveToFirst()) {
            do {
                res.add(new Code(cursor.getString(1),cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return res;
    }
    public void initialize(AssetManager mngr){
        Log.i("ini" ,"en cours");


        try {
            InputStream iS = mngr.open("CIS_CIP.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            List<String> lignes=readLines(reader);
            for(String line:lignes){
                this.add(new Code(line.substring(0,8),line.substring(9)));
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
//        Log.i("test" ,""+this.getAll().size());
        Log.i("size" ,""+this.getAll().size());
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
