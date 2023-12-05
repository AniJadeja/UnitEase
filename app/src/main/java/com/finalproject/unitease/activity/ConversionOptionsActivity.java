package com.finalproject.unitease.activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.finalproject.unitease.R;
import com.finalproject.unitease.databinding.ActivityCovnersationOptionsBinding;

import com.finalproject.unitease.model.FavoriteConversions;
import com.finalproject.unitease.recyclerviewadapter.OptionsRecyclerViewAdapter;
import com.finalproject.unitease.uicomponent.UnitEaseButton;
import com.finalproject.unitease.utils.ConversionConfiguration;
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

    private static String unit;
    private String[] options;
    FavoriteConversions favoriteConversions;

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

        setStrokeColor(conversionInputLayout, primaryColor, secondaryColor);
        Log.d(DEBUG_TAG, "init: "+String.valueOf(getColor(primaryColor)) + " " + String.valueOf(getColor(secondaryColor)));

        conversionButton.setText(conversionType);
        conversionButton.setBackgroundColor(getColor(primaryColor));
        conversionButton.setIconResource(button.getButtonIcon());
        getConversionButton.setBackgroundColor(getColor(primaryColor));
        getConversionButton.setText(getConversionButtonText);

        conversionEditText.setTextColor(getColor(button.getButtonBackgroundColor()));

        checkButton.setIconTint(ColorStateList.valueOf(getColor(primaryColor)));
        checkButton.setIconResource(R.drawable.heart);
        favoriteConversions = new FavoriteConversions(this);
        setFavoriteButton(favoriteConversions.isFavorite(conversionType));
        checkButton.setStateListAnimator(null);
        checkButton.setOnClickListener(this);
        getConversionButton.setOnClickListener(this);

        ConversionConfiguration configuration = new ConversionConfiguration(conversionId);
        options = configuration.getOptions();
    }

    private void setStrokeColor(TextInputLayout conversionInputLayout, int primaryColor, int secondaryColor) {

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
        unit = buttonText;
    }

    @Override
    public void onClick(View v) {

        if (v == checkButton) {
            if (!isConversionButtonFavorite) {
                setFavoriteButton(true);
            }
        } else if (v == getConversionButton) {
            if (conversionEditText.getText().toString().isEmpty()) {
                conversionInputLayout.setError(getString(R.string.enter_value));
                conversionInputLayout.setErrorEnabled(true);
                return;
            }
            if (unit == null) {
                conversionInputLayout.setError(getString(R.string.select_unit));
                conversionInputLayout.setErrorEnabled(true);

                return;
            }
            conversionInputLayout.setErrorEnabled(false);
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("Conversion", conversionButton.getText().toString());
            intent.putExtra("ConversionValue", conversionEditText.getText().toString());
            intent.putExtra("Unit", unit);
            Log.d(DEBUG_TAG, "onClick: GetConversions Button => Conversions : "+ conversionButton.getText().toString());
            Log.d(DEBUG_TAG, "onClick: GetConversions Button => Conversions Value : "+ conversionEditText.getText().toString());
            Log.d(DEBUG_TAG, "onClick: GetConversions Button => Unit : "+ unit);
            startActivity(intent);

        }

    }

    private void setFavoriteButton( Boolean isFavorite) {
        if (isFavorite) {
            checkButton.setIconResource(R.drawable.heart_filled);
            isConversionButtonFavorite = true;
            favoriteConversions.addFavorite(conversionButton.getText().toString());

        } else {
            checkButton.setIconResource(R.drawable.heart);
            isConversionButtonFavorite = false;
        }

    }
}