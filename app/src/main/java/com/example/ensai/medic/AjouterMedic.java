package com.example.ensai.medic;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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

import static com.example.ensai.medic.R.id.tv2;

/**
 * Created by ensai on 11/05/17.
 */
public class AjouterMedic extends Activity {

    private EditText tv3;
    private  String text;
    private String denom;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutermedic);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
         tv3 = (EditText) findViewById(R.id.tv3);
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

                try {
                    text = response.body().string();
                    JSONArray json = new JSONArray(text);
                    Log.d("test", json.toString());

                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonobject = json.getJSONObject(i);
                         denom = jsonobject.getString("denomination");
                        String code = jsonobject.getString("codeCIS");
                        Log.d("test1", denom);
                        Log.d("test2",code);

                    }

                } catch (JSONException exc){

                    exc.printStackTrace();
                }




                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TEST", text);
                        tv3.setText(text);
                    }
                }); // fin runOnUiThread
            } // fin onResponse
        });//fin Callback

    }



}