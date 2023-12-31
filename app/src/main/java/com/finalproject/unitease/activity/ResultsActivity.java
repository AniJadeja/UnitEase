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

    // Initializing of variables

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

    // Override methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultsBinding = ActivityResultsBinding.inflate(getLayoutInflater()); // layout inflator with the help of view binding
        setContentView(resultsBinding.getRoot());
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getBooleanExtra("isHistory", false)){
            Set<String> conversionsSet = new LinkedHashSet<>();
            conversionsSet = SharedPrefUtils.getConversions(type,this); // getting the list of saved conversion from shared preference
            // looping the array of conversions
            for (String conversionString : conversionsSet) {
                String[] parts = conversionString.split(":"); // splitting them up with :

                if (parts.length == 2) {
                    String option = parts[0];
                    String value = parts[1];
                    ConversionModel conversion = new ConversionModel( 0,option, value);
                    conversions.add(conversion);// adding the conversion
                } else {
                    Log.e("DebugUnitEase", "Invalid format for: " + conversionString);
                }
            }
        }
        else {
            conversions = ConversionConfiguration.getConversions(type, unit, Double.parseDouble(value));
        }

        Log.d(DEBUG_TAG, "onStart: list received " + conversions.size());
        setupRecyclerView();
        Set<String> conversionsSet = new LinkedHashSet<>();
        for(ConversionModel conversion : conversions) {
            conversionsSet.add(conversion.getOption()+":"+conversion.getValue());
        }

        SharedPrefUtils.saveConversions(type, conversionsSet, this);
    }

    // Setting up the recycler view

    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        wearableRecyclerView.setHasFixedSize(true);
        wearableRecyclerView.setEdgeItemsCenteringEnabled(false);
        wearableRecyclerView.setLayoutManager(manager);
        ResultsRecyclerViewAdapter adapter = new ResultsRecyclerViewAdapter(conversions,
                new UnitEaseButton(UnitEaseButton.getButtonId(type)).getButtonBackgroundColor(),
                this);
        // setting up the adpater
        wearableRecyclerView.setAdapter(adapter);
        // notifying thr adapter about the change
        adapter.notifyDataSetChanged();
    }

    private void init(){
        // assigning the values to the variables
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

    // method for view bindings
    private void bindViews(String conversion, int imageResource, int color){
        conversionResultImage = resultsBinding.conversionResultImg;
        conversionResultText = resultsBinding.conversionResultText;
        conversionResultImage.setImageTintList(ColorStateList.valueOf(getColor(color)));
        conversionResultText.setTextColor(getColor(color));
        conversionResultText.setText(conversion);
        conversionResultImage.setImageResource(imageResource);
    }
}