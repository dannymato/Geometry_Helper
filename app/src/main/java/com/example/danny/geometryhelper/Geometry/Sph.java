package com.example.danny.geometryhelper.Geometry;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;

public class Sph extends AppCompatActivity implements ActionBar.TabListener {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sph);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "VOLUME";
                case 1:
                    return "SURFACE AREA";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sph, container, false);

            Bundle args = getArguments();
            final int x = args.getInt(ARG_SECTION_NUMBER) - 1;

            final EditText rTxt = (EditText)rootView.findViewById(R.id.vol_editText);
            final TextView vTxt = (TextView)rootView.findViewById(R.id.sph_vol_num);

            TextView vTitle = (TextView)rootView.findViewById(R.id.sph_vol);

            rTxt.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    calc(rTxt,vTxt,x);
                    return false;
                }
            });

            if(x == 0){
                vTitle.setText("Volume = ");

            }

            else if(x == 1){
                vTitle.setText("Surface Area = ");
            }


            return rootView;
        }

        public void calc(EditText rTxt, TextView vTxt, int sectionNum){

            String sRad;

            double rad;


            if(sectionNum == 0) {

                double vol;

                sRad = rTxt.getText().toString();

                if (!sRad.equals("")) {

                    rad = Double.valueOf(sRad);

                    vol = (4* Math.PI * Math.pow(rad, 3))/3;

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String numDec = sharedPreferences.getString("example_list", "4");
                    System.out.println(numDec);

                    vTxt.setText(String.format("%." + numDec + "f", vol));

                }

            }

            else if(sectionNum == 1){

                sRad = rTxt.getText().toString();

                double area;

                if(!sRad.equals("")){

                    rad = Double.parseDouble(sRad);

                    area = 4*Math.PI*Math.pow(rad,2);

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String numDec = pref.getString("example_list","4");

                    vTxt.setText(String.format("%."+ numDec+"f", area));

                }

            }
        }
    }
}