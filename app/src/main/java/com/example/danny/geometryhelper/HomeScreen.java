package com.example.danny.geometryhelper;
    import android.Manifest;
    import android.app.ActivityOptions;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.os.Build;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v4.app.ActivityCompat;
    import android.support.v4.app.Fragment;
    import android.support.v4.content.ContextCompat;
    import android.util.Log;
    import android.util.Pair;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.GridView;

    import com.google.android.gms.ads.AdRequest;
    import com.google.android.gms.ads.AdView;

public class HomeScreen extends Fragment {

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

    private int[] mCardImgs ={
            R.drawable.tri_img,
            R.drawable.circle_img,
            R.drawable.quad_img,
            R.drawable.sphere_img,
            R.drawable.circle_img,//TODO: Draw cylinder
            R.drawable.tri_img //TODO: Draw Trapezoid
    };







    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("Build Version", Build.VERSION.SDK_INT + " " + Build.VERSION_CODES.M);



       View rootView = inflater.inflate(R.layout.activity_home_screen1, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.home_screen_grid);
        Log.d("wtf", "gridview: " + gridview);
        gridview.setAdapter(new CardAdapter(getActivity(), mCardTexts, mCardImgs));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                View i = v.findViewById(R.id.card_img);

                String transition;

                Intent intent = new Intent(getContext(), mActivities[position]);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    transition = i.getTransitionName();

                    Pair<View, String> pair = Pair.create(i, transition);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pair);
                    getContext().startActivity(intent,options.toBundle());

                } else {
                    startActivity(intent);
                }
            }
        });
        Log.d("Ad", "AdID: " + R.id.adView);
        AdView mAdView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        Log.d("Ad", "Ad: " + mAdView);
        mAdView.loadAd(adRequest);


        return rootView;
    }

    /*@Override
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
    }*/
}
