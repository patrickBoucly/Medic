package com.example.ensai.medic;

/**
 * Created by ensai on 18/05/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// on importe les classes IntentIntegrator et IntentResult de la librairie zxing

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Scan extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);

        Button mybutton = (Button) findViewById(R.id.scan_button);
        mybutton.setOnClickListener(this);

        AssetManager mngr=this.getAssets();

        Log.i("ini" ,"en cours");

        CodeDAO dao=new CodeDAO(this);


        if(dao.getAll().size()==0) {
            try {
                InputStream iS = mngr.open("CIS_CIP.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
                List<String> lignes = readLines(reader);
                for (String line : lignes) {
                        String cis=line.substring(0, 8);
                        String cip=line.substring(9);
                        dao.add(new Code(cis,cip));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.scan_button){

// on lance le scanner au clic sur notre bouton
            new IntentIntegrator(this).initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

// nous utilisons la classe IntentIntegrator et sa fonction parseActivityResult pour parser le résultat du scan
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

// nous récupérons le contenu du code barre
            String scanContent = scanningResult.getContents();

// nous récupérons le format du code barre
            String scanFormat = scanningResult.getFormatName();

            TextView scan_format = (TextView) findViewById(R.id.scan_format);
            TextView scan_content = (TextView) findViewById(R.id.scan_content);

// nous affichons le résultat dans nos TextView
            CodeDAO dao =new CodeDAO(this);

            String cis=dao.getCIS(scanContent);
            scan_format.setText("FORMAT: " + scanFormat);
            scan_content.setText("CIS: " + cis);



        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Aucune donnée reçu!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
            return true;
        //}

       // return super.onOptionsItemSelected(item);
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