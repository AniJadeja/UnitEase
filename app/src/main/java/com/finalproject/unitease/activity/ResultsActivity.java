package com.finalproject.unitease.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.unitease.databinding.ActivityResultsBinding;
import com.finalproject.unitease.model.ConversionModel;
import com.finalproject.unitease.recyclerviewadapter.ResultsRecyclerViewAdapter;
import com.finalproject.unitease.uicomponent.UnitEaseButton;
import com.finalproject.unitease.utils.ConversionConfiguration;
import com.finalproject.unitease.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ResultsActivity extends AppCompatActivity {

    private static final String FLOW_TAG = "Flow - ResultsActivity";
    private static final String DEBUG_TAG = "DebugUnitEase - ResultsActivity";

    UnitEaseButton button;

    int conversionId;

    ImageView conversionResultImage;
    TextView conversionResultText;

    ActivityResultsBinding resultsBinding;
    List<ConversionModel> conversions;

    WearableRecyclerView wearableRecyclerView;
    private static String type, unit, value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflating the layout using view binding
        resultsBinding = ActivityResultsBinding.inflate(getLayoutInflater());
        setContentView(resultsBinding.getRoot());

        // Initialize UI components and retrieve intent data
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if it's a history view
        if (getIntent().getBooleanExtra("isHistory", false)) {
            // Load historical conversions from SharedPreferences
            loadHistoricalConversions();
        } else {
            // Load conversions based on the provided type, unit, and value
            loadConversions();
        }

        // Set up RecyclerView with the loaded conversions
        setupRecyclerView();

        // Save the current conversions to SharedPreferences
        saveCurrentConversionsToSharedPreferences();
    }

    // Load historical conversions from SharedPreferences
    private void loadHistoricalConversions() {
        Set<String> conversionsSet = SharedPrefUtils.getConversions(type, this);

        for (String conversionString : conversionsSet) {
            String[] parts = conversionString.split(":");

            if (parts.length == 2) {
                String option = parts[0];
                String value = parts[1];
                ConversionModel conversion = new ConversionModel(0, option, value);
                conversions.add(conversion);
            } else {
                Log.e("DebugUnitEase", "Invalid format for: " + conversionString);
            }
        }
    }

    // Load conversions based on the provided type, unit, and value
    private void loadConversions() {
        conversions = ConversionConfiguration.getConversions(type, unit, Double.parseDouble(value));
    }

    // Set up the RecyclerView to display conversions
    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        wearableRecyclerView.setHasFixedSize(true);
        wearableRecyclerView.setEdgeItemsCenteringEnabled(false);
        wearableRecyclerView.setLayoutManager(manager);

        // Create and set up the adapter for the RecyclerView
        ResultsRecyclerViewAdapter adapter = new ResultsRecyclerViewAdapter(conversions,
                new UnitEaseButton(UnitEaseButton.getButtonId(type)).getButtonBackgroundColor(),
                this);
        wearableRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // Initialize UI components and set initial values
    private void init() {
        String conversionType = getIntent().getStringExtra("Conversion");
        type = conversionType;
        value = getIntent().getStringExtra("ConversionValue");
        unit = getIntent().getStringExtra("Unit");
        conversionId = UnitEaseButton.getButtonId(conversionType);
        button = new UnitEaseButton(conversionId);
        int primaryColor = button.getButtonBackgroundColor();
        int imageResource = button.getButtonIcon();
        bindViews(conversionType, imageResource, primaryColor);
        conversions = new ArrayList<>();
        wearableRecyclerView = resultsBinding.resultsList;
    }

    // Bind views with conversion-related information
    private void bindViews(String conversion, int imageResource, int color) {
        conversionResultImage = resultsBinding.conversionResultImg;
        conversionResultText = resultsBinding.conversionResultText;
        conversionResultImage.setImageTintList(ColorStateList.valueOf(getColor(color)));
        conversionResultText.setTextColor(getColor(color));
        conversionResultText.setText(conversion);
        conversionResultImage.setImageResource(imageResource);
    }

    // Save the current conversions to SharedPreferences
    private void saveCurrentConversionsToSharedPreferences() {
        Set<String> conversionsSet = new LinkedHashSet<>();
        for (ConversionModel conversion : conversions) {
            conversionsSet.add(conversion.getOption() + ":" + conversion.getValue());
        }

        SharedPrefUtils.saveConversions(type, conversionsSet, this);
    }
}