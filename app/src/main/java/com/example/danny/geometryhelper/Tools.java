package com.example.danny.geometryhelper;


public class Tools {

    public static boolean stringCheck(String s){

        if(s.equals(""))
            return false;
        else if(s.equals("."))
            return false;
        else return !s.equals("-");

    }

}
