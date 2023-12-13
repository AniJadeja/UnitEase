package com.finalproject.unitease.recyclerviewadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.unitease.databinding.LayoutAllOptionsRectangularBinding;
import com.finalproject.unitease.databinding.LayoutAllOptionsRoundedBinding;
import com.finalproject.unitease.uicomponent.UnitEaseButton;
import com.google.android.material.button.MaterialButton;

public class ConversionsRecyclerViewAdapter extends RecyclerView.Adapter<ConversionsRecyclerViewAdapter.ViewHolder> {

    // Tags for logging
    private static final String FLOW_TAG = "Flow - ConversionsRecyclerViewAdapter";
    private static final String DEBUG_TAG = "DebugUnitEase - ConversionsRecyclerViewAdapter";

    private final Context context;
    private final Boolean isScreenRound;

    // Callback interface
    public interface ButtonClickListener {
        void onButtonClick(String buttonText);
    }

    // Callback instance
    private ButtonClickListener buttonClickListener;

    // Constructor
    public ConversionsRecyclerViewAdapter(Context context, Boolean isScreenRound, ButtonClickListener buttonClickListener) {
        this.context = context;
        this.isScreenRound = isScreenRound;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the appropriate layout based on isScreenRound
        View itemView;
        if (isScreenRound) {
            LayoutAllOptionsRoundedBinding binding = LayoutAllOptionsRoundedBinding.inflate(inflater, parent, false);
            itemView = binding.getRoot();
            return new ViewHolder(itemView, binding);
        } else {
            LayoutAllOptionsRectangularBinding binding = LayoutAllOptionsRectangularBinding.inflate(inflater, parent, false);
            itemView = binding.getRoot();
            return new ViewHolder(itemView, binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position, isScreenRound);
    }

    @Override
    public int getItemCount() {
        return isScreenRound ? 2 : 1;
    }

    // ViewHolder class for each item in the RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LayoutAllOptionsRoundedBinding roundBinding;
        private LayoutAllOptionsRectangularBinding rectangularBinding;

        // Constructor for the ViewHolder
        public ViewHolder(@NonNull View itemView, LayoutAllOptionsRoundedBinding binding) {
            super(itemView);
            this.roundBinding = binding;
        }

        public ViewHolder(@NonNull View itemView, LayoutAllOptionsRectangularBinding binding) {
            super(itemView);
            this.rectangularBinding = binding;
        }

        // Method to bind the view based on the screen type
        public void bindView(int position, Boolean isScreenRound) {
            if (isScreenRound) {
                bindRoundView(position);
            } else {
                bindRectangularView();
            }
        }

        // Method to configure the button based on the UnitEaseButton
        private void configureButton(UnitEaseButton unitEaseButton, MaterialButton button) {
            button.setText(unitEaseButton.getButtonName());
            button.setBackgroundColor(context.getColor(unitEaseButton.getButtonBackgroundColor()));
            button.setIconResource(unitEaseButton.getButtonIcon());
            button.setOnClickListener(this);
        }

        // Method to bind the rectangular view with buttons
        private void bindRectangularView() {
            for (int i = 0; i <= 5; i++) {
                UnitEaseButton unitEaseButton = new UnitEaseButton(i);
                switch (i) {
                    case 0:
                        configureButton(unitEaseButton, rectangularBinding.topLeftButton);
                        break;
                    case 1:
                        configureButton(unitEaseButton, rectangularBinding.topCenterButton);
                        break;
                    case 2:
                        configureButton(unitEaseButton, rectangularBinding.topRightButton);
                        break;
                    case 3:
                        configureButton(unitEaseButton, rectangularBinding.bottomLeftButton);
                        break;
                    case 4:
                        configureButton(unitEaseButton, rectangularBinding.bottomCenterButton);
                        break;
                    case 5:
                        configureButton(unitEaseButton, rectangularBinding.bottomRightButton);
                        break;
                }
            }
        }

        // Method to bind the round view with buttons
        private void bindRoundView(int position) {
            int lastButton = position * 3;
            configureButton(new UnitEaseButton(lastButton), roundBinding.button0);
            configureButton(new UnitEaseButton(lastButton + 1), roundBinding.button1);
            configureButton(new UnitEaseButton(lastButton + 2), roundBinding.button2);

            roundBinding.button0.setIconSize(30);
            roundBinding.button1.setIconSize(30);
            roundBinding.button2.setIconSize(30);

            Log.d(DEBUG_TAG, "bindRoundView: round view bound ");

            if (position == 1) {
                Log.d(FLOW_TAG, "bindRoundView: round view bound ");
            }
        }

        // Click listener for button clicks
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();
            // callback should happen here
            buttonClickListener.onButtonClick(buttonText);
        }
    }
}