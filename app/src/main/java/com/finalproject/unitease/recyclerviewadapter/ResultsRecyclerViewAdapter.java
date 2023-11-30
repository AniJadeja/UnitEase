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

public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder>{

    private List<ConversionModel> conversions;
    Context context;
    int color;

    public ResultsRecyclerViewAdapter(List<ConversionModel> conversions, int color, Context context){
        this.conversions = conversions;
        this.context = context;
        this.color = color;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutResultsBinding binding = LayoutResultsBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bindView(conversions.get(position));
    }


    @Override
    public int getItemCount() {
        return conversions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LayoutResultsBinding binding;


        public ViewHolder(@NonNull LayoutResultsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(ConversionModel conversions){
            binding.hypen.setTextColor(context.getColor(color));
            binding.option.setTextColor(context.getColor(color));
            binding.value.setTextColor(context.getColor(color));
            binding.option.setText(conversions.getOption());
            binding.value.setText(conversions.getValue());
        }


    }
}
