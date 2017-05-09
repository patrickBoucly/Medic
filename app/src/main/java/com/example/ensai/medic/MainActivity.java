package com.example.ensai.medic;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;






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
        buttonPharma=(Button) findViewById((R.id.menu_pharma));
        ajouter_medic=(Button) findViewById((R.id.ajouter_medic));
        vaccin=(Button) findViewById((R.id.vaccin));
        geoloc=(Button) findViewById((R.id.geoloc));
        textView=(TextView) findViewById(R.id.tv);


        OkHttpClient okhttpClient = new OkHttpClient();
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
        });

    }
}
