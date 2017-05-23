package com.example.ensai.medic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ensai on 23/05/17.
 */

public class SelectedMedic extends Activity {
    TextView tv;
    Button sup;
    Button info;

    protected void onCreate(Bundle savedInstanceState,String monMedic) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedmedic);
        tv=(TextView) findViewById(R.id.selectedmedic);
        tv.setText(monMedic);
        sup=(Button) findViewById(R.id.supprimer);
        info=(Button) findViewById(R.id.info);













    }
}
