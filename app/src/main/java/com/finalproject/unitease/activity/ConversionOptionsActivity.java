package com.finalproject.unitease.activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.finalproject.unitease.R;
import com.finalproject.unitease.databinding.ActivityCovnersationOptionsBinding;
import com.finalproject.unitease.recyclerviewadapter.OptionsRecyclerViewAdapter;
import com.finalproject.unitease.uicomponent.UnitEaseButton;
import com.finalproject.unitease.utils.ConversionConfiguration;
import com.finalproject.unitease.utils.InputLayoutConfiguration;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;

public class ConversionOptionsActivity extends AppCompatActivity implements OptionsRecyclerViewAdapter.OnOptionSelectedListener, View.OnClickListener {

    private static final String FLOW_TAG = "Flow - ConversionOptionsActivity";
    private static final String DEBUG_TAG = "DebugUnitEase - ConversionOptionsActivity";

    private WearableRecyclerView recyclerView;
    private MaterialButton checkButton, getConversionButton, conversionButton;
    private int conversionId;
    private ActivityCovnersationOptionsBinding optionsBinding;
    private UnitEaseButton button;
    private TextInputLayout conversionInputLayout;
    private TextInputEditText conversionEditText;
    private Boolean isConversionButtonFavorite = false;

    private String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionsBinding = ActivityCovnersationOptionsBinding.inflate(getLayoutInflater());
        setContentView(optionsBinding.getRoot());
        init();
        setupRecyclerView();
    }


    // ----------------------------------------- Overridden Methods ----------------------------------------- //

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    // ----------------------------------------- User Defined Methods ----------------------------------------- //

    private void init() {
        recyclerView = optionsBinding.optionsRecyclerView;
        checkButton = optionsBinding.checkboxButton;
        conversionButton = optionsBinding.conversionButton;
        conversionInputLayout = optionsBinding.conversionTextInputLayout;
        conversionEditText = optionsBinding.conversionValueEditText;
        conversionButton.setClickable(false);
        getConversionButton = optionsBinding.getConversionsButton;

        String getConversionButtonText = getString(R.string.get) + getIntent().getStringExtra("Conversion") + getString(R.string.s);
        String conversionType = getIntent().getStringExtra("Conversion");
        conversionId = UnitEaseButton.getButtonId(conversionType);
        button = new UnitEaseButton(conversionId);
        int primaryColor = button.getButtonBackgroundColor();
        int secondaryColor = button.getButtonBackgroundTint();

        setStrrokeColor(conversionInputLayout, primaryColor, secondaryColor);
        Log.d(DEBUG_TAG, "init: "+String.valueOf(getColor(primaryColor)) + " " + String.valueOf(getColor(secondaryColor)));

        conversionButton.setText(conversionType);
        conversionButton.setBackgroundColor(getColor(primaryColor));
        getConversionButton.setBackgroundColor(getColor(primaryColor));
        getConversionButton.setText(getConversionButtonText);

        conversionEditText.setTextColor(getColor(button.getButtonBackgroundColor()));

        checkButton.setIconTint(ColorStateList.valueOf(getColor(primaryColor)));
        checkButton.setIconResource(R.drawable.heart_filled);
        checkButton.setStateListAnimator(null);
        checkButton.setOnClickListener(this);

        ConversionConfiguration configuration = new ConversionConfiguration(conversionId);
        options = configuration.getOptions();
    }

    private void setStrrokeColor(TextInputLayout conversionInputLayout, int primaryColor, int secondaryColor) {

        int[][] states = {
                new int[]{android.R.attr.state_active},
                new int[]{android.R.attr.state_focused},
                new int[]{-android.R.attr.state_focused},
                new int[]{android.R.attr.state_hovered},
                new int[]{android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_enabled}
        };

        int[] colors = {
                getColor(primaryColor),
                getColor(primaryColor),
                getColor(secondaryColor),
                getColor(primaryColor),
                getColor(primaryColor),
                getColor(secondaryColor)
        };

        ColorStateList colorState = new ColorStateList(states, colors);
        conversionInputLayout.setBoxStrokeColorStateList(colorState);
        conversionInputLayout.setDefaultHintTextColor(ColorStateList.valueOf(getColor(primaryColor)));


    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        OptionsRecyclerViewAdapter adapter = new OptionsRecyclerViewAdapter(this, button.getButtonBackgroundColor(), button.getButtonBackgroundTint(), Arrays.asList(options), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onOptionSelected(String buttonText) {
        Log.d(DEBUG_TAG, "onOptionSelected: selected option is " + buttonText);
    }

    @Override
    public void onClick(View v) {

        if (v == checkButton) {
            if (isConversionButtonFavorite) {
                checkButton.setIconResource(R.drawable.heart_filled);
                isConversionButtonFavorite = false;
            } else {
                checkButton.setIconResource(R.drawable.heart);
                isConversionButtonFavorite = true;
            }
        } else if (v == getConversionButton) {
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("Conversion", conversionButton.getText().toString());
            intent.putExtra("ConversionValue", conversionEditText.getText().toString());
            startActivity(intent);

        }

    }
}