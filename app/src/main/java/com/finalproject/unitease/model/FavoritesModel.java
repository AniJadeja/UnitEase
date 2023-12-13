package com.finalproject.unitease.model;

public class FavoritesModel {

    // Model attributes
    String id;           // Identifier for the favorites model
    String buttonName;   // Name of the favorite button
    boolean isLastRight; // Flag to indicate if the last favorite button pressed was on the right

    // Constructor to initialize the FavoritesModel
    public FavoritesModel(String id, String buttonName, boolean isLastRight) {
        this.id = id;
        this.buttonName = buttonName;
        this.isLastRight = isLastRight;
    }

    // Getter method to retrieve the ID of the favorites model
    public String getId() {
        return this.id;
    }

    // Getter method to retrieve the name of the favorite button
    public String getButtonName() {
        return this.buttonName;
    }

    // Setter method to update the ID of the favorites model
    public void setId(String id) {
        this.id = id;
    }

    // Setter method to update the name of the favorite button
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    // Getter method to check if the last favorite button pressed was on the right
    public boolean isLastRight() {
        return isLastRight;
    }

    // Setter method to update the flag indicating if the last favorite button pressed was on the right
    public void setLastRight(boolean lastRight) {
        isLastRight = lastRight;
    }
}