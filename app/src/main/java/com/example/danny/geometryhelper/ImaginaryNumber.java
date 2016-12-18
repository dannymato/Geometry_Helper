package com.example.danny.geometryhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ImaginaryNumber {

    private double realNum;
    private double imageNum;

    public ImaginaryNumber(double real, double image){
        realNum = real;
        imageNum = image;
    }

    public double getImageNum() {return imageNum;}

    public double getRealNum(){return realNum;}

    public String toString(Context context){

        String sReal;
        String sImage;

        int real = (int) realNum;
        int image = (int) imageNum;

        boolean isNeg = false;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String numDec = sharedPreferences.getString("example_list","4");

        if(real == realNum){
            sReal = String.valueOf(real);
        }
        else{
            sReal = String.valueOf(realNum);
            sReal = String.format(".%" + numDec + "f", sReal);
        }
        if(image == imageNum) {
            if(image < 0){
                isNeg = true;
                image = -image;
            }
            sImage = String.valueOf(image);
        }
        else{
            if(imageNum < 0){
                isNeg = true;
                imageNum = -imageNum;
            }
            sImage = String.valueOf(imageNum);
            sImage = String.format(".%" + numDec + "f", sImage);
        }



        return ((isNeg) ? sReal + " - " + sImage + "i":sReal + " + " + sImage + "i");

    }

}
