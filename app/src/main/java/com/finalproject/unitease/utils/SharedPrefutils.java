package com.finalproject.unitease.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.finalproject.unitease.model.FavoritesModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SharedPrefutils {

    private static final String FAVORITES = "FAVORITES";

    public static void saveFavorites(FavoritesModel favoritesModel, Context context){
        SharedPreferences preferences = context.getSharedPreferences(FAVORITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(favoritesModel.getId(), favoritesModel.getButtonName());
        editor.apply();
    }


    public static List<FavoritesModel> getFavorites(String type, Context context){
        SharedPreferences preferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        List<FavoritesModel> taskList = new ArrayList<>();

        Map<String, ?> map = preferences.getAll();

        Set set = map.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String savedTask = (String) entry.getValue();
            if (savedTask != null) {
                FavoritesModel model = new FavoritesModel(entry.getKey().toString(), savedTask);
                taskList.add(model);
            }
        }
        return taskList;
    }

    public static void saveConversions(String type, Set<String> set, Context context){
        SharedPreferences preferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(type, set);
        editor.apply();
    }

    public static Set<String> getConversions(String type, Context context){
        Log.d("DebugUnitEase", "getConversions: " + type);
        SharedPreferences preferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
        return preferences.getStringSet(type, null);
    }
}
