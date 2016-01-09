package com.example.danny.geometryhelper;

import android.util.Log;

public class AlgebraParser {

    private String expression;

    public AlgebraParser(String s){
        expression = s;
    }

    public int[] getCoEfficients(){
        String mod = expression;

        int[] coefficients;
        mod = removeWhitespace(mod);

        if(mod.contains("^")) {
            String highest = mod.substring(mod.indexOf("^") + 1, mod.indexOf("^") + 2);
            coefficients = new int[Integer.parseInt(highest) + 1];

            coefficients[0] = Integer.parseInt(mod.substring(0, mod.indexOf("x")));

            mod = mod.substring(mod.indexOf("^") + 2);

            Log.d("Algebra Parse", "String: " + mod);

            for (int i = 1; i < coefficients.length; i++) {

                int x;

                if (!mod.contains("x")){
                    coefficients[i] = Integer.parseInt(mod);}

                else if((Integer.parseInt(mod.substring(mod.indexOf("^") + 1, mod.indexOf("^") + 2))) != (coefficients.length - i - 1)){
                    coefficients[i] = 0;}

                else {

                    if (mod.startsWith("+"))
                        x = Integer.parseInt(mod.substring(1, mod.indexOf("x")));
                    else
                        x = Integer.parseInt(mod.substring(0, mod.indexOf("x")));


                    coefficients[i] = x;

                    Log.d("Algebra Parse", "Coefficients: " + x);

                    if (mod.contains("^"))
                        mod = mod.substring(mod.indexOf("x") + 3);


                    else
                        mod = mod.substring(mod.indexOf("x") + 1);


                }

                Log.d("Algebra Parse", "String: " + mod);
            }
        }
        else{
            coefficients = null;
        }


        return coefficients;
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
