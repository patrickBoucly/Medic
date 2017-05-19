package com.example.ensai.medic;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


/**
 * Created by ensai on 11/05/17.
 */
public class MaPharma extends Activity {
    Button bouton2 = null;
 ListView mes_medic;
    private MedicDAO medicDAO;
    private MonAdapter mesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapharma);
        mes_medic = (ListView) findViewById(R.id.mes_medic);

        medicDAO= new MedicDAO(this);
        medicDAO.open();
        //medicDAO.createMedic("bière","blonde");

        List<Medic> values = medicDAO.getAllMedics();
        List<String> noms=new ArrayList<String>();
        int i=0;
        for(Medic val : values){
            noms.add(i,val.getName());
            i++;
        }
        ArrayAdapter adapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1, noms);
        //MonAdapter adapter =new MonAdapter(values);

        ListView lv = (ListView)findViewById(R.id.mes_medic);
        lv.setAdapter(adapter);
    }



    /*public void clickBouton2(View v) {
        Toast.makeText(this, "Coucou2", Toast.LENGTH_LONG).show();
        Base base  = list_item Base(this);
        SQLiteDatabase writableDB = base.getReadableDatabase();
        ContentValues values = list_item ContentValues();
        values.put("nom", ((TextView) findViewById(R.id.zoneTexte1)).getText().toString());
        values.put("description",((TextView) findViewById(R.id.zoneTexte2)).getText().toString());
        writableDB.insert(base.getDatabaseName(), null, values);

        finish();
    }*/

}
