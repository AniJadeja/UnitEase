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

    // Binding for the layout
    ActivityChooseTypeBinding chooseTypeBinding;

    // Buttons for conversion and history
    MaterialButton conversionButton, historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);

        // Inflating the layout using view binding
        chooseTypeBinding = ActivityChooseTypeBinding.inflate(getLayoutInflater());
        setContentView(chooseTypeBinding.getRoot());

        // Initializing UI components
        init();
    }

    // Method to initialize UI components
    private void init() {
        Log.d("MeuApp", "chegou no init ");

        // Initializing buttons from the layout
        conversionButton = chooseTypeBinding.conversionButton;
        historyButton = chooseTypeBinding.historyButton;

        // Setting click listeners for buttons
        conversionButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Handling button clicks
        if (v == conversionButton) {
            Log.d("MeuApp", "choose conversion ");
            // Starting ChooseConversionActivity with isHistory set to false
            startActivity(new Intent(this, ChooseConversionActivity.class).putExtra("isHistory", false));
//            finish();
        } else if (v == historyButton) {
            // Starting ChooseConversionActivity with isHistory set to true
            startActivity(new Intent(this, ChooseConversionActivity.class).putExtra("isHistory", true));
//            finish();
        }
    }
}
