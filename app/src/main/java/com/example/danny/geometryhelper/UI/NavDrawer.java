package com.example.danny.geometryhelper.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.LruCache;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.example.danny.geometryhelper.R;
import com.mopub.mobileads.MoPubView;


public class NavDrawer extends AppCompatActivity {

    private String[] mMathTypes;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Context mContext;
    private Toolbar toolbar;

    private CharSequence mDrawerTitle;

    private Handler mDrawerActionHandler = new Handler();

    private static final long DRAWER_CLOSE_DELAY_MS = 250;

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

    private final int[] navMenuItems = {
        R.id.geo_menu,
        R.id.alg_menu
    };

    public static int numImgs = 0;
    public static int numCards = 0;

    private CharSequence mTitle;

    private MoPubView moPubView;


    private static LruCache<String,Bitmap> bitmapCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);

        mContext = this;


        setContentView(R.layout.activity_nav_drawer);

        toolbar = (Toolbar)findViewById(R.id.navDrawerToolbar);
        Log.d("Toolbar",toolbar.toString());

        setSupportActionBar(toolbar);
        mTitle = mDrawerTitle = getTitle();
        setTitle(mTitle);
        mMathTypes = getResources().getStringArray(R.array.math_types);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){

            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

        };

        NavigationView mNavigationMenu = (NavigationView) findViewById(R.id.navigation);
        Log.d("NavMenu", mNavigationMenu.toString());

        mNavigationMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                Log.d("NavMenu", "Selected");

                mDrawerLayout.closeDrawer(GravityCompat.START);
                mDrawerActionHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigate(item);
                    }
                },DRAWER_CLOSE_DELAY_MS);


                return true;
            }
        });

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory/8;

        bitmapCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
        Log.d("Cache",bitmapCache.toString());

       Log.d("Cache Before Fragment",cacheCheck());

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
            Log.d("Cache After Homescreen",cacheCheck());
            Bundle bundle = new Bundle();
            bundle.putInt("tabSelected", pos-1);
            bundle.putIntArray("imageArray", mCardImgs[pos-1]);
            bundle.putStringArray("textArray", mCardTexts[pos-1]);
            homeScreen.setArguments(bundle);
            manager.beginTransaction()
                    .add(R.id.content_frame, homeScreen)
                    .commit();
            //setTitle(mMathTypes[pos-1]);
            mNavigationMenu.setCheckedItem(navMenuItems[pos-1]);
        }



        Log.d("Cache After Fragment",cacheCheck());
        //Load Ad
            moPubView = (MoPubView) findViewById(R.id.adView);
            moPubView.setAdUnitId("99d5e065e0274e66a2406194a47f74f2");
            moPubView.loadAd();

    }

    private void navigate(MenuItem item){
        HomeScreen homeScreen;
        FragmentManager manager;
        Bundle bundle;
        switch (item.getItemId()){
            //Switch to Geometry Grid
            case R.id.geo_menu:
                homeScreen = new HomeScreen();
                manager = getSupportFragmentManager();

                bundle = new Bundle();
                bundle.putInt("tabSelected", 0);
                bundle.putIntArray("imageArray", mCardImgs[0]);
                bundle.putStringArray("textArray", mCardTexts[0]);
                homeScreen.setArguments(bundle);
                manager.beginTransaction()
                        .replace(R.id.content_frame, homeScreen).addToBackStack(null)
                        .commit();
                setTitle(mMathTypes[0]);
                //        mDrawerLayout.closeDrawer(mDrawerList);
                break;
            //Switch to Algebra II grid
            case R.id.alg_menu:
                homeScreen = new HomeScreen();
                manager = getSupportFragmentManager();

                bundle = new Bundle();
                bundle.putInt("tabSelected", 1);
                bundle.putIntArray("imageArray", mCardImgs[1]);
                bundle.putStringArray("textArray", mCardTexts[1]);
                homeScreen.setArguments(bundle);
                manager.beginTransaction()
                        .replace(R.id.content_frame, homeScreen).addToBackStack(null)
                        .commit();

                //  mDrawerList.setItemChecked(1, true);
                setTitle(mMathTypes[1]);
//                        mDrawerLayout.closeDrawer(mDrawerList);
                break;
            //Open Settings
            case R.id.setting_menu:
                setTitle("Settings");
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame, new SettingsActivity.GeneralPreferenceFragment())
                        .addToBackStack(null)
                        .commit();

                break;
            default:
                break;
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
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

    public Bitmap getBitmapfromCache(String key){
        return bitmapCache.get(key);
    }

    public void addBitmaptoCache(String key, Bitmap bitmap) {
        if (getBitmapfromCache(key) == null)
            bitmapCache.put(key, bitmap);
    }

    public String cacheCheck(){
        return bitmapCache.toString();
    }

    protected void onDestroy(){
        moPubView.destroy();
        super.onDestroy();
        bitmapCache.evictAll();
    }


}