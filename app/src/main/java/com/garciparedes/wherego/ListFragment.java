package com.garciparedes.wherego;

/**
 * Created by garciparedes on 20/07/14.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListFragment extends Fragment {

    //Widget Lista
    private ListView eventList;

    ArrayList <Event> datos = CallAPI.getEventList();


    public static ListFragment newInstance(int index) {
        ListFragment f = new ListFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);

    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        eventList = (ListView)getView().findViewById(R.id.list_event);

        eventList.setAdapter(new CustomArrayAdapter(this, datos));

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //**********************************************************
                //*                 UNCOMPLETE METHOD                      *
                //**********************************************************


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
