package com.finalproject.unitease.recyclerviewadapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.unitease.R;
import com.finalproject.unitease.databinding.LayoutConversionOptionsBinding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OptionsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Tags for logging
    private static final String FLOW_TAG = "Flow - ConversionsOptionsRecyclerViewAdapter";
    private static final String DEBUG_TAG = "DebugUnitEase - ConversionsOptionsRecyclerViewAdapter";

    private final Context context;

    // Callback interface
    public interface OnOptionSelectedListener {
        void onOptionSelected(String buttonText);
    }

    // Callback instance
    private OnOptionSelectedListener onOptionSelectedListener;
    private List<String> options;
    private List<MaterialButton> buttons;
    private int primaryColor, secondaryColor;

    // Constructor
    public OptionsRecyclerViewAdapter(Context context, int primaryColor, int secondaryColor, List<String> options, OnOptionSelectedListener onOptionSelectedListener) {
        this.context = context;
        this.onOptionSelectedListener = onOptionSelectedListener;
        this.options = options;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        buttons = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutConversionOptionsBinding binding = LayoutConversionOptionsBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindViews(position, primaryColor, secondaryColor);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    // ViewHolder class for each item in the RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final LayoutConversionOptionsBinding binding;
        private MaterialButton selectedButton;

        // Constructor for the ViewHolder
        public ViewHolder(@NonNull LayoutConversionOptionsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Method to bind views for each option in the RecyclerView
        public void bindViews(int position, int primaryColor, int secondaryColor) {
            Log.d(DEBUG_TAG, "bindViews: secondary color " + secondaryColor);
            MaterialButton button = (MaterialButton) binding.btnConversionOption;
            button.setText(options.get(position));
            button.setTextColor(context.getColor(primaryColor));
            button.setStrokeColor(ColorStateList.valueOf(context.getColor(primaryColor)));
            button.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.black_700)));
            button.setOnClickListener(this);
            buttons.add(button);
        }

        // Click listener for option clicks
        @Override
        public void onClick(View v) {
            for (MaterialButton button : buttons) {
                button.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.black_700)));
            }
            MaterialButton button = (MaterialButton) v;
            String buttonText = button.getText().toString();
            button.setBackgroundTintList(ColorStateList.valueOf(context.getColor(secondaryColor)));
            selectedButton = button;
            onOptionSelectedListener.onOptionSelected(buttonText);
        }
    }
}