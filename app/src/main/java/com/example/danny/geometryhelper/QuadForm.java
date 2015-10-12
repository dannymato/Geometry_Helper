package com.example.danny.geometryhelper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuadForm extends AppCompatActivity {

    EditText txtA;
    EditText txtB;
    EditText txtC;

    double numA;
    double numB;
    double numC;
    double numAns;
    double numAns1;

    double dis;

    TextView txtAns;

    Button calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quad_form);

        txtA = (EditText)findViewById(R.id.quadANum);
        txtB = (EditText)findViewById(R.id.quadBNum);
        txtC = (EditText)findViewById(R.id.quadCNum);

        txtAns = (TextView)findViewById(R.id.quadAns);

        txtA.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        txtB.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate();
                return false;
            }
        });
        txtC.setOnKeyListener(new View.OnKeyListener() {
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
        getMenuInflater().inflate(R.menu.menu_quad_form, menu);
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

        if(txtB.getText().toString().equals("") || txtA.getText().toString().equals("") || txtC.getText().toString().equals("")
           || txtB.getText().toString().equals("-") || txtA.getText().toString().equals("-") || txtC.getText().toString().equals("-")     ){}

        else{

            numA = Double.parseDouble(txtA.getText().toString());
            numB = Double.parseDouble(txtB.getText().toString());
            numC = Double.parseDouble(txtC.getText().toString());
            dis = (Math.pow(numB, 2)) + (- 4*numA*numC);

            if(dis < 0){
                //txtAns.setText("Not a Real Number");
                Toast toast = Toast.makeText(this, "The Answer is not a Real Number", Toast.LENGTH_SHORT);
                toast.show();

            }
            else{
                numAns = ((-numB) + Math.sqrt(dis))/(2*numA);
                numAns1 = ((-numB) - Math.sqrt(dis))/(2*numA);

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                String numDec = sharedPreferences.getString("example_list","");
                System.out.println(numDec);

                txtAns.setText("X = " + String.format("%."+numDec+"f",numAns) + ", " + String.format("%."+numDec+"f",numAns1));
            }
        }
    }
}
