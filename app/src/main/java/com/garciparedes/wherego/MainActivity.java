package com.garciparedes.wherego;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {


    private DrawerLayout drawerLayout;
    private ListView navList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle drawerToggle;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = getTitle(); // Get current title


        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navList = (ListView) findViewById(R.id.leftdrawer);

        // Load an array of options names
        final String[] names = getResources().getStringArray(
                R.array.nav_options);

        // Set previous array as adapter of the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new DrawerItemClickListener());



        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_drawer, R.string.open_drawer,
                R.string.close_drawer) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // creates call to onPrepareOptionsMenu()
                supportInvalidateOptionsMenu();
            }


            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.app_name);
                // creates call to onPrepareOptionsMenu()
                supportInvalidateOptionsMenu();
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Fragment fragmentList = TabbedFragment.newInstance(0);
        mTitle = getResources().getStringArray(R.array.nav_options)[0];
        getActionBar().setTitle(mTitle);


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManagerList = getSupportFragmentManager();
        fragmentManagerList.beginTransaction()
                .replace(R.id.content_frame, fragmentList).commit();

    }




    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
        
    }




    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Get text from resources
        mTitle = getResources().getStringArray(R.array.nav_options)[position];



        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragmentList = (TabbedFragment.newInstance(1));
        Fragment fragmentSaved = (TabbedFragment.newInstance(2));
        Fragment fragmentPopular = (TabbedFragment.newInstance(3));
        Fragment fragmentSettings = (SettingsFragment.newInstance());



        // Create a new fragment and specify the option to show based on
        // position
        switch (position){
            
            case 0:

                // Insert the fragment by replacing any existing fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragmentList).commit();
                break;

            case 1:

                // Insert the fragment by replacing any existing fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragmentSaved).commit();
                break;

            case 2:

                // Insert the fragment by replacing any existing fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragmentPopular).commit();
                break;

            case 3:

                // Insert the fragment by replacing any existing fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragmentSettings).commit();
                break;

        }

        // Highlight the selected item, update the title, and close the drawer
        navList.setItemChecked(position, true);
        getActionBar().setTitle(mTitle);
        drawerLayout.closeDrawer(navList);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Called by the system when the device configuration changes while your
        // activity is running
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /*
     * Called whenever we call invalidateOptionsMenu()
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content
        // view
        boolean drawerOpen = drawerLayout.isDrawerOpen(navList);
        menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        menu.findItem(R.id.action_refresh).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


    /*

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        //http://stackoverflow.com/a/6413736/3921457
        new AlertDialog.Builder(this)
                //.setTitle( R.string.exit_dialog_title)
                .setMessage(R.string.exit_dialog_message)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();

                    }
                }).create().show();
    }


}
