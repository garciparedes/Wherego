package com.garciparedes.wherego;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by garciparedes on 28/08/14.
 */

public class CustomArrayAdapter extends ArrayAdapter<Event> {

    private final Activity context;
    private final ArrayList<Event> datos;


    CustomArrayAdapter(Fragment context, ArrayList<Event> datos) {
        super(context.getActivity(), R.layout.listitem_events, datos);
        this.context = context.getActivity();
        this.datos = datos;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_events, null);

        TextView lblName = (TextView)item.findViewById(R.id.LblName);
        lblName.setText(datos.get(position).getName());

        TextView lblDate = (TextView)item.findViewById(R.id.LblDate);

        String date =
                (datos.get(position).getDate().getDay())+ "-" +
                (datos.get(position).getDate().getMonth() + 1) + "-" +
                (datos.get(position).getDate().getYear() + 1900);

        lblDate.setText(date);


        return(item);
    }
}

