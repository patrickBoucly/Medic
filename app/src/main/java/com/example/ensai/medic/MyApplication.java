package com.example.ensai.medic;

import android.app.Application;
//import android.support.multidex.MultiDex;

/**
 * Created by ensai on 18/05/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //MultiDex.install(this);
    }
}
