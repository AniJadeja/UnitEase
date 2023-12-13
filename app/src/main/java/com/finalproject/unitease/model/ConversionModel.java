package com.finalproject.unitease.model;

import java.util.Set;

public class ConversionModel {

    // Model attributes
    int id;         // Unique identifier for the conversion model
    String option;  // Conversion option or type
    String value;   // Value associated with the conversion option

    // Constructor to initialize the ConversionModel
    public ConversionModel(int id, String option, String value){
        this.id = id;
        this.option = option;
        this.value = value;
    }

    // Getter method to retrieve the ID of the conversion model
    public int getId(){
        return this.id;
    }

    // Getter method to retrieve the conversion option
    public String getOption(){
        return this.option;
    }

    // Getter method to retrieve the value associated with the conversion option
    public String getValue(){
        return this.value;
    }
}
