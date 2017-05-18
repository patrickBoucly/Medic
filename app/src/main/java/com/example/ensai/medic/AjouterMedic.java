package com.example.ensai.medic;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import static com.example.ensai.medic.R.id.tv2;

/**
 * Created by ensai on 11/05/17.
 */
public class AjouterMedic extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private TextView tv2;
    private EditText tv3;
    private String text;
    private String denom;
    private Button bouton_ajouter;
    private Button bouton_rechercher;
    private ListView resultats;
    private ArrayList<String> medic= new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutermedic);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3=(EditText) findViewById(R.id.tv3);
        bouton_ajouter = (Button) findViewById(R.id.bouton_ajouter);
        bouton_rechercher = (Button) findViewById(R.id.bouton_rechercher);
        resultats = (ListView) findViewById(R.id.resultats);
        bouton_rechercher.setOnClickListener(this);
        bouton_rechercher.setOnClickListener(this);
        Log.i("TEST1", tv3.getText().toString());
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bouton_ajouter:

                // do something when the corky is clicked

                break;
            case R.id.bouton_rechercher:

                Log.i("TEST1", tv3.getText().toString());
                String nom = tv3.getText().toString();
                String adresse = "https://open-medicaments.fr/api/v1/medicaments?query=" + nom;

                OkHttpClient okhttpClient = new OkHttpClient();
                Request myGetRequest = new Request.Builder()
                        .url(adresse)
                        .build();


                okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        //le retour est effectué dans un thread différent

                        try {
                            text = response.body().string();
                            JSONArray json = new JSONArray(text);
                            Log.d("test", json.toString());



                            for (int i = 0; i < json.length(); i++) {
                                JSONObject jsonobject = json.getJSONObject(i);
                                denom = jsonobject.getString("denomination");
                                /*String code = jsonobject.getString("codeCIS");
                                Log.d("test1", denom);
                                Log.d("test2", code);*/
                                medic.add(i,denom);
                            }



                        } catch (JSONException exc) {

                            exc.printStackTrace();
                        }


                       runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AjouterMedic.this,
                                        android.R.layout.simple_list_item_1, medic);
                                resultats.setAdapter(adapter);
                            }
                        }); // fin runOnUiThread
                    } // fin onResponse
                });//fin Callback


                break;

            default:
                break;
        }
    }
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

    }
}