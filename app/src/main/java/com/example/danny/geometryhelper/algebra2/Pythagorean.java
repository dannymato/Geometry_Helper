package com.example.danny.geometryhelper.algebra2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.Tools;

public class Pythagorean extends AppCompatActivity {

    private String sA = "";
    private String sB = "";
    private String sC = "";

    private double A = 0;
    private double B = 0;
    private double C = 0;

    private boolean isAEdited = false;
    private boolean isBEdited = false;
    private boolean isCEdited = false;

    EditText editA;
    EditText editB;
    EditText editC;

    Button clear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pythag);

        Toolbar toolbar = (Toolbar)findViewById(R.id.pythagToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editA = (EditText)findViewById(R.id.pythANum);
        editB = (EditText)findViewById(R.id.pythBNum);
        editC = (EditText)findViewById(R.id.pythCNum);

        editA.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        editB.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        editC.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        clear = (Button)findViewById(R.id.clrBtn);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editA.setText("");
                editB.setText("");
                editC.setText("");
                editA.requestFocus();
                isAEdited = false;
                isCEdited = false;
                isBEdited = false;
            }
        });




    }

    private void calculate(){

        sA = editA.getText().toString();
        sB = editB.getText().toString();
        sC = editC.getText().toString();

        isAEdited = editA.isFocused() || isAEdited;
        isBEdited = editB.isFocused() || isBEdited;
        isCEdited = editC.isFocused() || isCEdited;

        Log.d("Pyth", sA + sB + sC);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String numDec = sharedPreferences.getString("example_list","4");

        //A is the only empty
        if(!editA.isFocused() && Tools.stringCheck(sB) && Tools.stringCheck(sC) && !isAEdited){

            B = Double.parseDouble(sB);
            C = Double.parseDouble(sC);

           double disc = Math.pow(C,2) - Math.pow(B,2);
            if(disc > 0) {
                A = Math.sqrt(disc);

                String temp = String.format("%." + numDec + "f", A);
                temp = Tools.removeZeros(temp);

                editA.setText(temp);
            }

        }
        //B is the only empty
        else if(Tools.stringCheck(sA) && !editB.isFocused() && Tools.stringCheck(sC)){

            A = Double.parseDouble(sA);
            C = Double.parseDouble(sC);

            double disc = Math.pow(C,2) - Math.pow(A,2);
            if(disc > 0) {
                B = Math.sqrt(disc);

                String temp = String.format("%." + numDec + "f", B);
                temp = Tools.removeZeros(temp);

                editB.setText(temp);
            }

        }
        //C is the only empty
        else if(Tools.stringCheck(sA) && Tools.stringCheck(sB) && !editC.isFocused()){

            B = Double.parseDouble(sB);
            A = Double.parseDouble(sA);

            double disc = Math.pow(A,2) + Math.pow(B,2);

            Log.d("pyth", String.valueOf(disc));

            C = Math.sqrt(disc);

            String temp = String.format("%." + numDec + "f", C);
            temp = Tools.removeZeros(temp);

            editC.setText(temp);

        }

    }

}
