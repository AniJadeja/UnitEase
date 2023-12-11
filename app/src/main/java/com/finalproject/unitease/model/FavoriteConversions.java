package com.finalproject.unitease.model;

import android.content.Context;
import android.util.Log;

import com.finalproject.unitease.utils.SharedPrefUtils;

import java.util.List;

public class FavoriteConversions {
     private List<FavoritesModel> favorites;
     private final Context context;

    boolean isRight = false;


     public FavoriteConversions( Context context){
         favorites = SharedPrefUtils.getFavorites("FAVORITES", context);;
         this.context = context;
     }

    public List<FavoritesModel> getFavorites(){
         favorites = SharedPrefUtils.getFavorites("FAVORITES", context);

        return favorites;
    }

    public List<FavoritesModel> addFavorite(String favorite) {

        FavoritesModel model;

        if (favorites.isEmpty()) {
            model = new FavoritesModel("0", favorite, false);
            Log.d("DebugUnitEase - Favorites Model : Adding ","Model created for " + favorite);
            favorites.add(model);
             Log.d("DebugUnitEase - Favorites Model : Adding ","Model added for " + favorite);
            SharedPrefUtils.saveFavorites(favorites, context);
        } else if (favorites.size() == 1 && !isFavorite(favorite)) {
            model = new FavoritesModel("1", favorite, true);
            Log.d("DebugUnitEase - Favorites Model : Adding ","Model created for " + favorite);
            favorites.add(model);
            Log.d("DebugUnitEase - Favorites Model : Adding ","Model added for " + favorite);
            SharedPrefUtils.saveFavorites(favorites, context);
        } else if (favorites.size() == 2 && !isFavorite(favorite)) {
            if (favorites.get(1).isLastRight()){
                favorites.get(1).setLastRight(false);
                model = new FavoritesModel("0", favorite,false);
                favorites.set(0, model);
            }else {
                model = new FavoritesModel("1", favorite,true);
                favorites.set(1, model);
            }
            SharedPrefUtils.saveFavorites(favorites, context);

        }


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
