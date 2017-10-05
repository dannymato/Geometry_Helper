package com.example.danny.geometryhelper.Geometry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.R;
import com.example.danny.geometryhelper.Tools;

public class AreaTriangle extends AppCompatActivity {

    private EditText base;
    private EditText height;

    private TextView area;

    private double b;
    private double h;
    private double a;

    private int check;

    private String bb;
    private String hh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_triangle);

        Toolbar toolbar = (Toolbar)findViewById(R.id.triAreaToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    private void calculate(){
        bb = base.getText().toString();
        hh = height.getText().toString();


        if(!Tools.stringCheck(bb) || !Tools.stringCheck(hh)){
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
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                String numDec = sharedPreferences.getString("example_list","4");
                System.out.println(numDec);

                String temp = String.format("%."+numDec+"f",a);
                temp = Tools.removeZeros(temp) + " " + getString(R.string.sq_unit);

                area.setText(temp);
            }
        }
    }
}
