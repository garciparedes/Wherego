package com.garciparedes.wherego;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by garciparedes on 15/08/14.
 */
public class CalendarEventFragment extends Fragment {


    private ListView eventList;

    String[] events = {"Fiestas de santander", "Fiestas de Paredes de Nava", "Fiestas de Palencia", "Concierto de Nach", "Concierto de Cheb Ruben", "Fiestas de Villalumbroso", "Navidades", "Nochebuena", "Dia de Carejas", "Pascua", "Fiestas de Valladolid", "Fiestas de Fuentes de Nava,", "Fiestas de Carrión de los Condes", "Fiestas de Saldaña" };


    public static CalendarEventFragment newInstance(int index) {
        CalendarEventFragment f = new CalendarEventFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_calendar_event, container, false);

    }




    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        eventList = (ListView)getView().findViewById(R.id.calendarlist);


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


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                // Insert the fragment by replacing any existing fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame,CalendarEventFragment.newInstance(1)).commit();



                Toast.makeText(getActivity().getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();


            }
        });

    }
}