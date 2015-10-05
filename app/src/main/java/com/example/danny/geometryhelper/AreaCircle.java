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

public class AreaCircle extends Activity {

    EditText rad;

    TextView area;

    double r;
    double a;

    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_circle);

        area = (TextView)findViewById(R.id.circ_area_num);
        rad = (EditText)findViewById(R.id.editText3);

        rad.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        calculate();
                        return false;
                    }
                }
        );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_area_circle, menu);
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
        if(!rad.getText().toString().equals("")){
            r = Double.valueOf(rad.getText().toString());
            a = Math.PI*r*r;

            check = (int) a;

            if(check == a){
                area.setText(String.valueOf(check));
            }
            else{

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                String numDec = sharedPreferences.getString("example_list","");
                System.out.println(numDec);

                area.setText(String.format("%."+numDec+"f",a));
            }


        }
    }
}
