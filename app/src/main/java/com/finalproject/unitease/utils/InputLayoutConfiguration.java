package com.finalproject.unitease.utils;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.appcompat.content.res.AppCompatResources;

import com.finalproject.unitease.R;

public class InputLayoutConfiguration {
    public static void setStrokeColor(Drawable drawable, int focusedColor, int unfocusedColor){
        Drawable strokeStateList = drawable;

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

        assert strokeStateList != null;
        strokeStateList = strokeStateList.mutate();  // This is important to make sure the Drawable is not shared
        strokeStateList.setTintList(colorStateList);

    }
}
