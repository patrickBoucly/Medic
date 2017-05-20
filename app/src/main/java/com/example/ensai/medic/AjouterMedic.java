package com.example.ensai.medic;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ContentProvider;
import android.content.Intent;
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

import static com.example.ensai.medic.ContextProvider.sContext;
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
    private ArrayList<Medic> medics= new ArrayList<Medic>();
    private MedicDAO medicDAO;

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
        medicDAO= new MedicDAO(this);
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bouton_ajouter:
                //Création d'une instance de ma classe MedicBDD
              //  MedicDAO medicDAO = new MedicDAO(this);
/*
                //Création du medic
                Medic medic = new Medic(1,1,"test");

                //On ouvre la base de données pour écrire dedans
                medicBDD.open();
                //On insère le medic que l'on vient de créer s'il est absent de la bdd

                if(!(medicBDD.getBDD()).get.getCodeCIS()) {
                    medicBDD.insertMedic(medic);
                }
                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                Medic livreFromBdd = livreBdd.getLivreWithTitre(livre.getTitre());
                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if(livreFromBdd != null){
                    //On affiche les infos du livre dans un Toast
                    Toast.makeText(this, livreFromBdd.toString(), Toast.LENGTH_LONG).show();
                    //On modifie le titre du livre
                    livreFromBdd.setTitre("J'ai modifié le titre du livre");
                    //Puis on met à jour la BDD
                    livreBdd.updateLivre(livreFromBdd.getId(), livreFromBdd);
                }

                //On extrait le livre de la BDD grâce au nouveau titre
                livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifié le titre du livre");
                //S'il existe un livre possédant ce titre dans la BDD
                if(livreFromBdd != null){
                    //On affiche les nouvelles informations du livre pour vérifier que le titre du livre a bien été mis à jour
                    Toast.makeText(this, livreFromBdd.toString(), Toast.LENGTH_LONG).show();
                    //on supprime le livre de la BDD grâce à son ID
                    livreBdd.removeLivreWithID(livreFromBdd.getId());
                }

                //On essaye d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
                livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifié le titre du livre");
                //Si aucun livre n'est retourné
                if(livreFromBdd == null){
                    //On affiche un message indiquant que le livre n'existe pas dans la BDD
                    Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
                }
                //Si le livre existe (mais normalement il ne devrait pas)
                else{
                    //on affiche un message indiquant que le livre existe dans la BDD
                    Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
                }

                livreBdd.close();
        }
                Toast.makeText(this, "Ajout d'un medicament dans la pharmacie personnelle", Toast.LENGTH_LONG).show();
*/
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
                                String code = ""+jsonobject.getInt("codeCIS");
                                /*Log.d("test1", denom);
                                Log.d("test2", code);*/
                                medics.add(new Medic(1,code,denom));
                            }



                        } catch (JSONException exc) {

                            exc.printStackTrace();
                        }








                       runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<String> medic= new ArrayList<String>();
                                for(Medic med: medics){
                                    medic.add(med.getName());
                                }
                                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AjouterMedic.this,
                                        android.R.layout.simple_list_item_1, medic);
                                resultats.setAdapter(adapter);

                                resultats.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                    public void onItemClick(AdapterView<?> arg0,View arg1, int position, long id){
                                        /*

                                        Intent n = new Intent(getApplicationContext(), MaPharma.class);
                                        n.putExtra("position", position);
                                        Log.i("Envoi",""+id+"      "+ resultats.getItemAtPosition(position).toString());
                                        try {

                                        */
                                            Boolean res=(ContextProvider.getContext()==null);
                                            String str="false";
                                            if(res){
                                                str="true";
                                            }
                                            Log.i("cont",str);

                                            medicDAO.open();
                                            String name=medics.get(position).getName();
                                            String cis=medics.get(position).getCodeCIS().toString();
                                        Log.i("nb_av",""+medicDAO.getAllMedics().size());
                                            medicDAO.add(name,cis);
                                        Log.i("nb_ap",""+medicDAO.getAllMedics().size());


                                       /* } catch(ActivityNotFoundException e) {
                                            (Toast.makeText(getApplicationContext(), "Médicament non ajouté", Toast.LENGTH_LONG)).show();
                                          }*/
                                        //test
                                        // startActivity(n);
                                    }
                                });




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