package com.example.danny.geometryhelper.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danny.geometryhelper.R;


public class BlankFragment extends android.support.v4.app.Fragment {


    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    public BlankFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }



}
