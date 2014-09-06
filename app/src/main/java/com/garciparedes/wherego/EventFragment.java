package com.garciparedes.wherego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by garciparedes on 06/09/14.
 */
public class EventFragment extends Fragment {


    private TextView tvName;
    private TextView tvType;
    private TextView tvDescription;
    private TextView tvDate;
    private Event event;

    public static EventFragment newInstance(Event event1) {
        EventFragment f = new EventFragment();
        f.setSomeObject(event1);

        return f;
    }

    public void setSomeObject(Event Event1) {
        event = Event1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_event, container, false);

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        setName(event);
        setType(event);
        setDescription(event);
        setDate(event);
    }

    private void setName(Event event){
        tvName = (TextView) getView().findViewById(R.id.tv_event_name);

        tvName.setText(event.getName());
    }

    private void setType(Event event){
        tvType = (TextView) getView().findViewById(R.id.tv_event_type);

        tvType.setText(event.getType());
    }

    private void setDescription(Event event){
        tvDescription = (TextView) getView().findViewById(R.id.tv_event_description);

        tvDescription.setText(event.getDescription());
    }

    private void setDate(Event event){
        tvDate = (TextView) getView().findViewById(R.id.tv_event_date);

        tvDate.setText(event.getDate().getDay() + "/" +
                event.getDate().getMonth() + "/" +
                event.getDate().getYear());
    }
}
