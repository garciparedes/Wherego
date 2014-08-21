package com.garciparedes.wherego;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by garciparedes on 15/08/14.
 */
public class CalendarEventFragment extends Fragment {

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
}