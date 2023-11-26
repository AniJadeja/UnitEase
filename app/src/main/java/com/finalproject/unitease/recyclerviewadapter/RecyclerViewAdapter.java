package com.finalproject.unitease.recyclerviewadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.unitease.databinding.LayoutAllOptionsBinding;
import com.finalproject.unitease.databinding.LayoutAllOptionsRectangularBinding;
import com.finalproject.unitease.model.ButtonModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String FLOW_TAG = "Flow - RecyclerViewAdapter";
    private static final String DEBUG_TAG = "DebugUnitEase - RecyclerViewAdapter";
    private final Context context;
    private final Boolean isScreenRound;

    public RecyclerViewAdapter(Context context, Boolean isScreenRound) {
        this.context = context;
        this.isScreenRound = isScreenRound;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the appropriate layout based on isScreenRound
        View itemView;
        if (isScreenRound) {
            LayoutAllOptionsBinding binding = LayoutAllOptionsBinding.inflate(inflater, parent, false);
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private LayoutAllOptionsBinding roundBinding;
        private LayoutAllOptionsRectangularBinding rectangularBinding;

        public ViewHolder(@NonNull View itemView, LayoutAllOptionsBinding binding) {
            super(itemView);
            this.roundBinding = binding;
        }

        public ViewHolder(@NonNull View itemView, LayoutAllOptionsRectangularBinding binding) {
            super(itemView);
            this.rectangularBinding = binding;
        }

        public void bindView(int position, Boolean isScreenRound) {

            if (isScreenRound) {
                bindRoundView(position);
            } else {
                bindRectangularView();
            }


        }

        private void bindRectangularView() {
            for (int i = 0; i<=5; i++)
            {
                ButtonModel button = new ButtonModel(i);
                switch (i)
                {
                    case 0:
                        rectangularBinding.topLeftButton.setText(button.getButtonName());
                        rectangularBinding.topLeftButton.setBackgroundColor(context.getColor(button.getButtonBackgroundColor()));
                        rectangularBinding.topLeftButton.setIconResource(button.getButtonIcon());
                        break;
                    case 1:
                        rectangularBinding.topCenterButton.setText(button.getButtonName());
                        rectangularBinding.topCenterButton.setBackgroundColor(context.getColor(button.getButtonBackgroundColor()));
                        rectangularBinding.topCenterButton.setIconResource(button.getButtonIcon());
                        break;
                    case 2:
                        rectangularBinding.topRightButton.setText(button.getButtonName());
                        rectangularBinding.topRightButton.setBackgroundColor(context.getColor(button.getButtonBackgroundColor()));
                        rectangularBinding.topRightButton.setIconResource(button.getButtonIcon());
                        break;
                    case 3:
                        rectangularBinding.bottomLeftButton.setText(button.getButtonName());
                        rectangularBinding.bottomLeftButton.setBackgroundColor(context.getColor(button.getButtonBackgroundColor()));
                        rectangularBinding.bottomLeftButton.setIconResource(button.getButtonIcon());
                        break;
                    case 4:
                        rectangularBinding.bottomCenterButton.setText(button.getButtonName());
                        rectangularBinding.bottomCenterButton.setBackgroundColor(context.getColor(button.getButtonBackgroundColor()));
                        rectangularBinding.bottomCenterButton.setIconResource(button.getButtonIcon());
                        break;
                    case 5:
                        rectangularBinding.bottomRightButton.setText(button.getButtonName());
                        rectangularBinding.bottomRightButton.setBackgroundColor(context.getColor(button.getButtonBackgroundColor()));
                        rectangularBinding.bottomRightButton.setIconResource(button.getButtonIcon());
                        break;
                }
            }
            Log.d(FLOW_TAG, "bindRectangularView: Rectangular view bound ");
        }

        private void bindRoundView(int position) {
            int lastButton = position * 3;
            ButtonModel leftButton = new ButtonModel(lastButton);
            ButtonModel centerButton = new ButtonModel(lastButton+1);
            ButtonModel rightButton = new ButtonModel(lastButton+2);

            roundBinding.leftButton.setText(leftButton.getButtonName());
            roundBinding.centerButton.setText(centerButton.getButtonName());
            roundBinding.rightButton.setText(rightButton.getButtonName());

            roundBinding.leftButton.setIconSize(30);
            roundBinding.centerButton.setIconSize(30);
            roundBinding.rightButton.setIconSize(30);

            roundBinding.leftButton.setBackgroundColor(context.getColor(leftButton.getButtonBackgroundColor()));
            roundBinding.centerButton.setBackgroundColor(context.getColor(centerButton.getButtonBackgroundColor()));
            roundBinding.rightButton.setBackgroundColor(context.getColor(rightButton.getButtonBackgroundColor()));

            roundBinding.leftButton.setIconResource(leftButton.getButtonIcon());
            roundBinding.centerButton.setIconResource(centerButton.getButtonIcon());
            roundBinding.rightButton.setIconResource(rightButton.getButtonIcon());
            if (position == 1){
                Log.d(FLOW_TAG, "bindRoundView: round view bound ");
            }

        }
    }
}
