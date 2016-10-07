package com.example.danny.geometryhelper.Geometry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;

public class Trapezoid extends AppCompatActivity {

    private EditText tb1;
    private EditText tb2;
    private EditText th;
    private TextView ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trapezoid);

        tb1 = (EditText)findViewById(R.id.editText6);
        tb2 = (EditText)findViewById(R.id.editText5);
        th = (EditText)findViewById(R.id.editText4);

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

    private void calculate(){

        String sb1 = tb1.getText().toString();
        String sb2 = tb2.getText().toString();
        String sh = th.getText().toString();

        if(!(sb1.equals("") || sb2.equals("") || sh.equals("") || sb1.equals(".") || sb2.equals(".") || sh.equals("."))){

            double b1 = Double.parseDouble(sb1);
            double b2 = Double.parseDouble(sb2);
            double h = Double.parseDouble(sh);

            double a1 = (b1 + b2);

            a1 *= 0.5;

            a1 *= h;

            double a = (a1) * h;

            int x = (int) a1;

            if(x == a1){
                ta.setText("Area = " + x);
            }

            else{
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                String numDec = preferences.getString("example_list","4");

                Log.d("TrapArea", "Trap: " + a);

                ta.setText("Area = " + String.format("%." + numDec + "f", a1));
            }

        }

    }
}
