package com.finalproject.unitease.model;

public class FavoritesModel {
    String id;
    String buttonName;

    public FavoritesModel(String id, String buttonName){
        this.id = id;
        this.buttonName = buttonName;
    }

    public String getId(){
        return this.id;
    }

    public String getButtonName(){
        return this.buttonName;
    }

}
