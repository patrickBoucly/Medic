package com.example.ensai.medic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ensai on 12/05/17.
 */

public class MonAdapter extends BaseAdapter {
    private List<String> listeDesElements;
    private Context context;

    public MonAdapter(Context contexte,List<String> listeElements) {
        this.listeDesElements= listeElements;
        this.context=contexte;
    }




    @Override
    public int getCount() {
        return listeDesElements.size();
    }

    @Override
    public Object getItem(int position) {
        return listeDesElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_element, null);
        }
        else{
            v=convertView;

        }
        String element = (String) getItem(position);
        TextView nom= (TextView) v.findViewById(R.id.zone1);
        TextView description= (TextView) v.findViewById(R.id.zone2);
        nom.setText(element.toString());

        return v;
    }
}
