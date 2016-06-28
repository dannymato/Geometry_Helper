package com.example.danny.geometryhelper.UI;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ViewPagerFragment extends Fragment {

    private ImageView img;
    private String _transitionName;
    private boolean isNull = false;

    public void onResume(){
        super.onResume();
        _transitionName = ((getActivity().getIntent().getIntExtra("tabIndex",0) == 0) ? "geoTransition":"algTransition")
                + String.valueOf(getActivity().getIntent().getIntExtra("gridIndex",0));
        if(_transitionName != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(!isNull)
                setTransitionNameLollipop();
            else
                setTransitionNull();
            ActivityCompat.startPostponedEnterTransition(getActivity());
            Log.d("Transition", "onResume");
        }
    }


    protected void assignImageView(View rootview, int id){
        img = (ImageView)rootview.findViewById(id);
    }

    protected void assignImageView(View rootview,int id, Boolean bool){
        img = (ImageView)rootview.findViewById(id);
        isNull=bool;

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setTransitionNameLollipop(){
        img.setTransitionName(_transitionName);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setTransitionNull(){img.setTransitionName(null);}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private String getTransitionName(){return img.getTransitionName();}

}
