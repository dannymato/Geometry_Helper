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

public class VolCyln extends Activity {

    private double h;
    private double r;
    private double v;

    private String sH;
    private String sR;
    private String sV;

    private EditText editH;
    private EditText editR;
    private TextView textV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_cyln);

        editH = (EditText)findViewById(R.id.cyl_height);
        editR = (EditText)findViewById(R.id.cyl_rad);

        textV = (TextView)findViewById(R.id.cyl_vol_num);

        editH.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });

        editR.setOnKeyListener(new View.OnKeyListener() {
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
        getMenuInflater().inflate(R.menu.menu_vol_cyln, menu);
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

        sH = editH.getText().toString();
        sR = editR.getText().toString();

        if(sH.equals("") || sR.equals("")){}

        else {

            r = Double.valueOf(sR);
            h = Double.valueOf(sH);

            v = Math.pow(r,2)*Math.PI*h;

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String numDec = sharedPreferences.getString("example_list","");
            System.out.println(numDec);

            textV.setText(String.format("%."+numDec+"f",v));

        }

    }
}
