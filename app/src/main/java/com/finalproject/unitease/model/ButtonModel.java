package com.finalproject.unitease.model;
import com.finalproject.unitease.R;

public class ButtonModel {

    private final String buttonName;
    private final int buttonIcon;
    private final int buttonBackgroundColor;

    // button names to be displayed
    private static final String[] BUTTONS = {
            "Length",
            "Weight",
            "Volume",
            "Speed",
            "Area",
            "Temp"
    };

    // related icons for the buttons
    private static final int[] BUTTON_ICONS = {
            R.drawable.length_logo,
            R.drawable.weight_logo,
            R.drawable.volume_logo,
            R.drawable.speed_logo,
            R.drawable.area_logo,
            R.drawable.temp_logo
    };

    // related background colors for the buttons
    private static final int[] BUTTON_BACKGROUND_COLOR = {
            R.color.green_500,
            R.color.orange_500,
            R.color.teal_500,
            R.color.orange_200,
            R.color.pink_500,
            R.color.blue_500,
    };
   public ButtonModel(int id) {
        this.buttonName = BUTTONS[id];
        this.buttonIcon = BUTTON_ICONS[id];
        this.buttonBackgroundColor = BUTTON_BACKGROUND_COLOR[id];
   }

    public String getButtonName() {
        return buttonName;
    }

    public int getButtonIcon() {
        return buttonIcon;
    }

    public int getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

}
