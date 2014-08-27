package com.garciparedes.wherego;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viewpagerindicator.TabPageIndicator;


/**
 * Created by garciparedes on 27/07/14.
 */


public class TabbedFragment extends Fragment {

    /**
     * The pager widget, which handles animation and allows swiping horizontally
     * to access previous and next pages.
     */
    private ViewPager pager = null;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */


    private FragmentActivity myContext;
    ActionBarActivity activity = ((ActionBarActivity)getActivity());

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }


    public static TabbedFragment newInstance(int index) {

        TabbedFragment f = new TabbedFragment();



        return f;

    }




    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_tabbed_events, container, false);
        pager = (ViewPager) result.findViewById(R.id.pager);


        //Limita el numero de tabs cargadas en memoria
        pager.setOffscreenPageLimit(3);

        //Seleciona un adapter
        pager.setAdapter(buildAdapter());
        TabPageIndicator titleIndicator = (TabPageIndicator) result.findViewById(R.id.indicator);




        return (result);
    }


    private PagerAdapter buildAdapter() {
        return(new TabAdapter(getActivity(), getChildFragmentManager()));
    }




    public void onDestroyView() {
        super.onDestroyView();
        Fragment f = (Fragment) getFragmentManager().findFragmentById(R.id.map);
        if (f != null) {
            getFragmentManager().beginTransaction().remove(f).commit();

        }

    }


}