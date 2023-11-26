package com.finalproject.unitease.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.finalproject.unitease.R;

public class ResultsActivity extends AppCompatActivity {

    private static final String FLOW_TAG = "Flow - ResultsActivity";
    private static final String DEBUG_TAG = "DebugUnitEase - ResultsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }
}