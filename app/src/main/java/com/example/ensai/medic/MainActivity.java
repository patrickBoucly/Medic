package com.example.ensai.medic;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity {
    private Button buttonPharma;
    private Button ajouter_medic;
    private Button vaccin;
    private Button geoloc;
    private Button scan;
    private TextView textView;
    public SQLiteDatabase base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQLiteHelperCode bddCode=new MySQLiteHelperCode(this);
        MySQLiteHelper bddPharma=new MySQLiteHelper(this);
        CodeDAO daoCode=new CodeDAO(this);
        Log.i("zzz","ici");
/*
        AssetManager mngr = getAssets();

        try {
            InputStream iS = mngr.open("CIS_CIP.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));

            List<String> codes=readLines(reader);
            // read every line of the file into the line-variable, on line at the time
            int i=1;
            List<Code> mesCodes=null;
            for(String line:codes){

                    //Log.i("zzz", "a" + line.substring(0, 8) + "b" + line.substring(9)+"i:"+i);
                mesCodes.add(new Code(line.substring(0,8),line.substring(9)));
                i++;
            }
            for(Code c:mesCodes){
                daoCode.add(c);
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){

        }
        */
        //Log.i("taille",""+daoCode.getAll().size());

        buttonPharma = (Button) findViewById((R.id.menu_pharma));
        ajouter_medic = (Button) findViewById((R.id.ajouter_medic));
        vaccin = (Button) findViewById((R.id.vaccin));
        geoloc = (Button) findViewById((R.id.geoloc));
        textView = (TextView) findViewById(R.id.tv);
        scan= (Button) findViewById(R.id.scan);




    }

    public void vers_pharma(View v) {
        Toast.makeText(this, "Bienvenue sur l'écran Ma Pharmacie", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MaPharma.class);
        startActivity(intent);
    }
    public void vers_ajouter_medic(View v) {
        Toast.makeText(this, "Bienvenue sur l'écran Ajouter Medicament", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, AjouterMedic.class);
        startActivity(intent);
    }
    public void vers_vaccin(View v) {
        Toast.makeText(this, "Bienvenue sur l'écran Mes Vaccins", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MesVaccins.class);
        startActivity(intent);
    }
    public void vers_geoloc(View v) {
        Toast.makeText(this, "Bienvenue sur l'écran Geoloc", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Geoloc.class);
        startActivity(intent);
    }
    public void vers_scan(View v) {
        Toast.makeText(this, "Bienvenue sur l'écran Scan", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
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
