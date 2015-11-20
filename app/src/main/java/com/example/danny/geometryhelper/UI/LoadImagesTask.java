package com.example.danny.geometryhelper.UI;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

public class LoadImagesTask extends AsyncTask<Integer,Void,Bitmap> {

    public ImageView iView;
    public Context mContext;
    public int iNumber;

    public LoadImagesTask(ImageView imageView, Context context){

        iView = imageView;
        mContext = context;

    }


    @Override
    protected Bitmap doInBackground(Integer... params) {
        iNumber = params[0];

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point res = new Point();
        display.getSize(res);
        int width = res.x/2;
        int height = (int) (width*.90625);



        Log.d("Image Load", "Loaded an image");


        return decodeSampledBitmapFromResource(mContext.getResources(),iNumber, width,height);
    }

    @Override
    protected void onPostExecute(Bitmap bit){
        iView.setImageBitmap(bit);

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                        int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
