package com.garciparedes.wherego;

/**
 * Created by garciparedes on 27/07/14.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    Context ctxt=null;

    public TabAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;
    }

    //Devuelve el numero de tabs
    @Override
    public int getCount() {
        String [] a = ctxt.getResources().getStringArray(R.array.tab_list);
        return(a.length);
    }

    //Selecciona el fragment necesario, segun cada tab
    @Override
    public Fragment getItem(int position) {


        switch (position) {

            case 0:
                return(ListFragment.newInstance(1));

            case 1:
                return(CalendarEventFragment.newInstance(1));

            case 2:
                return(GMapFragment.newInstance(1));

            default:
                return(new ListFragment());

        }
    }


    //Genera el titulo de la tab a partir de un String Array
    @Override
    public String getPageTitle(int position) {

        return ctxt.getResources().getStringArray(R.array.tab_list)[position];


    }


}
