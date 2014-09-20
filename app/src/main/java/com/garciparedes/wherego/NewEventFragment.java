package com.garciparedes.wherego;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by garciparedes on 03/09/14.
 */
public class NewEventFragment extends Fragment {

    private EditText editTextName;
    private EditText editTextType;
    private EditText editTextDescription;
    private EditText editTextDate;
    private EditText editTextHour;
    private AutoCompleteTextView editTextCoordinate;

    private Button btnConfirm;

    private String newName;
    private String newDescription;
    private String newType;
    private Date newDate;
    private LatLng newCoordinate;
    private Event newEvent;

    private String referencePlace = "";

    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;
    private long dateMillis;


    public static NewEventFragment newInstance() {
        NewEventFragment f = new NewEventFragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new_event, container, false);

    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        setName();
        setType();
        setDescription();
        setTime();
        setPlace();
        setConfirm();

    }

    private void setName(){
        editTextName = (EditText) getView().findViewById(R.id.edit_text_set_name);
        editTextName.setHint(getString(R.string.name));

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


            }
        });

    }

    private void setType(){
        editTextType = (EditText) getView().findViewById(R.id.edit_text_set_type);

        editTextType.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


            }
        });
    }

    private void setDescription(){
        editTextDescription = (EditText) getView().findViewById(R.id.edit_text_set_description);
        editTextDescription.setHint(getString(R.string.description));


        editTextDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }

    private void setTime(){

        editTextDate = (EditText) getView().findViewById(R.id.edit_text_set_date);
        editTextDate.setHint("dd/mm/yyyy");

        //txtHour = (EditText) getView().findViewById(R.id.edit_text_set_hour);


        editTextDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Process to get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                editTextDate.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                                dateMillis = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0).getMillis();
                            }
                        }, mYear, mMonth, mDay);
                if (hasFocus == true) {
                    dpd.show();

                }else{
                    dpd.dismiss();
                }



            }
        });


    }

    private void setPlace(){
        editTextCoordinate = (AutoCompleteTextView) getView().findViewById(R.id.edit_text_set_coordinate);

        final PlacesAutoCompleteAdapter adapter = new PlacesAutoCompleteAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line);
        editTextCoordinate.setAdapter(adapter);

        editTextCoordinate.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                if (adapter.desRefList.size() >0){
                    referencePlace = adapter.desRefList.get(0).second.toString();
                    System.out.println("Coordenadas");

                }
            }
        });

        editTextCoordinate.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Toast.makeText(getActivity().getBaseContext(), "Hola",
                            Toast.LENGTH_SHORT
                    ).show();

                    referencePlace = adapter.desRefList.get(position).second.toString();
                }

            });

    }

    private void setConfirm() {

        btnConfirm = (Button) getView().findViewById(R.id.btn_new_event_confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!editTextName.getText().toString().isEmpty() &&
                        !editTextType.getText().toString().isEmpty() &&
                        !editTextDescription.getText().toString().isEmpty()&&
                        !editTextDate.getText().toString().isEmpty() &&
                        !referencePlace.isEmpty()
                        ) {

                    PlacesGetLatLngFromReference getLatLngFromReference = new PlacesGetLatLngFromReference();


                    getLatLngFromReference.execute(referencePlace);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                    // Insert the fragment by replacing any existing fragment
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, TabbedFragment.newInstance(0)).commit();
                }

            }

        });

    }

    public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        ArrayList<Pair> desRefList = new ArrayList<Pair>();

        public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {



                        // Retrieve the autocomplete results.
                        resultList = new ArrayList<String>();

                        desRefList = GooglePlaces.getDesRef(constraint.toString());

                        for ( int i = 0; i < desRefList.size();i++){
                            resultList.add(i, desRefList.get(i).first.toString());
                            System.out.println(desRefList.get(i).first.toString());
                        }


                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();

                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    }
                    else {
                        notifyDataSetInvalidated();
                    }
                }};
            return filter;
        }
    }

    private class PlacesGetLatLngFromReference extends AsyncTask<String, Void, LatLng>{

        @Override
        protected LatLng doInBackground(String... reference){

            return GooglePlaces.getLatLngFromReference(reference[0]);

        }

        @Override

        protected void onPostExecute(LatLng latLng) {

            newName = editTextName.getText().toString();
            newType = editTextType.getText().toString();
            newDescription = editTextDescription.getText().toString();
            newDate = new Date(dateMillis);
            newCoordinate = latLng;
            System.out.println("Hola");
            newEvent = new Event(newName, newType, newDescription, newDate, newCoordinate);
            CallAPI.addEvent(newEvent);
        }
    }

}
