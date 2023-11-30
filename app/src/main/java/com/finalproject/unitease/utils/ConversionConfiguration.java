package com.finalproject.unitease.utils;

import android.util.Log;

import com.finalproject.unitease.model.ConversionModel;

import java.util.ArrayList;
import java.util.List;

public class ConversionConfiguration {
    private final List<String[]> options;
    private final int id;
    private static final String DEBUG_TAG = "DebugUnitEase - ConversionConfiguration";
    public ConversionConfiguration(int id) {
        this.options = new ArrayList<>();
        this.options.add(LENGTH_UNITS);
        this.options.add(WEIGHT_UNITS);
        this.options.add(VOLUME_UNITS);
        this.options.add(SPEED_UNITS);
        this.options.add(AREA_UNITS);
        this.options.add(TEMP_UNITS);
        this.id = id;
    }


    private static final String[] LENGTH_UNITS = {"Centimeters", "Inches", "Feet", "Meters", "Kilometers", "Miles"};

    private static final String[] WEIGHT_UNITS = {"Grams", "Ounces", "Pounds", "Kilograms", "Tons"};

    private static final String[] VOLUME_UNITS = {"Milliliters", "Fluid Ounces", "Liters", "Gallons"};

    private static final String[] SPEED_UNITS = {"Kilometers per Hour", "Miles per Hour"};

    private static final String[] AREA_UNITS = {"Square Feet", "Square Meters", "Square Kilometers", "Hectares", "Acres"};

    private static final String[] TEMP_UNITS = {"Celsius", "Fahrenheit"};


    public String[] getOptions() {
        return options.get(id);
    }

    public static List<ConversionModel> getConversions(String type, String unit, double value) {
        Log.d(DEBUG_TAG, "getConversions: type " + type + " unit " + unit + " value " + value);
        List<ConversionModel> conversions = new ArrayList<>();
        switch (type.toLowerCase()) {
            case "length": {
                double centimeters = 0;
                double inches = 0;
                double feet = 0;
                double meters = 0;
                double kilometers = 0;
                double miles = 0;
                switch (unit.toLowerCase()) {

                    case "centimeters":
                        centimeters = value;
                        inches = roundUpToFiveDecimals(convertCentimetersToInches(value));
                        feet = roundUpToFiveDecimals(convertInchesToFeet(inches));
                        meters = roundUpToFiveDecimals(convertInchesToMeters(inches));
                        kilometers = roundUpToFiveDecimals(convertInchesToKilometers(inches));
                        miles = roundUpToFiveDecimals(convertInchesToMiles(inches));
                        break;
                    case "inches":
                        inches = value;
                        centimeters = roundUpToFiveDecimals(convertInchesToCentimeters(value));
                        feet = roundUpToFiveDecimals(convertInchesToFeet(value));
                        meters = roundUpToFiveDecimals(convertInchesToMeters(value));
                        kilometers = roundUpToFiveDecimals(convertInchesToKilometers(value));
                        miles = roundUpToFiveDecimals(convertInchesToMiles(value));
                        break;
                    case "feet":
                        feet = value;
                        inches = roundUpToFiveDecimals(convertFeetToInches(value));
                        centimeters = roundUpToFiveDecimals(convertInchesToCentimeters(inches));
                        meters = roundUpToFiveDecimals(convertInchesToMeters(inches));
                        kilometers = roundUpToFiveDecimals(convertInchesToKilometers(inches));
                        miles = roundUpToFiveDecimals(convertInchesToMiles(inches));
                        break;
                    case "meters":
                        meters = value;
                        inches = roundUpToFiveDecimals(convertMetersToInches(value));
                        centimeters = roundUpToFiveDecimals(convertInchesToCentimeters(inches));
                        feet = roundUpToFiveDecimals(convertInchesToFeet(inches));
                        kilometers = roundUpToFiveDecimals(convertInchesToKilometers(inches));
                        miles = roundUpToFiveDecimals(convertInchesToMiles(inches));
                        break;
                    case "kilometers":
                        kilometers = value;
                        inches = roundUpToFiveDecimals(convertKilometersToInches(value));
                        centimeters = roundUpToFiveDecimals(convertInchesToCentimeters(inches));
                        feet = roundUpToFiveDecimals(convertInchesToFeet(inches));
                        meters = roundUpToFiveDecimals(convertInchesToMeters(inches));
                        miles = roundUpToFiveDecimals(convertInchesToMiles(inches));
                        break;
                    case "miles":
                        miles = value;
                        inches = roundUpToFiveDecimals(convertMilesToInches(value));
                        centimeters = roundUpToFiveDecimals(convertInchesToCentimeters(inches));
                        feet = roundUpToFiveDecimals(convertInchesToFeet(inches));
                        meters = roundUpToFiveDecimals(convertInchesToMeters(inches));
                        kilometers = roundUpToFiveDecimals(convertInchesToKilometers(inches));
                        break;
                    default:
                        return null;
                }

                conversions.add(new ConversionModel(0, "Centimeters", String.valueOf(centimeters)));
                conversions.add(new ConversionModel(1, "Inches", String.valueOf(inches)));
                conversions.add(new ConversionModel(2, "Feet", String.valueOf(feet)));
                conversions.add(new ConversionModel(3, "Meters", String.valueOf(meters)));
                conversions.add(new ConversionModel(4, "Kilometers", String.valueOf(kilometers)));
                conversions.add(new ConversionModel(5, "Miles", String.valueOf(miles)));
                Log.d(DEBUG_TAG, "getConversions: Length conversions added size " + conversions.size());
            }
            break;
            case "weight": {
                // follow length pattern
                // start with grams

                double grams = 0;
                double ounces = 0;
                double pounds = 0;
                double kilograms = 0;
                double tons = 0;
                switch (unit.toLowerCase()) {
                    case "grams":
                        grams = value;
                        ounces = roundUpToFiveDecimals(convertGramsToOunces(value));
                        pounds = roundUpToFiveDecimals(convertGramsToPounds(grams));
                        kilograms = roundUpToFiveDecimals(convertGramsToKilograms(grams));
                        tons = roundUpToFiveDecimals(convertGramsToTons(grams));
                        break;
                    case "ounces":
                        ounces = value;
                        grams = roundUpToFiveDecimals(convertOuncesToGrams(value));
                        pounds = roundUpToFiveDecimals(convertGramsToPounds(grams));
                        kilograms = roundUpToFiveDecimals(convertGramsToKilograms(grams));
                        tons = roundUpToFiveDecimals(convertGramsToTons(grams));
                        break;
                    case "pounds":
                        pounds = value;
                        grams = roundUpToFiveDecimals(convertPoundsToGrams(value));
                        ounces = roundUpToFiveDecimals(convertGramsToOunces(grams));
                        kilograms = roundUpToFiveDecimals(convertGramsToKilograms(grams));
                        tons = roundUpToFiveDecimals(convertGramsToTons(grams));
                        break;

                    case "kilograms":
                        kilograms = value;
                        grams = roundUpToFiveDecimals(convertKilogramsToGrams(value));
                        ounces = roundUpToFiveDecimals(convertGramsToOunces(grams));
                        pounds = roundUpToFiveDecimals(convertGramsToPounds(grams));
                        tons = roundUpToFiveDecimals(convertGramsToTons(grams));
                        break;
                    case "tons":
                        tons = value;
                        grams = roundUpToFiveDecimals(convertTonsToGrams(value));
                        ounces = roundUpToFiveDecimals(convertGramsToOunces(grams));
                        pounds = roundUpToFiveDecimals(convertGramsToPounds(grams));
                        kilograms = roundUpToFiveDecimals(convertGramsToKilograms(grams));
                        break;
                    default:
                        return null;
                }

                conversions.add(new ConversionModel(0, "Grams", String.valueOf(grams)));
                conversions.add(new ConversionModel(1, "Ounces", String.valueOf(ounces)));
                conversions.add(new ConversionModel(2, "Pounds", String.valueOf(pounds)));
                conversions.add(new ConversionModel(3, "Kilograms", String.valueOf(kilograms)));
                conversions.add(new ConversionModel(4, "Tons", String.valueOf(tons)));
                Log.d(DEBUG_TAG, "getConversions: Weight conversions added size " + conversions.size());
            }
            break;
            case "volume": {
                double milliliters = 0;
                double fluidOunces = 0;
                double liters = 0;
                double gallons = 0;

                switch (unit.toLowerCase()) {
                    case "milliliters":
                        milliliters = value;
                        fluidOunces = roundUpToFiveDecimals(convertMillilitersToFluidOunces(value));
                        liters = roundUpToFiveDecimals(convertMillilitersToLiters(value));
                        gallons = roundUpToFiveDecimals(convertMillilitersToGallons(value));
                        break;
                    case "fluid ounces":
                        fluidOunces = value;
                        milliliters = roundUpToFiveDecimals(convertFluidOuncesToMilliliters(value));
                        liters = roundUpToFiveDecimals(convertMillilitersToLiters(milliliters));
                        gallons = roundUpToFiveDecimals(convertMillilitersToGallons(milliliters));
                        break;
                    case "liters":
                        liters = value;
                        milliliters = roundUpToFiveDecimals(convertLitersToMilliliters(value));
                        fluidOunces = roundUpToFiveDecimals(convertMillilitersToFluidOunces(milliliters));
                        gallons = roundUpToFiveDecimals(convertMillilitersToGallons(milliliters));
                        break;
                    case "gallons":
                        gallons = value;
                        milliliters = roundUpToFiveDecimals(convertGallonsToMilliliters(value));
                        fluidOunces = roundUpToFiveDecimals(convertMillilitersToFluidOunces(milliliters));
                        liters = roundUpToFiveDecimals(convertMillilitersToLiters(milliliters));
                        break;
                    default:
                        return null;
                }
                conversions.add(new ConversionModel(0, "Milliliters", String.valueOf(milliliters)));
                conversions.add(new ConversionModel(1, "Fluid Ounces", String.valueOf(fluidOunces)));
                conversions.add(new ConversionModel(2, "Liters", String.valueOf(liters)));
                conversions.add(new ConversionModel(3, "Gallons", String.valueOf(gallons)));
                Log.d(DEBUG_TAG, "getConversions: Volume conversions added size " + conversions.size());

            }
            break;
            case "speed": {
                double kilometersPerHour = 0;
                double milesPerHour = 0;
                switch (unit.toLowerCase()) {
                    case "kilometers per hour":
                        kilometersPerHour = value;
                        milesPerHour = roundUpToFiveDecimals(convertKilometersPerHourToMilesPerHour(value));
                        break;
                    case "miles per hour":
                        milesPerHour = value;
                        kilometersPerHour = roundUpToFiveDecimals(convertMilesPerHourToKilometersPerHour(value));
                        break;
                    default:
                        return null;
                }
                conversions.add(new ConversionModel(0, "Kilometers per Hour", String.valueOf(kilometersPerHour)));
                conversions.add(new ConversionModel(1, "Miles per Hour", String.valueOf(milesPerHour)));
                Log.d(DEBUG_TAG, "getConversions: Speed conversions added size " + conversions.size());
            }
            break;
            case "area": {
                double squareFeet = 0;
                double squareMeters = 0;
                double squareKilometers = 0;
                double hectares = 0;
                double acres = 0;
                switch (unit.toLowerCase()) {
                    case "square feet":
                        squareFeet = value;
                        squareMeters = roundUpToFiveDecimals(convertSquareFeetToSquareMeters(value));
                        squareKilometers = roundUpToFiveDecimals(convertSquareFeetToSquareKilometers(value));
                        hectares = roundUpToFiveDecimals(convertSquareFeetToHectares(value));
                        acres = roundUpToFiveDecimals(convertSquareFeetToAcres(value));
                        break;
                    case "square meters":
                        squareMeters = value;
                        squareFeet = roundUpToFiveDecimals(convertSquareMetersToSquareFeet(value));
                        squareKilometers = roundUpToFiveDecimals(convertSquareFeetToSquareKilometers(squareFeet));
                        hectares = roundUpToFiveDecimals(convertSquareFeetToHectares(squareFeet));
                        acres = roundUpToFiveDecimals(convertSquareFeetToAcres(squareFeet));
                        break;
                    case "square kilometers":
                        squareKilometers = value;
                        squareFeet = roundUpToFiveDecimals(convertSquareKilometersToSquareFeet(value));
                        squareMeters = roundUpToFiveDecimals(convertSquareFeetToSquareMeters(squareFeet));
                        hectares = roundUpToFiveDecimals(convertSquareFeetToHectares(squareFeet));
                        acres = roundUpToFiveDecimals(convertSquareFeetToAcres(squareFeet));
                        break;
                    case "hectares":
                        hectares = value;
                        squareFeet = roundUpToFiveDecimals(convertHectaresToSquareFeet(value));
                        squareMeters = roundUpToFiveDecimals(convertSquareFeetToSquareMeters(squareFeet));
                        squareKilometers = roundUpToFiveDecimals(convertSquareFeetToSquareKilometers(squareFeet));
                        acres = roundUpToFiveDecimals(convertSquareFeetToAcres(squareFeet));
                        break;
                    case "acres":
                        acres = value;
                        squareFeet = roundUpToFiveDecimals(convertAcresToSquareFeet(value));
                        squareMeters = roundUpToFiveDecimals(convertSquareFeetToSquareMeters(squareFeet));
                        squareKilometers = roundUpToFiveDecimals(convertSquareFeetToSquareKilometers(squareFeet));
                        hectares = roundUpToFiveDecimals(convertSquareFeetToHectares(squareFeet));
                        break;
                    default:
                        return null;
                }
                conversions.add(new ConversionModel(0, "Sq. Feet", String.valueOf(squareFeet)));
                conversions.add(new ConversionModel(1, "Sq. Meters", String.valueOf(squareMeters)));
                conversions.add(new ConversionModel(2, "Sq. Kilometers", String.valueOf(squareKilometers)));
                conversions.add(new ConversionModel(3, "Hectares", String.valueOf(hectares)));
                conversions.add(new ConversionModel(4, "Acres", String.valueOf(acres)));
                Log.d(DEBUG_TAG, "getConversions: Area conversions added size " + conversions.size());

            }
            break;
            case "temp": {
                double celsius = 0;
                double fahrenheit = 0;
                switch (unit.toLowerCase()) {
                    case "celsius":
                        celsius = value;
                        fahrenheit = roundUpToFiveDecimals((celsius * 9 / 5) + 32);
                        break;
                    case "fahrenheit":
                        fahrenheit = value;
                        celsius = roundUpToFiveDecimals((fahrenheit - 32) * 5 / 9);
                        break;
                    default:
                        return null;
                }
                conversions.add(new ConversionModel(0, "Celsius", String.valueOf(celsius)));
                conversions.add(new ConversionModel(1, "Fahrenheit", String.valueOf(fahrenheit)));
                Log.d(DEBUG_TAG, "getConversions: Temp conversions added size " + conversions.size());
            }
            break;
        }

        return conversions;
    }

    private static double convertAcresToSquareFeet(double value) {
        return value * 43560;
    }

    private static double convertHectaresToSquareFeet(double value) {
        return value * 107639;
    }

    private static double convertSquareKilometersToSquareFeet(double value) {
        return value * 10763910.4;
    }

    private static double convertSquareMetersToSquareFeet(double value) {
        return value * 10.7639;
    }

    private static double convertSquareFeetToAcres(double value) {
        return value * 0.0000229568;
    }

    private static double convertSquareFeetToHectares(double value) {
        return value * 0.0000092903;
    }

    private static double convertSquareFeetToSquareKilometers(double value) {
        return value * 0.000000092903;
    }

    private static double convertSquareFeetToSquareMeters(double value) {
        return value * 0.092903;
    }

    private static double convertMilesPerHourToKilometersPerHour(double value) {
        return value * 1.60934;
    }

    private static double convertKilometersPerHourToMilesPerHour(double value) {
        return value * 0.621371;
    }

    private static double convertGallonsToMilliliters(double value) {
        return value * 3785.41;
    }

    private static double convertLitersToMilliliters(double value) {
        return value * 1000;
    }

    private static double convertFluidOuncesToMilliliters(double value) {
        return value * 29.5735;
    }

    private static double convertMillilitersToGallons(double value) {
        return value * 0.000264172;
    }

    private static double convertMillilitersToLiters(double value) {
        return value * 0.001;
    }

    private static double convertMillilitersToFluidOunces(double value) {
        return value * 0.033814;
    }

    private static double convertTonsToGrams(double value) {
        return value * 907185;
    }

    private static double convertKilogramsToGrams(double value) {
        return value * 1000;
    }

    private static double convertPoundsToGrams(double value) {
        return value * 453.592;
    }

    private static double convertOuncesToGrams(double value) {
        return value * 28.3495;
    }

    private static double convertGramsToTons(double grams) {
        return grams * 0.00000110231;
    }

    private static double convertGramsToKilograms(double grams) {
        return grams * 0.001;
    }

    private static double convertGramsToPounds(double grams) {
        return grams * 0.00220462;
    }

    private static double convertGramsToOunces(double value) {
        return value * 0.035274;
    }

    private static double convertMilesToInches(double value) {
        return value * 63360;
    }

    private static double convertKilometersToInches(double value) {
        return value * 39370.1;
    }

    private static double convertMetersToInches(double value) {
        return value * 39.3701;
    }

    private static double convertFeetToInches(double value) {
        return value * 12;
    }

    private static double convertCentimetersToInches(double value) {
        return value * 0.393701;
    }

    private static double convertInchesToCentimeters(double value) {
        return value * 2.54;
    }

    private static double convertInchesToFeet(double value) {
        return value * 0.0833333;
    }

    private static double convertInchesToMeters(double value) {
        return value * 0.0254;
    }

    private static double convertInchesToKilometers(double value) {
        return value * 0.0000254;
    }

    private static double convertInchesToMiles(double value) {
        return value * 0.0000157828;
    }

    private static double roundUpToFiveDecimals(double value) {
        return Math.round(value * 100000.0) / 100000.0;
    }

}
