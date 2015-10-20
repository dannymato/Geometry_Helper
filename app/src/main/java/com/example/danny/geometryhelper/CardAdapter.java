package com.example.danny.geometryhelper;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CardAdapter extends BaseAdapter {

    Context mContext;
    private String[] mCardTexts;
    private int[] mCardImgs;

    public CardAdapter(Context c, String[] s,int[] i){
        System.out.println("Passed CardAdapter");
        mContext = c;
        mCardTexts = s;
        mCardImgs = i;
        System.out.println("Passed Through CardAdapter");
        System.out.println(c.getPackageName());
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

        System.out.println("Got to getView");

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

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
