package com.example.danny.geometryhelper.algebra2;

import android.os.Bundle;
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
            }
        });




    }

    private void calculate(){


        sA = editA.getText().toString();
        sB = editB.getText().toString();
        sC = editC.getText().toString();


        Log.d("Pyth", sA + sB + sC);

        if(!Tools.stringCheck(sA) && Tools.stringCheck(sB) && Tools.stringCheck(sC)){

            B = Double.parseDouble(sB);
            C = Double.parseDouble(sC);

           // double disc = Math.pow(C,2) - Math.pow(B,2);
            A = Math.sqrt(Math.pow(C,2) - Math.pow(B,2));

            editA.setText(Tools.truncString(A,getApplicationContext()));

        }

        else if(Tools.stringCheck(sA) && !Tools.stringCheck(sB) && Tools.stringCheck(sC)){

            A = Double.parseDouble(sA);
            C = Double.parseDouble(sC);

            double disc = Math.pow(C,2) - Math.pow(A,2);
            B = Math.sqrt(disc);

            editB.setText(Tools.truncString(B,getApplicationContext()));

        }
        else if(Tools.stringCheck(sA) && Tools.stringCheck(sB) && !Tools.stringCheck(sC)){

            B = Double.parseDouble(sB);
            A = Double.parseDouble(sA);

            double disc = Math.pow(A,2) + Math.pow(B,2);

            Log.d("pyth", String.valueOf(disc));

            C = Math.sqrt(disc);

            editC.setText(Tools.truncString(C,this.getBaseContext()));

            Log.d("Pyth", String.valueOf(C));

        }


    }

}
