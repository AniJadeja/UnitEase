package com.finalproject.unitease.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.finalproject.unitease.R;
import com.finalproject.unitease.databinding.ActivityChooseTypeBinding;
import com.google.android.material.button.MaterialButton;

public class ChooseActivityType extends AppCompatActivity implements View.OnClickListener {

    ActivityChooseTypeBinding chooseTypeBinding;
    MaterialButton conversionButton, historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        chooseTypeBinding = ActivityChooseTypeBinding.inflate(getLayoutInflater());
        setContentView(chooseTypeBinding.getRoot());
        init();
    }

    private void init() {
        Log.d("MeuApp", "chegou no init ");
        conversionButton = chooseTypeBinding.conversionButton;
        historyButton = chooseTypeBinding.historyButton;
        conversionButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == conversionButton){
            Log.d("MeuApp", "choose conversion ");
            startActivity(new Intent(this, ChooseConversionActivity.class).putExtra("isHistory", false));
            finish();
        } else if (v == historyButton){
            startActivity(new Intent(this, ChooseConversionActivity.class).putExtra("isHistory", true));
            finish();
        }
    }
}