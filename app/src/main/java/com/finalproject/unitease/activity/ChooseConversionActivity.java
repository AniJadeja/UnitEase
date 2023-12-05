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

    private WearableRecyclerView recyclerView;
    private ImageView scrollButton;
    private LinearLayoutManager layoutManager;
    private LinearSmoothScroller linearSmoothScroller;
    private MaterialButton customCategoryButton1, customCategoryButton2;
    private Boolean isScreenRound, isHistory;

    FavoriteConversions conversions;
    List<FavoritesModel> favoriteConversionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //startActivity(new Intent(this, ResultsActivity.class).putExtra("Conversion", "Length"));
        SplashScreen.installSplashScreen(this);
        configureScreen();
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
        updateFavoriteConversions();
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
        } else if (v == customCategoryButton1) {
            startActivity(new Intent(this, ConversionOptionsActivity.class).putExtra("Conversion", customCategoryButton1.getText()));
        } else if ( v == customCategoryButton2) {
            startActivity(new Intent(this, ConversionOptionsActivity.class).putExtra("Conversion", customCategoryButton2.getText()));
        }
    }

    // ----------------------------------------- Defined Methods ----------------------------------------- //

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
    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        ConversionsRecyclerViewAdapter adapter = new ConversionsRecyclerViewAdapter(getApplicationContext(), isScreenRound, this);
        recyclerView.setAdapter(adapter);
        recyclerView.suppressLayout(true);
        Log.d(FLOW_TAG, "setUpRecyclerView: RecyclerView set up");
        scrollButton.setRotation(180f);
        isScrolled = false;
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

        ActivityChooseConversionRoundedBinding binding = ActivityChooseConversionRoundedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.rvChooseConversation;
        scrollButton = binding.scrollButton;
        customCategoryButton1 = binding.customCategoryButton1;
        customCategoryButton2 = binding.customCategoryButton2;
        binding.scrollButton.setOnClickListener(this);
        Log.d(FLOW_TAG, "configureRoundScreen: round screen configured");

    }

    // configure the screen for rectangular screens
    private void configureRectangularScreen() {
        ActivityChooseConversionRectangularBinding bindingRectangular = ActivityChooseConversionRectangularBinding.inflate(getLayoutInflater());
        setContentView(bindingRectangular.getRoot());
        recyclerView = bindingRectangular.rvChooseConversation;
        Log.d(FLOW_TAG, "configureRectangularScreen: rectangular screen configured");
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
    private void updateFavoriteConversions() {
        setFavoriteConversions(customCategoryButton1, customCategoryButton2);
    }
// **************** Debora Changed here
    private void setFavoriteConversions(MaterialButton button1, MaterialButton button2) {
        Log.d(DEBUG_TAG, "setFavoriteConversions: getting favorite conversions");
        favoriteConversionsList = conversions.getFavorites();
        Log.d(DEBUG_TAG, "setFavoriteConversions: favorite conversions size is " + favoriteConversionsList.size());
        if(favoriteConversionsList.size() > 0){
            String button1Text = favoriteConversionsList.get(0).getButtonName();
            int conversionId = UnitEaseButton.getButtonId(button1Text);
            UnitEaseButton button = new UnitEaseButton(conversionId);
            button1.setText(button1Text);
            button1.setIconResource(button.getButtonIcon());
            if(favoriteConversionsList.size() >1 ){
                String button2Text = favoriteConversionsList.get(1).getButtonName();
                conversionId = UnitEaseButton.getButtonId(button2Text);
                button = new UnitEaseButton(conversionId);
                button2.setText(button2Text);
                button2.setIconResource(button.getButtonIcon());
            }

        }
    }
}
