package com.example.danny.geometryhelper;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Tools {


    private static String string;
    private static Context context;

    //If the string can be parsed to double returns true
    //Else returns false
    public static boolean stringCheck(String s){

        if(s.equals(""))
            return false;
        else if(s.equals("."))
            return false;
        else return !s.equals("-");

    }
    //Truncates string to requested decimal place
    public static String truncString(Double string, Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String numDec = sharedPreferences.getString("example_list","4");

        return String.format("%." + numDec + "f", string);

    }

}
