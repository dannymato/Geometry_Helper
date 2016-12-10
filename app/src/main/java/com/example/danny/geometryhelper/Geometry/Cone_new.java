package com.example.danny.geometryhelper.Geometry;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.Tools;
import com.example.danny.geometryhelper.UI.ViewPagerAdapter;
import com.example.danny.geometryhelper.UI.ViewPagerFragment;

public class Cone_new extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityCompat.postponeEnterTransition(this);
        setContentView(R.layout.activity_cone_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cone_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.cone_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.cone_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(((new PlaceholderFragment()).newInstance(0)),"Volume");
        adapter.addFrag(((new PlaceholderFragment()).newInstance(1)),"Surface Area");

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    adapter.getItem(position).setTransitionNameLollipop();
                    if (position > 0)
                        adapter.getItem(position - 1).setTransitionNull();
                    else
                        adapter.getItem(position + 1).setTransitionNull();
                }

            }

            @Override
            public void onPageSelected(int position) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    adapter.getItem(position).setTransitionNameLollipop();
                    if (position > 0)
                        adapter.getItem(position - 1).setTransitionNull();
                    else
                        adapter.getItem(position + 1).setTransitionNull();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);

    }

    public static class PlaceholderFragment extends ViewPagerFragment {
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

            Bundle args = getArguments();
            final int x = args.getInt(ARG_SECTION_NUMBER);

            View rootView = inflater.inflate(x==0 ? R.layout.fragment_cyl_vol : R.layout.fragment_cyl, container, false);

            if(x==0)
                assignImageView(rootView,R.id.cyl_img_vol);
            else
                assignImageView(rootView,R.id.cyl_img,false);

            final EditText rTxt = (EditText)rootView.findViewById(R.id.cyl_length);
            final EditText hTxt = (EditText)rootView.findViewById(R.id.cyl_height);
            final TextView vTxt = (TextView)rootView.findViewById(R.id.cyl_vol_num);

            TextView vTitle = (TextView)rootView.findViewById(R.id.cyl_vol_title);

            rTxt.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    calc(rTxt,hTxt,vTxt,x);
                    return false;
                }
            });

            hTxt.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    calc(rTxt,hTxt,vTxt,x);
                    return false;
                }
            });

            Log.d("Page#" , String.valueOf(x));

            if(x == 0){
                vTitle.setText("Volume = ");

            }

            else if(x == 1){
                vTitle.setText("Surface Area = ");
            }


            return rootView;
        }

        public void calc(EditText rTxt,EditText hTxt, TextView vTxt, int sectionNum) {

            String sRad;
            String sHeight;
            double rad;
            double height;

            if (sectionNum == 0) {

                double vol;

                sRad = rTxt.getText().toString();
                sHeight = hTxt.getText().toString();

                if (Tools.stringCheck(sRad) && Tools.stringCheck(sHeight)) {

                    rad = Double.valueOf(sRad);
                    height = Double.valueOf(sHeight);

                    vol = Math.pow(rad, 2) * Math.PI * (height / 3.0);

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String numDec = sharedPreferences.getString("example_list", "4");
                    System.out.println(numDec);

                    int temp = (int) vol;

                    vTxt.setText(String.format("%." + numDec + "f", temp == vol ? temp:vol));

                }

            } else if (sectionNum == 1) {

                sRad = rTxt.getText().toString();
                sHeight = hTxt.getText().toString();

                double area;

                if (Tools.stringCheck(sRad) && Tools.stringCheck(sHeight)) {

                    rad = Double.parseDouble(sRad);
                    height = Double.parseDouble(sHeight);

                    area = Math.PI*rad*(rad + Math.sqrt(Math.pow(height,2) + Math.pow(rad,2)));

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String numDec = pref.getString("example_list", "4");

                    int temp = (int) area;

                    vTxt.setText(String.format("%." + numDec + "f", temp == area ? temp:area));

                }

            }
        }
    }
}

