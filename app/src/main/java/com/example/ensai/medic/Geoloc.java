
package com.example.ensai.medic;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.MapView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.R.id.list;
import static com.example.ensai.medic.R.id.resultats;

public class Geoloc extends Activity {

    private EditText tv3;
    private  String text;
    private String denom;

    List<String> list = new ArrayList<String>();

    private LocationManager locationManager;
    private LocationListener locationListener;
    double latitude=0;
    double longitude=0;
    Location coordonnees;
    ListView resultats;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geoloc);
        //TextView tv2 = (TextView) findViewById(R.id.tv2);
       // listeView = (ListView) findViewById(R.id.maVue);
        //tv3 = (EditText) findViewById(R.id.tv3);
        resultats = (ListView) findViewById(R.id.resultats_geoloc);

// on demande à l'utilisateur l'acces au GPS (obligatoire depuis la version 6.0)
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        //cela renvoie à la fonction onRequestPermissionsResult


    }







    // fonction qui gère les autorisations d'acces au gps de l'utilisateur
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(Build.VERSION.SDK_INT >= 23) {
                        if (this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                && this.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                            //---use the LocationManager class to obtain GPS locations---


                            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                            locationListener = new MyLocationListener();
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    0,
                                    0,
                                    locationListener);
                             coordonnees = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            //coordonnees=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                            if(coordonnees==null){
                                Toast.makeText(this, "Dernière localisation impossible", Toast.LENGTH_LONG).show();
                                coordonnees=new Location("");//provider name is unnecessary
                                coordonnees.setLatitude(48.033329d);//your coords of course
                                coordonnees.setLongitude(-1.75d);
                            }


                             latitude = coordonnees.getLatitude();
                             longitude=coordonnees.getLongitude();

                            Log.d("Coord.brutes", "Coordonnées:"+" latitude=" +latitude+" , longitude= "+longitude);


                            // on lance la requete des pharmacies autour

                            Log.d("coord", "location="+latitude+","+longitude+"&radius=2000");

                            OkHttpClient okhttpClient = new OkHttpClient();
                            Request myGetRequest = new Request.Builder()
                                    .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=2000&types=pharmacy&key=AIzaSyD1syvaUahKlwgsvgHZhzHtFwcHHAblNHQ")
                                    .build();


                            okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

// fonction qui effectue le parsing du json récupéré via google
                                @Override
                                public void onResponse(Response response) throws IOException {

                                    try {
                                        text = response.body().string();
                                        JSONObject json = new JSONObject(text);
                                        //Log.d("test", json.toString());
                                        JSONArray jArray = json.getJSONArray("results");
                                        //Log.d("test2", jArray.toString());


                                        for (int i = 0; i < jArray.length(); i++) {
                                            String info = jArray.getJSONObject(i).getString("name") +", adresse: " + jArray.getJSONObject(i).getString("vicinity");
                                            list.add(info);
                                            Log.d("pharmacies_proches", info);

                                        }


                                    } catch (JSONException exc) {

                                        exc.printStackTrace();
                                    }


                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //Log.i("TEST", text);
                                           //tv3.setText(list.toString());
                                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Geoloc.this,
                                                    android.R.layout.simple_list_item_1, list);
                                            resultats.setAdapter(adapter);

                                            resultats.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                                public void onItemClick(AdapterView<?> arg0,View arg1, int position, long id){


                                                    Intent n = new Intent(getApplicationContext(), MaPharma.class);
                                                    n.putExtra("position", position);
                                                    Log.i("Envoi",""+ resultats.getItemAtPosition(position).toString());
                                                    try {
                                                        // Uri uri = Uri.parse("geo:"+longitude+","+latitude);
                                                        Uri uri = Uri.parse("geo:0,0?q="+resultats.getItemAtPosition(position).toString());
                                                        Intent it = new Intent(Intent.ACTION_VIEW,uri);
                                                        startActivity(it);
                                                    } catch(ActivityNotFoundException e) {
                                                        (Toast.makeText(getApplicationContext(), "GoogleMap non trouvé", Toast.LENGTH_LONG)).show();
                                                    }
                                                    //test
                                                   // startActivity(n);
                                                }
                                            });









                                        }
                                    }); // fin runOnUiThread
                                } // fin onResponse
                            });//fin Callback







                        } else {
                            Log.d("ERREUR", "erreur de permission");
                        }
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d("ERREUR", "erreur de permission");
                }
                return ;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }







}
