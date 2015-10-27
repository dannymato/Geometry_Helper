package com.example.danny.geometryhelper.UI;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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


public class NavDrawer extends AppCompatActivity {

    private String[] mMathTypes;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private Context mContext;

    private CharSequence mDrawerTitle;


    private int[] mGeoCardImgs ={
            R.drawable.tri_img,
            R.drawable.circle_img,
            R.drawable.quad_img,
            R.drawable.sphere_img,
            R.drawable.circle_img,//TODO: Draw cylinder
            R.drawable.tri_img //TODO: Draw Trapezoid
    };

    private int[] mAlgCardImgs = {
            R.drawable.tri_img,//TODO: Draw a symbol for Descartes
            R.drawable.circle_img //TODO: Draw a symbol for distance formula
    };

    private int[][] mCardImgs = {
            mGeoCardImgs,
            mAlgCardImgs
    };

    private String[] mGeoCardTexts ={
            "Triangles",
            "Circles",
            "Quadratic Formula",
            "Spheres",
            "Cylinders",
            "Trapezoid"
    };
    private String[] mAlgCardTexts = {
            "Descartes",
            "Distance Formula"
    };

    private String[][] mCardTexts = {
            mGeoCardTexts,
            mAlgCardTexts
    };






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



        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_layout, mMathTypes));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position == 4) {
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

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.content_frame, new BlankFragment())
                .commit();



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
        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}