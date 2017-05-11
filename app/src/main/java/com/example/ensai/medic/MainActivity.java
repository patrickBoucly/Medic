package com.example.ensai.medic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private Button buttonPharma;
    private Button ajouter_medic;
    private Button vaccin;
    private Button geoloc;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPharma = (Button) findViewById((R.id.menu_pharma));
        ajouter_medic = (Button) findViewById((R.id.ajouter_medic));
        vaccin = (Button) findViewById((R.id.vaccin));
        geoloc = (Button) findViewById((R.id.geoloc));
        textView = (TextView) findViewById(R.id.tv);


        /*OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder()
                .url("https://open-medicaments.fr/api/v1/medicaments?query=doliprane")
                .build();
        okhttpClient.newCall(myGetRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                //le retour est effectué dans un thread différent
                final String text = response.body().string();
                JSONObject object=null;
                final int statusCode = response.code();
                try {

                    JSONObject Jobject = new JSONObject(text);
                    JSONArray Jarray = Jobject.getJSONArray("denomination");

                    for (int i = 0; i < Jarray.length(); i++) {
                        object = Jarray.getJSONObject(i);

                        System.out.println(object.getString("denomination"));
                    }

                    final JSONObject monObject=object;


                }catch(JSONException ex){
                    ex.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(text);
                    }
                });
            }
        });*/

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


}
