package com.finalproject.unitease.uicomponent;

import com.finalproject.unitease.R;

public class UnitEaseButton {

    // Name of the button
    private final String buttonName;

    // Icon resource ID for the button
    private final int buttonIcon;

    // Background color resource ID for the button
    private final int buttonBackgroundColor;

    // Background tint color resource ID for the button
    private final int buttonBackgroundTint;

    // Highlight color resource ID for the button
    private final int buttonHighlightColor;

    // Names to be displayed on the buttons
    private static final String[] BUTTONS = {"Length", "Weight", "Volume", "Speed", "Area", "Temp"};

    // Icons for the buttons
    private static final int[] BUTTON_ICONS = {R.drawable.length_logo, R.drawable.weight_logo, R.drawable.volume_logo, R.drawable.speed_logo, R.drawable.area_logo, R.drawable.temp_logo};

    // Background colors for the buttons
    private static final int[] BUTTON_BACKGROUND_COLOR = {R.color.green_500, R.color.orange_500, R.color.teal_500, R.color.orange_200, R.color.pink_500, R.color.blue_500};

    // Background tint colors for the buttons
    private static final int[] BUTTON_BACKGROUND_TINT = {R.color.green_500_alpha_10, R.color.orange_500_alpha_10, R.color.teal_500_alpha_10, R.color.orange_200_alpha_10, R.color.pink_500_alpha_10, R.color.blue_500_alpha_10};

    // Highlight colors for the buttons
    private static final int[] BUTTON_HIGHLIGHT_COLOR = {R.color.green_500_alpha_80, R.color.orange_500_alpha_80, R.color.teal_500_alpha_80, R.color.orange_200_alpha_80, R.color.pink_500_alpha_80, R.color.blue_500_alpha_80};

    // Constructor to initialize the button properties based on the provided ID
    public UnitEaseButton(int id) {
        this.buttonName = BUTTONS[id];
        this.buttonIcon = BUTTON_ICONS[id];
        this.buttonBackgroundColor = BUTTON_BACKGROUND_COLOR[id];
        this.buttonBackgroundTint = BUTTON_BACKGROUND_TINT[id];
        this.buttonHighlightColor = BUTTON_HIGHLIGHT_COLOR[id];
    }

    // Getter methods for button properties
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

    // Static method to get the button ID based on the provided button name
    public static int getButtonId(String buttonName) {
        for (int i = 0; i < BUTTONS.length; i++) {
            if (BUTTONS[i].equals(buttonName)) {
                return i;
            }
        }
        return -1;
    }
}