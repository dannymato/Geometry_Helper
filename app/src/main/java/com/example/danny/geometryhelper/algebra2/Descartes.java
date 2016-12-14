package com.example.danny.geometryhelper.algebra2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danny.geometryhelper.DescartesSigns;
import com.example.danny.geometryhelper.R;

public class Descartes extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    private String posRoots = "";
    private String negRoots = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decartes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.decartToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = (EditText)findViewById(R.id.decart_edit);
        textView = (TextView)findViewById(R.id.decart_view);


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("Descartes","Shit");
                textView.setText(findRoots());
                Log.d("Descartes","Shit");

                return false;
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("descartes","shit");
                return false;
            }
        });

    }


    private String findRoots(){
        DescartesSigns descartesSigns = new DescartesSigns(editText.getText().toString());

        String temp;

        int negRoot = descartesSigns.getRealNegFactors();
        int posRoot = descartesSigns.getPosRealFactors();

        for(int i = negRoot; i >=0; i-=2){
            if(i == 0 || i == 1)
                negRoots += i;
            else
                negRoots += i + " or ";
        }

        for(int i = posRoot; i >=0; i-=2){
            if(i == 0 || i == 1)
                posRoots += i;
            else
                posRoots += i + " or ";
        }

        Log.d("Descartes", "Positive Roots: " + posRoots);

        temp = "Positive Roots: " + posRoots + "\nNegative Roots: " + negRoots;

        return temp;
    }
}
