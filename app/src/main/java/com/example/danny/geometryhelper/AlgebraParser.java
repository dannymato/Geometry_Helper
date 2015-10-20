package com.example.danny.geometryhelper;

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
        int[] coefficients = new int[Integer.parseInt(highest)];

        coefficients[0] = Integer.parseInt(highest);

        mod = mod.substring(mod.indexOf("^")+3);

        for(int i = 1; i < coefficients.length; i++){



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
