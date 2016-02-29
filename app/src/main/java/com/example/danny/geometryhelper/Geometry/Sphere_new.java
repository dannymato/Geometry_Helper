package com.example.danny.geometryhelper.Geometry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.UI.ViewPagerAdapter;
import com.example.danny.geometryhelper.UI.ViewPagerFragment;

public class Sphere_new extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityCompat.postponeEnterTransition(this);
        setContentView(R.layout.sphere_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sphere_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.sphere_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sphere_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(((new PlaceholderFragment()).newInstance(0)),"Volume");
        adapter.addFrag(((new PlaceholderFragment()).newInstance(1)),"Surface Area");
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

      //  private static String _transitionName = "geoTransition3";
       // private ImageView img;

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
            assignImageView(rootView,R.id.imageView);

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

     /*   public void onViewCreated(View view, Bundle savedInstanceState){

            super.onViewCreated(view, savedInstanceState);

            if(_transitionName != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setTransitionNameLollipop();
                ActivityCompat.startPostponedEnterTransition(getActivity());
            }



        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private void setTransitionNameLollipop(){
            img.setTransitionName(_transitionName);
        }*/

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

