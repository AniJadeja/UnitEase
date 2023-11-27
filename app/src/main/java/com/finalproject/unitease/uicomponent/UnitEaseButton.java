package com.finalproject.unitease.uicomponent;


import com.finalproject.unitease.R;

public class UnitEaseButton {

    private final String buttonName;
    private final int buttonIcon;
    private final int buttonBackgroundColor, buttonBackgroundTint, buttonHighlightColor;

    // button names to be displayed
    private static final String[] BUTTONS = {"Length", "Weight", "Volume", "Speed", "Area", "Temp"};

    // related icons for the buttons
    private static final int[] BUTTON_ICONS = {R.drawable.length_logo, R.drawable.weight_logo, R.drawable.volume_logo, R.drawable.speed_logo, R.drawable.area_logo, R.drawable.temp_logo};

    // related background colors for the buttons
    private static final int[] BUTTON_BACKGROUND_COLOR = {R.color.green_500, R.color.orange_500, R.color.teal_500, R.color.orange_200, R.color.pink_500, R.color.blue_500,};

    // related background tint colors for the buttons
    private static final int[] BUTTON_BACKGROUND_TINT = {R.color.green_500_alpha_10, R.color.orange_500_alpha_10, R.color.teal_500_alpha_10, R.color.orange_200_alpha_10, R.color.pink_500_alpha_10, R.color.blue_500_alpha_10,};


    // related background tint colors for the buttons
    private static final int[] BUTTON_HIGHLIGHT_COLOR = {R.color.green_500_alpha_80, R.color.orange_500_alpha_80, R.color.teal_500_alpha_80, R.color.orange_200_alpha_80, R.color.pink_500_alpha_80, R.color.blue_500_alpha_80,};


    public UnitEaseButton(int id) {
        this.buttonName = BUTTONS[id];
        this.buttonIcon = BUTTON_ICONS[id];
        this.buttonBackgroundColor = BUTTON_BACKGROUND_COLOR[id];
        this.buttonBackgroundTint = BUTTON_BACKGROUND_TINT[id];
        this.buttonHighlightColor = BUTTON_HIGHLIGHT_COLOR[id];
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

    public int getButtonBackgroundTint() {
        return buttonBackgroundTint;
    }

    public int getButtonHighlightColor() {
        return buttonHighlightColor;
    }

    public static int getButtonId(String buttonName) {

        for (int i = 0; i < BUTTONS.length; i++) {
            if (BUTTONS[i].equals(buttonName)) {
                return i;
            }
        }

        return -1;
    }

}
