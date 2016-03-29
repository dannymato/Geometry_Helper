package com.example.danny.geometryhelper.UI;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;


class CardAdapter extends BaseAdapter {

    private final Context mContext;
    private final String[] mCardTexts;
    private final int[] mCardImgs;

    public CardAdapter(Context c, String[] s,int[] i){
        mContext = c;
        mCardTexts = s;
        mCardImgs = i;
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


                gridView = inflater.inflate(R.layout.card_template, null);

                TextView textView = (TextView) gridView
                        .findViewById(R.id.card_info);
                textView.setText(mCardTexts[position]);

                ImageView imageView = (ImageView) gridView
                        .findViewById(R.id.card_img);


                Log.d("Card Load", String.valueOf(++NavDrawer.numCards));
                Log.d("Card Count", String.valueOf(getCount()));
                new LoadImagesTask(imageView, mContext).execute(mCardImgs[position]);
            //  imageView.setImageResource(mCardImgs[position]);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    if (mCardTexts[0].equals("Triangles"))
                        imageView.setTransitionName("geoTransition" + position);
                    else if (mCardTexts[0].equals("Descartes"))
                        imageView.setTransitionName("algTransition" + position);
                }


        return gridView;
    }



}

