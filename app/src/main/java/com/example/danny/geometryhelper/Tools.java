package com.example.danny.geometryhelper;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Tools {


    private static String string;
    private static Context context;

    //Returns true if the string can be parsed to a double (assumes the string cannot contain any alphabetic characters)
    public static boolean stringCheck(String s){return !s.equals("-") && !s.equals(".") && !s.equals("") && containsNumber(s);}

    //Returns true if the string can be cast to a string
    public static boolean containsNumber(String s){
        boolean x = true;
        try{
            Double d = Double.valueOf(s);
        }
        catch (ClassCastException e){
            x = false;
        }
        return x;
    }

    public static String removeZeros(String s){
        if(!containsNumber(s))
            return s;
        String temp = s;
        Log.d("Remove Zeros", temp.substring(temp.length()-1));
        if(temp.substring(temp.length()-1).equals("0") && temp.contains(".")){
            temp = temp.substring(0,temp.length()-2);
            Log.d("Remove Zeros", temp);
            temp = removeZeros(temp);
        }
        else if(temp.substring(temp.length()-1).equals(".")){
            temp = temp.substring(0,temp.length()-2);
            return temp;
        }
        return temp;
    }
    //Truncates string to requested decimal place
    public static String truncString(Double string, Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String numDec = sharedPreferences.getString("example_list","4");

        return String.format("%." + numDec + "f", string);

    }

}
