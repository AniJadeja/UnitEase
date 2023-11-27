package com.finalproject.unitease.utils;

import java.util.ArrayList;
import java.util.List;

public class ConversionConfiguration {
    private List<String[]> options;
    private int id;

    public  ConversionConfiguration(int id){
        this.options = new ArrayList<>();
        this.options.add(LENGTH_UNITS);
        this.options.add(WEIGHT_UNITS);
        this.options.add(VOLUME_UNITS);
        this.options.add(SPEED_UNITS);
        this.options.add(AREA_UNITS);
        this.options.add(TEMP_UNITS);
        this.id = id;
    }


    private static final String[] LENGTH_UNITS = {
            "Centimeters",
            "Inches",
            "Feet",
            "Meters",
            "Kilometers",
            "Miles"
    };

    private static final String[] WEIGHT_UNITS = {
            "Grams",
            "Ounces",
            "Pounds",
            "Kilograms",
            "Tons"
    };

    private static final String[] VOLUME_UNITS = {
            "Milliliters",
            "Fluid Ounces",
            "Liters",
            "Gallons"
    };

    private static final String[] SPEED_UNITS = {
            "Kilometers per Hour",
            "Miles per Hour"
    };

    private static final String[] AREA_UNITS = {
            "Square Feet",
            "Square Meters",
            "Square Kilometers",
            "Hectares",
            "Acres"
    };

    private static final String[] TEMP_UNITS = {
            "Celsius",
            "Fahrenheit"
    };


    public String[] getOptions(){
        return options.get(id);
    }


}
