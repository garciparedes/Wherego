package com.garciparedes.wherego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by garciparedes on 07/08/14.
 */
public class GMapFragment extends Fragment {


    static final LatLng PALENCIA = new LatLng(42.0088161,-4.5269538);

    // Google Map
    GoogleMap map;


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

        */

        Marker palencia = map.addMarker(new MarkerOptions()
                        .position(PALENCIA)
                        .title("Palencia")
                        .snippet("Bonita ciudad")
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
        );

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(PALENCIA, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


        map.getUiSettings().setAllGesturesEnabled(false);
        return result;

    }



    public void onDestroyView() {
        super.onDestroyView();
        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);
        if (f != null) {
            getFragmentManager().beginTransaction().remove(f).commit();

        }

    }



}