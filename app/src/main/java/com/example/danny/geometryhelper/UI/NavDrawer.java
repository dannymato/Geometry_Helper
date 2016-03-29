package com.example.danny.geometryhelper.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.SettingsActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class NavDrawer extends AppCompatActivity {

    private String[] mMathTypes;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private Context mContext;

    private CharSequence mDrawerTitle;


    private final int[] mGeoCardImgs ={
            R.drawable.tri_img,
            R.drawable.circle_img,
            R.drawable.quad_img,
            R.drawable.sphere_img,
            R.drawable.circle_img,//TODO: Draw cylinder
            R.drawable.tri_img,//TODO: Draw Trapezoid
            R.drawable.quad_img,
            R.drawable.circle_img
    };

    private final int[] mAlgCardImgs = {
            R.drawable.tri_img,//TODO: Draw a symbol for Descartes
            R.drawable.circle_img, //TODO: Draw a symbol for distance formula
            R.drawable.pythag_img
    };

    private final int[][] mCardImgs = {
            mGeoCardImgs,
            mAlgCardImgs
    };

    private final String[] mGeoCardTexts ={
            "Triangles",
            "Circles",
            "Quadratic Formula",
            "Spheres",
            "Cylinders",
            "Trapezoid",
            "Rectangular Prism",
            "Cone"
    };
    private final String[] mAlgCardTexts = {
            "Descartes",
            "Distance Formula",
            "Pythagorean Thᵐ"
    };

    private final String[][] mCardTexts = {
            mGeoCardTexts,
            mAlgCardTexts
    };

    public static int numImgs = 0;
    public static int numCards = 0;

    private CharSequence mTitle;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(R.layout.activity_nav_drawer);
        mTitle = mDrawerTitle = getTitle();

        mMathTypes = getResources().getStringArray(R.array.math_types);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){

            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList = (ListView)findViewById(R.id.left_drawer);



        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.listview_layout, mMathTypes));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position == 2) {
                    Intent intent = new Intent(mContext, SettingsActivity.class);
                    mContext.startActivity(intent);
                } else {
                    HomeScreen homeScreen = new HomeScreen();
                    FragmentManager manager = getSupportFragmentManager();

                    Bundle bundle = new Bundle();
                    bundle.putInt("tabSelected", position);
                    bundle.putIntArray("imageArray", mCardImgs[position]);
                    bundle.putStringArray("textArray", mCardTexts[position]);
                    homeScreen.setArguments(bundle);
                    manager.beginTransaction()
                            .replace(R.id.content_frame, homeScreen).addToBackStack(null)
                            .commit();

                    mDrawerList.setItemChecked(position, true);
                    setTitle(mMathTypes[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);
                }
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int pos = Integer.parseInt(sharedPreferences.getString("default_page","0"));

        FragmentManager manager = getSupportFragmentManager();

        if(pos == 0){
            manager.beginTransaction()
                    .add(R.id.content_frame, new BlankFragment())
                    .commit();
        }

        else{

            HomeScreen homeScreen = new HomeScreen();

            Bundle bundle = new Bundle();
            bundle.putInt("tabSelected", pos-1);
            bundle.putIntArray("imageArray", mCardImgs[pos-1]);
            bundle.putStringArray("textArray", mCardTexts[pos-1]);
            homeScreen.setArguments(bundle);
            manager.beginTransaction()
                    .add(R.id.content_frame, homeScreen)
                    .commit();
            setTitle(mMathTypes[pos-1]);

        }



        AdView mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());

        Tracker t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.send(new HitBuilders.ScreenViewBuilder().build());


    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}