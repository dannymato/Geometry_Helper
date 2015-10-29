package com.example.danny.geometryhelper.algebra2;

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

public class DistanceForm extends AppCompatActivity {

    private EditText editx1;
    private EditText edity1;
    private EditText editx2;
    private EditText edity2;
    private TextView textView;

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double distance;
    private double discrim;

    private String sy1 = "";
    private String sx1 = "";
    private String sy2 = "";
    private String sx2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_form);

        editx1 = (EditText)findViewById(R.id.editX1);
        edity1 = (EditText)findViewById(R.id.editY1);
        editx2 = (EditText)findViewById(R.id.editX2);
        edity2 = (EditText)findViewById(R.id.editY2);
        textView = (TextView)findViewById(R.id.textView8);

        editx1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        editx2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        edity1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        edity2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });


    }


    private void calculate(){

        sy1 = edity1.getText().toString();
        sy2 = edity2.getText().toString();
        sx1 = editx1.getText().toString();
        sx2 = editx2.getText().toString();

        if(sy1.equals("") || sx2.equals("") || sx1.equals("") || sy2.equals("")){}

        else{

            x1 = Double.parseDouble(sx1);
            x2 = Double.parseDouble(sx2);
            y1 = Double.parseDouble(sy1);
            y2 = Double.parseDouble(sy2);

            Log.d("Distance Form", String.valueOf(x1));
            Log.d("Distance Form", String.valueOf(y1));
            Log.d("Distance Form", String.valueOf(x2));
            Log.d("Distance Form", String.valueOf(y2));

            double x = x2 - x1;
            double y = y2 - y1;

            discrim = Math.pow(x,2) + Math.pow(y,2);

            distance = Math.sqrt(discrim);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String numDec = sharedPreferences.getString("example_list","4");
            Log.d("Dec Preference", numDec);
            textView.setText(String.format("%." + numDec + "f", distance));
            //textView.setText(String.valueOf(distance));



        }

    }
}
