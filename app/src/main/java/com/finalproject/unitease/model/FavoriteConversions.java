package com.finalproject.unitease.model;

import android.content.Context;
import android.util.Log;

import com.finalproject.unitease.utils.SharedPrefutils;

import java.util.List;

public class FavoriteConversions {
     private List<FavoritesModel> favorites;
     private final Context context;


     public FavoriteConversions( Context context){
         favorites = SharedPrefutils.getFavorites("FAVORITES", context);;
         this.context = context;
     }

    public List<FavoritesModel> getFavorites(){
         favorites = SharedPrefutils.getFavorites("FAVORITES", context);
        return favorites;
    }

    public List<FavoritesModel> addFavorite(String favorite){
         Log.d("DebugUnitEase - Favorites Model : ","Adding " +favorite);
        String temp = this.favorites.get(0).buttonName;
        if (favorites.size() == 2 && !isFavorite(favorite)){
            Log.d("DebugUnitEase - Favorites Model : ","Favorites size is 2 and " + favorite + " is not a favorite");
            favorites.clear();
        } else if (isFavorite(favorite)) {
            Log.d("DebugUnitEase - Favorites Model : ","Favorites size is 2 and " + favorite + " is a favorite");
            return null;
        }
        FavoritesModel model = new FavoritesModel("0", favorite);
        Log.d("DebugUnitEase - Favorites Model : Adding ","Model created for " + favorite);
        favorites.add(model);
        Log.d("DebugUnitEase - Favorites Model : Adding ","Model added for " + favorite);
        model = new FavoritesModel(String.valueOf(favorites.size()),temp);
        Log.d("DebugUnitEase - Favorites Model : Adding ","Model created for " + temp);
        favorites.add(model);
        Log.d("DebugUnitEase - Favorites Model : Adding ","Model added for " + temp);
        SharedPrefutils.saveFavorites(model, context);
        SharedPrefutils.saveFavorites(favorites.get(0), context);
        return favorites;
    }

    public boolean isFavorite(String favorite){
        for (FavoritesModel model: favorites){
            if (model.buttonName.equals(favorite)){
                return true;
            }
        }
        return false;
    }


}
