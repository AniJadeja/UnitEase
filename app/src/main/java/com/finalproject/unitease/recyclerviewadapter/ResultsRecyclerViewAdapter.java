package com.finalproject.unitease.recyclerviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.unitease.databinding.LayoutResultsBinding;
import com.finalproject.unitease.model.ConversionModel;

import java.util.List;

public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder> {

    // List of ConversionModel objects
    private List<ConversionModel> conversions;

    // Context to be used for resource access
    private Context context;

    // Color for styling
    private int color;

    // Constructor
    public ResultsRecyclerViewAdapter(List<ConversionModel> conversions, int color, Context context) {
        this.conversions = conversions;
        this.context = context;
        this.color = color;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the layout for each item in the RecyclerView
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutResultsBinding binding = LayoutResultsBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Binding data to the ViewHolder
        holder.bindView(conversions.get(position));
    }

    @Override
    public int getItemCount() {
        // Returning the total number of items in the RecyclerView
        return conversions.size();
    }

    // ViewHolder class for each item in the RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Binding object for the layout
        LayoutResultsBinding binding;

        // Constructor for the ViewHolder
        public ViewHolder(@NonNull LayoutResultsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Method to bind data to the views in the ViewHolder
        public void bindView(ConversionModel conversions) {
            binding.hypen.setTextColor(context.getColor(color));
            binding.option.setTextColor(context.getColor(color));
            binding.value.setTextColor(context.getColor(color));
            binding.option.setText(conversions.getOption());
            binding.value.setText(conversions.getValue());
        }
    }
}