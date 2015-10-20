package com.example.danny.geometryhelper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Trapezoid extends AppCompatActivity {

    private double b1;
    private double b2;
    private double h;
    private double a;

    private String sb1;
    private String sb2;
    private String sh;

    private EditText tb1;
    private EditText tb2;
    private EditText th;
    private TextView ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trapezoid);

        tb1 = (EditText)findViewById(R.id.editText4);
        tb2 = (EditText)findViewById(R.id.editText5);
        th = (EditText)findViewById(R.id.editText6);

        ta = (TextView)findViewById(R.id.trap_area);

        tb1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        tb2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        th.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });



    }

    public void calculate(){

        sb1 = tb1.getText().toString();
        sb2 = tb2.getText().toString();
        sh = th.getText().toString();

        if(!(sb1.equals("") || sb2.equals("") || sh.equals("") || sb1.equals(".") || sb2.equals(".") || sh.equals("."))){

            b1 = Double.parseDouble(sb1);
            b2 = Double.parseDouble(sb2);
            h = Double.parseDouble(sh);

            a = ((b1 + b2)/2)*h;

            int x = (int)a;

            if(x == a){
                ta.setText("Area = " + x);
            }

            else{
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                String numDec = preferences.getString("example_list","");

                Log.d("TrapArea", "Trap: " + a);

                ta.setText(String.format("%." + numDec + "f",a));
            }

        }

    }
}
