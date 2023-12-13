package com.finalproject.unitease.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.appcompat.content.res.AppCompatResources;

import com.finalproject.unitease.R;

/**
 * Utility class for configuring input layout appearance.
 */
public class InputLayoutConfiguration {


    public static void setStrokeColor(Drawable drawable, int focusedColor, int unfocusedColor) {
        // Obtain a mutable copy of the provided Drawable
        Drawable strokeStateList = drawable;

        // Define a ColorStateList for different states of the view
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_hovered},
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{}
                },
                new int[]{
                        focusedColor,
                        focusedColor,
                        focusedColor,
                        unfocusedColor
                }
        );

        // Ensure the Drawable is mutable to avoid sharing with other drawables
        assert strokeStateList != null;
        strokeStateList = strokeStateList.mutate();

        // Set the tint list for the Drawable using the defined ColorStateList
        strokeStateList.setTintList(colorStateList);
    }
}
