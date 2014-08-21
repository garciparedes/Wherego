package com.garciparedes.wherego;

/**
 * Created by garciparedes on 20/07/14.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListEventFragment extends Fragment {

    //Widget Lista
    private ListView eventList;


    //Variable que recoge el nombre de los eventos

    //String[] events = getArguments().getStringArray(getString(R.array.nav_options));
    String[] events = {"Fiestas de santander", "Fiestas de Paredes de Nava", "Fiestas de Palencia", "Concierto de Nach", "Concierto de Cheb Ruben", "Fiestas de Villalumbroso", "Navidades", "Nochebuena", "Dia de Carejas", "Pascua", "Fiestas de Valladolid", "Fiestas de Fuentes de Nava,", "Fiestas de Carrión de los Condes", "Fiestas de Saldaña" };


    public static ListEventFragment newInstance(int index) {
        ListEventFragment f = new ListEventFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list_event, container, false);

    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        eventList = (ListView)getView().findViewById(R.id.list_event);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, events);
        eventList.setAdapter(adapter);
        //eventList.setAdapter(new CustomArrayAdapter(this));

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {



                //**********************************************************
                //*                 UNCOMPLETE METHOD                      *
                //**********************************************************



                Toast.makeText(getActivity().getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();


            }
        });

    }

    class CustomArrayAdapter extends ArrayAdapter<String> {

        Activity context;

        CustomArrayAdapter(Fragment context) {
            super(context.getActivity(), R.layout.listitem_events, events);
            this.context = context.getActivity();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_events, null);

            TextView lblDe = (TextView)item.findViewById(R.id.LblName);
            lblDe.setText(events[position]);


            return(item);
        }
    }



}
