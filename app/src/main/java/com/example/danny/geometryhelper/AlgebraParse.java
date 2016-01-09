package com.example.danny.geometryhelper;

import android.util.Log;

import java.util.ArrayList;

public class AlgebraParse {

    private String exp;

    public AlgebraParse(String exp){
        this.exp = exp;
    }

    public int[] getValues(){
        String tmp = removeWhitespace(exp);
        ArrayList<Integer> values = new ArrayList<>();

        while(tmp.contains(",")){
            values.add(Integer.parseInt(tmp.substring(0,tmp.indexOf(","))));
            tmp = tmp.substring(tmp.indexOf(",") + 1);
        }
        values.add(Integer.parseInt(tmp));

        return toArray(values);
    }

    public int[] toArray(ArrayList<Integer> swag){

        int[] x = new int[swag.size()];

        for(int i = 0; i < x.length;i++) {
            x[i] = swag.get(i);
            Log.d("Parse", x[i] + "");
        }

        return x;
    }

    private String removeWhitespace(String s){
        String tmp = s;

        if(!(tmp.contains(" "))){
            return tmp;
        }

        else{
            tmp = tmp.substring(0,tmp.indexOf(" ")) + tmp.substring(tmp.indexOf(" ")+1);
            return removeWhitespace(tmp);
        }

    }


}
