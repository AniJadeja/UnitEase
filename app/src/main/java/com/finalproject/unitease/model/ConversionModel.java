package com.finalproject.unitease.model;

import java.util.Set;

public class ConversionModel {

    int id;
    String option;
    String value;

    public ConversionModel(int id, String option, String value){
        this.id = id;
        this.option = option;
        this.value = value;
    }

    public int getId(){
        return this.id;
    }

    public String getOption(){
        return this.option;
    }

    public String getValue(){
        return this.value;
    }


}
