package com.example.danny.geometryhelper;
    import android.app.ActivityOptions;
    import android.content.Intent;
    import android.os.Build;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Pair;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.GridView;

    import com.google.android.gms.ads.AdRequest;
    import com.google.android.gms.ads.AdView;

public class HomeScreen extends AppCompatActivity {

        private String[] mCardTexts ={
                "Triangles",
                "Circles",
                "Quadratic Formula",
                "Spheres",
                "Cylinders",
                "Trapezoid"
    };

    private Class[] mActivities = {
            AreaTriangle.class,
            AreaCircle.class,
            QuadForm.class,
            Sph.class,
            Cyl.class,
            Trapezoid.class
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen1);




        GridView gridview = (GridView)findViewById(R.id.home_screen_grid);
        gridview.setAdapter(new CardAdapter(this, mCardTexts));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                View i = v.findViewById(R.id.card_img);

                String transition;

                Intent intent = new Intent(HomeScreen.this, mActivities[position]);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    transition = i.getTransitionName();

                    Pair<View, String> pair = Pair.create(i, transition);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this, pair);
                    startActivity(intent, options.toBundle());

                } else {
                    startActivity(intent);
                }
            }
        });

        AdView mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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

            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
