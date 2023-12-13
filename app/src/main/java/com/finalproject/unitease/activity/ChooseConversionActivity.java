package com.finalproject.unitease.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.wear.widget.WearableRecyclerView;

import com.finalproject.unitease.databinding.ActivityChooseConversionRectangularBinding;
import com.finalproject.unitease.databinding.ActivityChooseConversionRoundedBinding;
import com.finalproject.unitease.model.FavoriteConversions;
import com.finalproject.unitease.model.FavoritesModel;
import com.finalproject.unitease.recyclerviewadapter.ConversionsRecyclerViewAdapter;
import com.finalproject.unitease.uicomponent.UnitEaseButton;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ChooseConversionActivity extends AppCompatActivity implements View.OnClickListener, ConversionsRecyclerViewAdapter.ButtonClickListener {
    private static final String FLOW_TAG = "Flow - ChooseConversionActivity";
    private static final String DEBUG_TAG = "DebugUnitEase - ChooseConversionActivity";
    private static boolean isScrolled = false;

    // UI components
    private WearableRecyclerView recyclerView;
    private ImageView scrollButton;
    private MaterialButton customCategoryButton1, customCategoryButton2;

    // RecyclerView components
    private LinearLayoutManager layoutManager;
    private LinearSmoothScroller linearSmoothScroller;

    // Flags to determine screen type and history state
    private Boolean isScreenRound, isHistory;

    // Data components
    FavoriteConversions conversions;
    List<FavoritesModel> favoriteConversionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Installing SplashScreen
        SplashScreen.installSplashScreen(this);

        // Configuring the screen based on its type (round or rectangular)
        configureScreen();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Setting up the RecyclerView's LayoutManager
        setupLayoutManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Setting up the RecyclerView and updating favorite conversions
        setUpRecyclerView();
        updateFavoriteConversions();
    }

    @Override
    public void onClick(View v) {
        if (v == scrollButton) {
            /*
             * handle the recycler view scrolling state and button animation
             */
            if (isScrolled) {
                ObjectAnimator.ofFloat(scrollButton, View.ROTATION, 0f, 180f).setDuration(300).start();
                linearSmoothScroller.setTargetPosition(0);
            } else {
                ObjectAnimator.ofFloat(scrollButton, View.ROTATION, 180f, 360f).setDuration(300).start();
                linearSmoothScroller.setTargetPosition(1);
            }
            isScrolled = !isScrolled;
            layoutManager.startSmoothScroll(linearSmoothScroller);
        } else if (v == customCategoryButton1) {
            startActivity(new Intent(this, ConversionOptionsActivity.class).putExtra("Conversion", customCategoryButton1.getText()));
        } else if ( v == customCategoryButton2) {
            startActivity(new Intent(this, ConversionOptionsActivity.class).putExtra("Conversion", customCategoryButton2.getText()));
        }
    }

    // configure the screen based on the screen type
    private void configureScreen() {

        isScreenRound = this.getResources().getConfiguration().isScreenRound();
        conversions = new FavoriteConversions(this);
        if (isScreenRound) configureRoundScreen();
        else configureRectangularScreen();
        updateFavoriteConversions();
        isHistory = getIntent().getBooleanExtra("isHistory", false);


        if (!isHistory) {
            customCategoryButton1.setVisibility(View.VISIBLE);
            customCategoryButton2.setVisibility(View.VISIBLE);
            customCategoryButton1.setOnClickListener(this);
            customCategoryButton2.setOnClickListener(this);
        }
        else {
            customCategoryButton1.setVisibility(View.GONE);
            customCategoryButton2.setVisibility(View.GONE);
        }

    }

    // set up the RecyclerView
    // Set up the RecyclerView
    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        ConversionsRecyclerViewAdapter adapter = new ConversionsRecyclerViewAdapter(getApplicationContext(), isScreenRound, this);
        recyclerView.setAdapter(adapter);
        recyclerView.suppressLayout(true);
        // Set initial rotation and scrolling state for the scroll button
        scrollButton.setRotation(180f);
        isScrolled = false;
    }

    // Set up the LayoutManager for the RecyclerView and the LinearSmoothScroller for scrolling
    private void setupLayoutManager() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return (float) 300 / displayMetrics.densityDpi;
            }
        };
    }

    // Configure the screen layout for round screens
    private void configureRoundScreen() {
        // Inflate the round screen layout
        ActivityChooseConversionRoundedBinding binding = ActivityChooseConversionRoundedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UI components
        recyclerView = binding.rvChooseConversation;
        scrollButton = binding.scrollButton;
        customCategoryButton1 = binding.customCategoryButton1;
        customCategoryButton2 = binding.customCategoryButton2;

        // Set click listener for the scroll button
        binding.scrollButton.setOnClickListener(this);
    }

    // Configure the screen layout for rectangular screens
    private void configureRectangularScreen() {
        // Inflate the rectangular screen layout
        ActivityChooseConversionRectangularBinding bindingRectangular = ActivityChooseConversionRectangularBinding.inflate(getLayoutInflater());
        setContentView(bindingRectangular.getRoot());

        // Initialize UI components
        recyclerView = bindingRectangular.rvChooseConversation;
        customCategoryButton1 = bindingRectangular.customCategoryButton1;
        customCategoryButton2 = bindingRectangular.customCategoryButton2;
    }


    @Override
    public void onButtonClick(String buttonText) {
        Log.d(DEBUG_TAG, "onButtonClick: clicked on button " + buttonText);
        if (isHistory){
            startActivity(new Intent(this, ResultsActivity.class).putExtra("Conversion", buttonText).putExtra("isHistory", true));
        }
        else
            startActivity(new Intent(this, ConversionOptionsActivity.class).putExtra("Conversion", buttonText));

    }
    // Update the favorite conversions on the custom category buttons
    private void updateFavoriteConversions() {
        setFavoriteConversions(customCategoryButton1, customCategoryButton2);
    }

    // Set the favorite conversions on the custom category buttons
    private void setFavoriteConversions(MaterialButton button1, MaterialButton button2) {
        Log.d(DEBUG_TAG, "setFavoriteConversions: getting favorite conversions");
        favoriteConversionsList = conversions.getFavorites();
        Log.d(DEBUG_TAG, "setFavoriteConversions: favorite conversions size is " + favoriteConversionsList.size());
        if (favoriteConversionsList.size() > 0) {
            String button1Text = favoriteConversionsList.get(0).getButtonName();
            int conversionId = UnitEaseButton.getButtonId(button1Text);
            UnitEaseButton button = new UnitEaseButton(conversionId);
            button1.setText(button1Text);
            button1.setIconResource(button.getButtonIcon());
            if (favoriteConversionsList.size() > 1) {
                String button2Text = favoriteConversionsList.get(1).getButtonName();
                conversionId = UnitEaseButton.getButtonId(button2Text);
                button = new UnitEaseButton(conversionId);
                button2.setText(button2Text);
                button2.setIconResource(button.getButtonIcon());
            }
        }
    }
}
