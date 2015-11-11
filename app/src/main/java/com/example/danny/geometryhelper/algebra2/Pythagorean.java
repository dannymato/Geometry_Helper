package com.example.danny.geometryhelper.algebra2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.danny.geometryhelper.R;

/**
 * Created by danny on 11/3/15.
 */
public class Pythagorean extends AppCompatActivity {

    String sA = "";
    String sB = "";
    String sC = "";

    double A = 0;
    double B = 0;
    double C = 0;

    EditText editA;
    EditText editB;
    EditText editC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pythag);



    }

    private void calculate(){

        if(sA.equals("") && sB.equals("") && sC.equals("")){}

        else if(sA.equals("") && !sB.equals("") && !sC.equals("")){

            B = Double.parseDouble(sB);
            C = Double.parseDouble(sC);

            double disc = Math.pow(C,2) - Math.pow(B,2);
            A = Math.sqrt(disc);

        }

        else if(!sA.equals("") && sB.equals("") && !sC.equals("")){

            A = Double.parseDouble(sA);
            C = Double.parseDouble(sC);

            double disc = Math.pow(C,2) - Math.pow(A,2);
            B = Math.sqrt(disc);

        }
        else if(!sA.equals("") && !sB.equals("") && sC.equals("")){

            B = Double.parseDouble(sB);
            A = Double.parseDouble(sA);

            double disc = Math.pow(A,2) + Math.pow(B,2);
            C = Math.sqrt(disc);

        }

    }

}
