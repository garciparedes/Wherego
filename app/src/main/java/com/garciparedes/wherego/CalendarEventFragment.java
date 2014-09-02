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
import java.util.Calendar;

/**
 * Created by garciparedes on 15/08/14.
 */
public class CalendarEventFragment extends Fragment {


    private ListView eventList;
    private CalendarView calView;

    Calendar cal = Calendar.getInstance();


    ArrayList <Event> datos = CallAPI.getEventList();
    ArrayList <Event> datosDay = new ArrayList<Event>();


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

        configList();

        configCal();


    }


    public void configCal (){

        //**********************************************************
        //*                 UNCOMPLETE METHOD                      *
        //**********************************************************

        calView = (CalendarView) getView().findViewById(R.id.calendar);

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

                month++;

                Toast.makeText(getActivity().getBaseContext(), "Selected Date is\n\n"
                                + dayOfMonth + " : " + month + " : " + year,
                        Toast.LENGTH_SHORT
                ).show();

                datosDay = getListDayEvent(dayOfMonth,month,year);

                updateAdapter(datosDay);
            }
        });
    }


    public void configList (){

        //**********************************************************
        //*                 UNCOMPLETE METHOD                      *
        //**********************************************************

        eventList = (ListView)getView().findViewById(R.id.calendarlist);

        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);

        datosDay = getListDayEvent(dayOfMonth,month,year);

        updateAdapter(datosDay);

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
                    "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                    .show();

            }
        });
    }


    public ArrayList<Event> getListDayEvent(int dayOfMonth, int month, int year){

        ArrayList<Event> datosDay = new ArrayList<Event>();

        for (int i = 0; i< datos.size(); i++){
            if ((datos.get(i).getDate().getYear()+1900) == year &&
                (datos.get(i).getDate().getMonth()+1) == month &&
                datos.get(i).getDate().getDay() == dayOfMonth){
                datosDay.add(datos.get(i));

            }

        }

        return datosDay;
    }


    public void updateAdapter (ArrayList<Event> datos){
        eventList.setAdapter(new CustomArrayAdapter(this, datos));

    }
}