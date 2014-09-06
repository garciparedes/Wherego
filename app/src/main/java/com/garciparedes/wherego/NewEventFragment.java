package com.garciparedes.wherego;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

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
    private EditText editTextCoordinate;

    private Button btnConfirm;


    private String newName;
    private String newDescription;
    private String newType;
    private Date newDate;
    private Coordinate newCoordinate;
    private Event newEvent;


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
        setCoordinate();
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

    private void setCoordinate(){
        editTextCoordinate = (EditText) getView().findViewById(R.id.edit_text_set_coordinate);

        editTextCoordinate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

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
                        !editTextDescription.getText().toString().isEmpty()
                        //!editTextDate.getText().toString().isEmpty() &&
                        //!editTextCoordinate.getText().toString().isEmpty()
                        ){
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Button clicked", Toast.LENGTH_LONG)
                            .show();

                    newName = editTextName.getText().toString();
                    newType = editTextType.getText().toString();
                    newDescription = editTextDescription.getText().toString();
                    newDate = new Date(dateMillis);
                    newCoordinate = new Coordinate("Palencia",34,-4);

                    newEvent = new Event(newName, newType, newDescription, newDate, newCoordinate);
                    CallAPI.addEvent(newEvent);

                }

            }

        });

    }

}
