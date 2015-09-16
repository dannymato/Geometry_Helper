package com.example.danny.geometryhelper;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen extends Activity {

    private String[] mCardTexts ={
            "Area of a Triangle",
            "Area of a Circle",
            "Quadratic Formula"
    };

    private Class[] mActivities = {
            AreaTriangle.class,
            AreaCircle.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen1);

        GridView gridview = (GridView)findViewById(R.id.home_screen_grid);
        gridview.setAdapter(new CardAdapter(this,mCardTexts));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                View i = (ImageView)v.findViewById(R.id.card_img);

                String transition;

                Intent intent = new Intent(HomeScreen.this,mActivities[position]);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    transition = i.getTransitionName();

                    Pair<View, String> pair = Pair.create(i,transition);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this, pair);
                    startActivity(intent,options.toBundle());

                }

                else {
                    startActivity(intent);
                }
            }
        });


      /*  tri.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(HomeScreen.this, AreaTriangle.class);
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            ActivityOptions options = ActivityOptions.
                                    makeSceneTransitionAnimation(HomeScreen.this, triImage, getString(R.string.tirangle_transition_name));
                            startActivity(intent, options.toBundle());

                        }
                        else{
                            startActivity(intent);
                        }

                    }
                }
        );

        circ.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(HomeScreen.this,AreaCircle.class);
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            ActivityOptions options = ActivityOptions.
                                    makeSceneTransitionAnimation(HomeScreen.this, circImage, getString(R.string.circle_transition_name));
                            startActivity(intent, options.toBundle());
                        }
                        else {
                            startActivity(intent);
                        }

                    }
                }
        );
        */





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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
}
