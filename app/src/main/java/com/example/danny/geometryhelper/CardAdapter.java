package com.example.danny.geometryhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Danny on 9/16/2015.
 */
public class CardAdapter extends BaseAdapter {

    Context mContext;
    private String[] mCardTexts;

    private int[] mCardImgs ={
            R.drawable.tri_img,
            R.drawable.circle_img,
            R.drawable.quad_img
    };

    public CardAdapter(Context c,String[] s){
            mCardTexts = s;
            mContext = c;

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

        View gridView;

        if(convertView == null){

            gridView = new View(mContext);

            gridView = inflater.inflate(R.layout.card_template,null);

            TextView textView = (TextView) gridView
                    .findViewById(R.id.card_info);
            textView.setText(mCardTexts[position]);

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.card_img);
            imageView.setImageResource(mCardImgs[position]);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                imageView.setTransitionName("imgTransition" + position);
            }
        }

        else{
            gridView = (View) convertView;
        }

        return gridView;
    }
}
