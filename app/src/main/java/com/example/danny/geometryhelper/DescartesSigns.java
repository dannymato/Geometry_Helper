package com.example.danny.geometryhelper;

import android.util.Log;


public class DescartesSigns {

    public AlgebraParser parser;

    private String expression;

    private int[] coeffs;

    private int[] simpleCoeffs;

    private int[] simpleNegCoeffs;

    public DescartesSigns(String s){

        expression = s;
        parser = new AlgebraParser(expression);
        if(parser.getCoEfficients() != null)
            coeffs = parser.getCoEfficients();
    }

    public int getPosRealFactors() {

        int numFactors = 0;

        setSimpleCoeffs();

        for(int i = 1; i < simpleCoeffs.length; i++){

            if(simpleCoeffs[i] == 0){}

            else{
                if(simpleCoeffs[i] == simpleCoeffs[i-1]);
                else
                    numFactors++;
            }

        }

        Log.d("Descartes: ","Positive Factors: " + numFactors);

        return numFactors;

    }

    public int getRealNegFactors(){

        int numFactors = 0;

        setSimpleNegCoeffs();

        for(int i = 1; i < simpleNegCoeffs.length; i++){

            if(simpleNegCoeffs[i] == 0){}

            else{
                if(simpleNegCoeffs[i] == simpleNegCoeffs[i-1]);
                else
                    numFactors++;
            }

        }

        Log.d("Descartes: ","Negative Factors: " + numFactors);

        return numFactors;

    }

    public void setSimpleCoeffs(){

        simpleCoeffs = new int[coeffs.length];

        for (int i = 0; i < coeffs.length; i++){
            if(coeffs[i] < 0)
                simpleCoeffs[i] = -1;
            else if(coeffs[i] > 0)
                simpleCoeffs[i] = 1;
            else
                simpleCoeffs[i] = 0;
        }

    }

    public void setSimpleNegCoeffs(){

        setSimpleCoeffs();
        simpleNegCoeffs = new int[simpleCoeffs.length];
        if(simpleCoeffs.length %2 == 0){
            for(int i = 0; i < simpleCoeffs.length; i++){

                if(i%2 == 0)
                    simpleNegCoeffs[i] = simpleCoeffs[i]*-1;

                else
                    simpleNegCoeffs[i] = simpleCoeffs[i];

            }
        }
        else{
            for(int i = 0; i < simpleCoeffs.length;i++){
                if(i%2 == 0)
                    simpleNegCoeffs[i] = simpleCoeffs[i];
                else
                    simpleNegCoeffs[i] = simpleCoeffs[i]*-1;
            }
        }

    }




}
