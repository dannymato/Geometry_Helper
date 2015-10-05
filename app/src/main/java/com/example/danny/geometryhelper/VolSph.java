package com.example.danny.geometryhelper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VolSph extends Activity {

    private double rad;
    private double vol;

    private TextView vTxt;
    private EditText rTxt;

    private String sRad;
    private String sVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_sph);

        vTxt = (TextView)findViewById(R.id.sph_vol_num);
        rTxt = (EditText)findViewById(R.id.vol_editText);

        rTxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vol_sph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculate(){

        sRad = rTxt.getText().toString();

        if(!sRad.equals("")){

            rad = Double.valueOf(sRad);

            vol = (4/3)*Math.PI*Math.pow(rad,3);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String numDec = sharedPreferences.getString("example_list","");
            System.out.println(numDec);

            vTxt.setText(String.format("%."+numDec+"f",vol));

        }

    }
}
