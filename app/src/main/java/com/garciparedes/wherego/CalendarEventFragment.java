package com.garciparedes.wherego;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by garciparedes on 15/08/14.
 */
public class CalendarEventFragment extends Fragment {


    private ListView eventList;


    String[] events = {"Fiestas de santander", "Fiestas de Paredes de Nava"};

    private Event[] datos =
            new Event[]{
                    new Event("Fiestas Becerril", "Bobada", 28,8,2014),
                    new Event("Fiestas Amusco", "Bobada", 29,8,2014),
                    new Event("Fiestas Paredes", "Bobada", 30,8,2014),
                    new Event("Fiestas San cebrian", "Bobada", 27,8,2014),
                    new Event("Fiestas Villatoquite", "Bobada", 24,8,2014),
                    new Event("Fiestas Santander", "Bobada", 1,9,2014),
                    new Event("Fiestas Palencia", "Bobada", 2,9,2014),
                    new Event("Fiestas vega", "Bobada", 3,9,2014),
                    new Event("Fiestas herrera", "Bobada", 44,9,2014),

            };


    private ArrayAdapter<String> adapter;
    private CalendarView cal;


    public static CalendarEventFragment newInstance(int index) {

        CalendarEventFragment f = new CalendarEventFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_calendar, container, false);

    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        configCal();

        configList();

    }


    public void configCal (){

        //**********************************************************
        //*                 UNCOMPLETE METHOD                      *
        //**********************************************************

        cal = (CalendarView) getView().findViewById(R.id.calendar);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

                Toast.makeText(getActivity().getBaseContext(), "Selected Date is\n\n"
                                + dayOfMonth + " : " + month + " : " + year,
                        Toast.LENGTH_LONG
                ).show();

            }
        });
    }


    public void configList (){

        //**********************************************************
        //*                 UNCOMPLETE METHOD                      *
        //**********************************************************

        eventList = (ListView)getView().findViewById(R.id.calendarlist);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, events);

        eventList.setAdapter(adapter);

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            /*
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            // Insert the fragment by replacing any existing fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,CalendarEventFragment.newInstance(1)).commit();
            */

            Toast.makeText(getActivity().getApplicationContext(),
                    "Click ListItem Number " + position, Toast.LENGTH_LONG)
                    .show();

            }
        });
    }
}