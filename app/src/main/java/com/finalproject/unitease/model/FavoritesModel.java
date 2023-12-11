package com.finalproject.unitease.model;

public class FavoritesModel {
    String id;
    String buttonName;

    boolean isLastRight;


    public FavoritesModel(String id, String buttonName, boolean isLastRight){
        this.id = id;
        this.buttonName = buttonName;
        this.isLastRight = isLastRight;
    }

    public String getId(){
        return this.id;
    }

    public String getButtonName(){
        return this.buttonName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
    public boolean isLastRight() {
        return isLastRight;
    }

    public void setLastRight(boolean lastRight) {
        isLastRight = lastRight;
    }

}
