package com.example.danny.geometryhelper.Geometry;

import android.content.SharedPreferences;
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

public class RectPrism_new extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityCompat.postponeEnterTransition(this);
        setContentView(R.layout.rect_prism_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cyl_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.cyl_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.cyl_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(((new PlaceholderFragment()).newInstance(0)),"Volume");
        adapter.addFrag(((new PlaceholderFragment()).newInstance(1)),"Surface Area");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                adapter.getItem(position).setTransitionNameLollipop();
                if(position>0)
                    adapter.getItem(position-1).setTransitionNull();
                else
                    adapter.getItem(position+1).setTransitionNull();

            }

            @Override
            public void onPageSelected(int position) {
                adapter.getItem(position).setTransitionNameLollipop();
                if(position>0)
                    adapter.getItem(position-1).setTransitionNull();
                else
                    adapter.getItem(position+1).setTransitionNull();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);
    }

    public static class PlaceholderFragment extends ViewPagerFragment{
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

            View rootView;

            if(x == 0){
                rootView = inflater.inflate(R.layout.fragment_rect_prism_vol, container, false);
                assignImageView(rootView,R.id.rect_img_vol);
            }
            else {
                rootView = inflater.inflate(R.layout.fragment_rect_prism,container,false);
                assignImageView(rootView,R.id.rect_img,true);
            }

            final EditText wTxt = (EditText)rootView.findViewById(R.id.rect_width);
            final EditText hTxt = (EditText)rootView.findViewById(R.id.rect_height);
            final EditText lTxt = (EditText)rootView.findViewById(R.id.rect_length);
            final TextView vTxt = (TextView)rootView.findViewById(R.id.rect_vol_num);

            TextView vTitle = (TextView)rootView.findViewById(R.id.rect_vol_title);

            wTxt.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    calc(wTxt,hTxt,lTxt,vTxt,x);
                    return false;
                }
            });

            hTxt.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    calc(wTxt,hTxt,lTxt,vTxt,x);
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

        public void calc(EditText wTxt,EditText hTxt, EditText lTxt, TextView vTxt, int sectionNum){

            String sWidth;
            String sLength;
            String sHeight;

            double length;
            double width;
            double height;
            if(sectionNum == 0) {

                double area;

                sWidth = wTxt.getText().toString();
                sLength = lTxt.getText().toString();
                sHeight = hTxt.getText().toString();

                if(Tools.stringCheck(sHeight) && Tools.stringCheck(sLength) && Tools.stringCheck(sWidth)){

                    length = Double.parseDouble(sLength);
                    height = Double.parseDouble(sHeight);
                    width = Double.parseDouble(sWidth);

                    area = (length*width*height);

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String numDec = pref.getString("example_list","4");

                    int tmp = (int) area;

                    Log.d("shit", String.valueOf(tmp==area));

                    String temp = tmp==area?String.valueOf(tmp)+" un³":String.format("%."+ numDec+"f",area) + " un³";

                    vTxt.setText(temp);


                }

            }

            if(sectionNum == 1) {

                double area;

                sWidth = wTxt.getText().toString();
                sLength = lTxt.getText().toString();
                sHeight = hTxt.getText().toString();

                if(Tools.stringCheck(sWidth) && Tools.stringCheck(sLength) && Tools.stringCheck(sHeight)){

                    length = Double.parseDouble(sLength);
                    height = Double.parseDouble(sHeight);
                    width = Double.parseDouble(sWidth);

                    area = (2*length*width) + (2*length*height) + (2*width*height);

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String numDec = pref.getString("example_list","4");

                    int tmp = (int) area;

                    String temp = String.format("%."+ numDec+"f",tmp==area?tmp:area) + " un²";

                    vTxt.setText(temp);


                }

            }
        }
    }
}

