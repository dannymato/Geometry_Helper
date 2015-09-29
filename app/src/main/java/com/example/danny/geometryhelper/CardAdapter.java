package com.example.danny.geometryhelper;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Danny on 9/16/2015.
 */
public class CardAdapter extends BaseAdapter {

    Context mContext;
    private String[] mCardTexts;
    private int[] mCardImgs ={
            R.drawable.tri_img,
            R.drawable.circle_img,
            R.drawable.quad_img,
            R.drawable.circle_img,
            R.drawable.circle_img
    };

    public CardAdapter(Context c, String[] s){
        mContext = c;
        mCardTexts = s;
    }



    @Override
    public int getCount() {
        return mCardImgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = new View(mContext);

            gridView = inflater.inflate(R.layout.card_template, null);

            TextView textView = (TextView) gridView
                    .findViewById(R.id.card_info);
            textView.setText(mCardTexts[position]);

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.card_img);
            imageView.setImageResource(mCardImgs[position]);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                imageView.setTransitionName("imgTransition" + position);
            }

        return gridView;
    }
}
