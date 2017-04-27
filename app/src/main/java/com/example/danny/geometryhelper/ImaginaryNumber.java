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

        if(real == realNum){
            sReal = String.valueOf(real);
        }
        else{
            sReal = Tools.truncString(realNum,context);
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
            sImage = Tools.truncString(imageNum,context);
        }



        return ((isNeg) ? sReal + " - " + sImage + "i":sReal + " + " + sImage + "i");

    }

}
