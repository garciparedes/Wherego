package com.garciparedes.wherego;

/**
 * Created by garciparedes on 27/07/14.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class TabAdapter extends FragmentPagerAdapter {
    Context ctxt=null;

    public TabAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;
    }

    //Devuelve el numero de tabs
    @Override
    public int getCount() {
        return(3);
    }

    //Selecciona el fragment necesario, segun cada tab
    @Override
    public Fragment getItem(int position) {
        //return(EditorFragment.newInstance(position));
        //return(new ListEventFragment());

        switch (position) {

            case 0:
                return(ListEventFragment.newInstance(1));

            case 1:
                return(CalendarEventFragment.newInstance(1));

            case 2:
                return(MapEventsFragment.newInstance(1));

            default:
                return(new ListEventFragment());

        }
    }


    //Genera el titulo de la tab
    @Override
    public String getPageTitle(int position) {

        switch (position) {

            case 0:
                return (ctxt.getString(R.string.events));

            case 1:
                return (ctxt.getString(R.string.calendar));

            case 2:
                return (ctxt.getString(R.string.map));

            default:
                return (ctxt.getString(R.string.app_name));
        }


    }


}
