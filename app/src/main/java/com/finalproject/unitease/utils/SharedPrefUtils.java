package com.finalproject.unitease.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.finalproject.unitease.model.FavoritesModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


//  Utility class for managing shared preferences related to favorites and conversions.

public class SharedPrefUtils {

    // Key for the shared preferences containing favorites
    private static final String FAVORITES = "FAVORITES";

    // Saves a list of favorite conversion models to shared preferences.

    public static void saveFavorites(List<FavoritesModel> favorites, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        // Iterate through the list of favorites and save them to shared preferences
        for (FavoritesModel model : favorites) {
            editor.putString(model.getId(), model.getButtonName());
            editor.putBoolean("LAST_RIGHT_" + model.getId(), model.isLastRight());
        }
        editor.apply();
    }

    // Retrieves a list of favorite conversion models from shared preferences.

    public static List<FavoritesModel> getFavorites(String type, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        List<FavoritesModel> taskList = new ArrayList<>();

        Map<String, ?> map = preferences.getAll();

        Set set = map.entrySet();
        Iterator iterator = set.iterator();

        // Iterate through the shared preferences and create a list of favorites
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String id = entry.getKey().toString();

            if (entry.getValue() instanceof String) {
                String savedTask = (String) entry.getValue();

                boolean isLastRight = preferences.getBoolean("LAST_RIGHT_" + id, false);

                FavoritesModel model = new FavoritesModel(id, savedTask, isLastRight);
                taskList.add(model);
            }
        }
        return taskList;
    }


     // Saves a set of conversion items to shared preferences.

    public static void saveConversions(String type, Set<String> set, Context context){
        SharedPreferences preferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(type, set);
        editor.apply();
    }


     //Retrieves a set of conversion items from shared preferences.

    public static Set<String> getConversions(String type, Context context){
        Log.d("DebugUnitEase", "getConversions: " + type);

        SharedPreferences preferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        Set<String> savedSet = preferences.getStringSet(type, Collections.emptySet());
        return savedSet;
    }
}