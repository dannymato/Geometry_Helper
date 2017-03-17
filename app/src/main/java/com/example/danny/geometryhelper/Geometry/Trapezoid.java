package com.example.danny.geometryhelper.Geometry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.Tools;

public class Trapezoid extends AppCompatActivity {

    private EditText tb1;
    private EditText tb2;
    private EditText th;
    private TextView ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trapezoid);

        Toolbar toolbar = (Toolbar)findViewById(R.id.trapToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        if(Tools.stringCheck(sb1) && Tools.stringCheck(sb2) && Tools.stringCheck(sh)){

            double b1 = Double.parseDouble(sb1);
            double b2 = Double.parseDouble(sb2);
            double h = Double.parseDouble(sh);

            double a1 = (b1 + b2);

            a1 *= 0.5;

            a1 *= h;

            double a = (a1) * h;

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String numDec = preferences.getString("example_list","4");

            Log.d("TrapArea", "Trap: " + a);

            String temp = String.format("%." + numDec + "f", a1);
            temp = Tools.removeZeros(temp) + R.string.sq_unit;
            temp = "Area = " + temp;

            ta.setText(temp);

        }

    }
}
