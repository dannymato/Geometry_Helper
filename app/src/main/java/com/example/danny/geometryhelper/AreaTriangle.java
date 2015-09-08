package com.example.danny.geometryhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AreaTriangle extends Activity {

    EditText base;
    EditText height;

    TextView area;

    double b;
    double h;
    double a;

    int check;

    String bb;
    String hh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_triangle);

        base = (EditText)findViewById(R.id.editText);
        height = (EditText)findViewById(R.id.editText2);

        area = (TextView)findViewById(R.id.area_num);

        base.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        calculate();
                        return false;
                    }
                }
        );

        height.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        calculate();
                        return false;
                    }
                }
        );

        check = 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_area_triangle, menu);
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

    protected void calculate(){
        bb = base.getText().toString();
        hh = height.getText().toString();


        if(bb.equals("") || hh.equals("")){
            area.setText("0");
        }

        else{
            b = Double.valueOf(bb);
            h = Double.valueOf(hh);

            a = .5*b*h;

            check = (int)a;

            if(check == a){
                area.setText(String.valueOf(check));
            }
            else {
                area.setText(String.valueOf(a));
            }
        }
    }
}
