package com.garciparedes.wherego;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * Created by garciparedes on 07/08/14.
 */
public class GMapFragment extends Fragment {


    static final LatLng PALENCIA = new LatLng(42.0088161,-4.5269538);

    // Google Map
    GoogleMap map;
    private static View rootView;
    SupportMapFragment mapView;
    Button lockButton;
    Boolean isLocked = false;


    ArrayList<Event> datos = CallAPI.getEventList();

    /**
     * Static factory method that takes an int parameter,
     * initializes the fragment's arguments, and returns the
     * new fragment to the client.
     */
    public static GMapFragment newInstance(int index) {
        GMapFragment f = new GMapFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }


    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_map, container, false);


        // http://stackoverflow.com/a/17024596/3921457

        SupportMapFragment mapFrag = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);

        map = mapFrag.getMap();
        /*

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        SupportMapFragment fmap = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);

        if (fmap == null) {
            fmap = SupportMapFragment.newInstance();
            ft.add(R.id.map, fmap);
        }

        ft.commit();
        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(PALENCIA, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


        map.getUiSettings().setAllGesturesEnabled(false);
        return result;

    }


    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_map, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        mapView = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        map = mapView.getMap();

        // Gets the MapView from the XML layout and creates it
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        if (map != null) {
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.setMyLocationEnabled(true);
            map.addMarker(new MarkerOptions().position(new LatLng(50.167003, 19.383262)));

            // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
            MapsInitializer.initialize(this.getActivity());

            Marker palencia = map.addMarker(new MarkerOptions()
                            .position(PALENCIA)
                            .title("Palencia")
                            .snippet("Bonita ciudad")
                    //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
            );



            map.getUiSettings().setAllGesturesEnabled(isLocked);

            // Updates the location and zoom of the MapView
            //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(PALENCIA, 10);
            //map.animateCamera(cameraUpdate);

            for (int i = 0; i<datos.size(); i++){
                createMarker(datos.get(i));
            }


        }

        lockButton = (Button) rootView.findViewById(R.id.btn_googlemaps_lock);

        lockButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unlockButton(v);
            }
        });
        return rootView;
    }

    public void unlockButton(View view) {

        if (isLocked == false){
            isLocked = true;
            map.getUiSettings().setAllGesturesEnabled(isLocked);

        }else{
            isLocked = false;
            map.getUiSettings().setAllGesturesEnabled(isLocked);
        }


    }
    public void createMarker(Event event){

        LatLng coordinates = new LatLng(event.getCoordinate().getX(),event.getCoordinate().getY());
        Marker marker = map.addMarker(new MarkerOptions()
                        .position(coordinates)
                        .title(event.getName())
                        .snippet(event.getDescription())
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
        );
    }



}