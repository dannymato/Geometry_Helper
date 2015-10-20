package com.example.danny.geometryhelper;

import android.util.Log;

/**
 * Created by danny on 10/19/15.
 */
public class AlgebraParser {

    private String expression;

    public AlgebraParser(String s){
        expression = s;
    }

    protected int[] getCoEfficients(){
        String mod = expression;

        mod = removeWhitespace(mod);

        String highest = mod.substring(mod.indexOf("^")+1,mod.indexOf("^")+2);
        int[] coefficients = new int[Integer.parseInt(highest)+1];

        coefficients[0] = Integer.parseInt(mod.substring(0, mod.indexOf("x")));

        mod = mod.substring(mod.indexOf("^") + 2);

        Log.d("Algebra Parse", "String: " + mod);

        for(int i = 1; i < coefficients.length; i++){

            int x = 0;

            if(!mod.contains("x"))
                coefficients[i] = Integer.parseInt(mod);


            else {
                x = Integer.parseInt(mod.substring(0, mod.indexOf("x")));


                coefficients[i] = x;

                if(mod.contains("^"))
                    mod = mod.substring(mod.indexOf("x")+3);


                else
                    mod = mod.substring(mod.indexOf("x") + 1);


            }

            Log.d("Algebra Parse", "String: " + mod);
        }

        return coefficients;
    }

    protected String removeWhitespace(String s){
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
