package com.example.ensai.medic;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;

/**
 * Created by ensai on 11/05/17.
 */
public class MaPharma extends Activity {
    Button bouton2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapharma);
       // bouton2 = (Button) findViewById(R.id.boutonAjouter);
    }
    /*public void clickBouton2(View v) {
        Toast.makeText(this, "Coucou2", Toast.LENGTH_LONG).show();
        Base base  = new Base(this);
        SQLiteDatabase writableDB = base.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", ((TextView) findViewById(R.id.zoneTexte1)).getText().toString());
        values.put("description",((TextView) findViewById(R.id.zoneTexte2)).getText().toString());
        writableDB.insert(base.getDatabaseName(), null, values);

        finish();
    }*/

}
