package com.example.ensai.medic;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ensai on 18/05/17.
 */

 class MyLocationListener implements LocationListener

{

    @Override

    public void onLocationChanged(Location loc) {

        if (loc != null) {

            Log.d("GEOLOC","Localisation actuelle :n Lat: " + loc.getLatitude() +  "  Lng: " + loc.getLongitude());



        }

    }

    @Override

    public void onProviderDisabled(String provider) {

// TODO Auto-generated method stub

    }

    @Override

    public void onProviderEnabled(String provider) {

// TODO Auto-generated method stub

    }

    @Override

    public void onStatusChanged(String provider, int status,

                                Bundle extras) {

// TODO Auto-generated method stub

    }
}
