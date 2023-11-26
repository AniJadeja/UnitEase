package com.finalproject.unitease.activity;

import android.animation.ObjectAnimator;
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

import com.finalproject.unitease.databinding.ActivityChooseConversionBinding;
import com.finalproject.unitease.databinding.ActivityChooseConversionRectangularBinding;
import com.finalproject.unitease.recyclerviewadapter.RecyclerViewAdapter;

public class ChooseConversionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FLOW_TAG = "Flow - ChooseConversionActivity";
    private static final String DEBUG_TAG = "DebugUnitEase - ChooseConversionActivity";
    private static boolean isScrolled = false;

    private WearableRecyclerView recyclerView;
    private ImageView scrollButton;
    private LinearLayoutManager layoutManager;
    private LinearSmoothScroller linearSmoothScroller;
    private Boolean isScreenRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        isScreenRound = this.getResources().getConfiguration().isScreenRound();
        if (isScreenRound) configureRoundScreen();
        else configureRectangularScreen();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupLayoutManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpRecyclerView();
    }

    // ----------------------------------------- Overridden Methods ----------------------------------------- //

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
        }
    }

    // ----------------------------------------- Defined Methods ----------------------------------------- //

    // set up the RecyclerView
    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(), isScreenRound);
        recyclerView.setAdapter(adapter);
        recyclerView.suppressLayout(true);
        Log.d(FLOW_TAG, "setUpRecyclerView: RecyclerView set up");
    }

    // set up the LayoutManager for the RecyclerView and the LinearSmoothScroller for scrolling
    private void setupLayoutManager() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return (float) 300 / displayMetrics.densityDpi;
            }
        };
        Log.d(FLOW_TAG, "setupLayoutManager: layoutManager and linearSmoothScroller set up");
    }

    // configure the screen for round screens
    private void configureRoundScreen() {
        ActivityChooseConversionBinding binding = ActivityChooseConversionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.rvChooseConversation;
        scrollButton = binding.scrollButton;
        binding.scrollButton.setOnClickListener(this);
        Log.d(FLOW_TAG, "configureRoundScreen: round screen configured");
    }

    // configure the screen for rectangular screens
    private void configureRectangularScreen() {
        ActivityChooseConversionRectangularBinding bindingRectangular = ActivityChooseConversionRectangularBinding.inflate(getLayoutInflater());
        setContentView(bindingRectangular.getRoot());
        recyclerView = bindingRectangular.rvChooseConversation;
        Log.d(FLOW_TAG, "configureRectangularScreen: rectangular screen configured");
    }


}
