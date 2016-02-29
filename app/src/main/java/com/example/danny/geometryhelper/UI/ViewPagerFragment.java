package com.example.danny.geometryhelper.UI;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

public class ViewPagerFragment extends Fragment {

    private ImageView img;
    private String _transitionName;

    public void onViewCreated(View view, Bundle savedInstanceState){

        _transitionName = ((getActivity().getIntent().getIntExtra("tabIndex",0) == 0) ? "geoTransition":"algTransition")
                + String.valueOf(getActivity().getIntent().getIntExtra("gridIndex",0));

        super.onViewCreated(view, savedInstanceState);

        if(_transitionName != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTransitionNameLollipop();
            ActivityCompat.startPostponedEnterTransition(getActivity());
        }
    }

    protected void assignImageView(View rootview, int id){
        img = (ImageView)rootview.findViewById(id);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setTransitionNameLollipop(){
        img.setTransitionName(_transitionName);
    }

}
