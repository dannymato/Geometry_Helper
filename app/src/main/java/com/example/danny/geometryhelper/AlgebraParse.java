package com.example.danny.geometryhelper;

import android.util.Log;

import java.util.ArrayList;

public class AlgebraParse {

    private String exp;

    public AlgebraParse(String exp){
        this.exp = exp;
    }

    public double[] getValues(){
        String tmp = removeWhitespace(exp);
        ArrayList<Double> values = new ArrayList<>();

        while(tmp.contains(",")){
            values.add(Double.parseDouble(tmp.substring(0,tmp.indexOf(","))));
            tmp = tmp.substring(tmp.indexOf(",") + 1);
        }
        values.add(Double.parseDouble(tmp));

        return toArray(values);
    }

    public double[] toArray(ArrayList<Double> values){

        double[] x = new double[values.size()];

        for(int i = 0; i < x.length;i++) {
            x[i] = values.get(i);
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
