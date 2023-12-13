package com.finalproject.unitease.model;
import android.content.Context;
import android.util.Log;
import com.finalproject.unitease.utils.SharedPrefUtils;
import java.util.List;

public class FavoriteConversions {

    // List to store favorite conversion models
    private List<FavoritesModel> favorites;

    // Context for accessing application resources and preferences
    private final Context context;

    // Flag to track the position of the last favorite button pressed
    boolean isRight = false;

    // Constructor to initialize the FavoriteConversions instance
    public FavoriteConversions(Context context) {
        // Retrieve the list of favorite conversions from SharedPreferences
        favorites = SharedPrefUtils.getFavorites("FAVORITES", context);
        // Initialize the context
        this.context = context;
    }

    // Getter method to retrieve the list of favorite conversion models
    public List<FavoritesModel> getFavorites() {
        // Refresh the list of favorites from SharedPreferences
        favorites = SharedPrefUtils.getFavorites("FAVORITES", context);
        return favorites;
    }

    // Method to add a favorite conversion to the list
    public List<FavoritesModel> addFavorite(String favorite) {

        // Create a new FavoritesModel
        FavoritesModel model;

        // Check if the list is empty
        if (favorites.isEmpty()) {
            // Create a model with position 0 and set it as the last favorite pressed
            model = new FavoritesModel("0", favorite, false);
            Log.d("DebugUnitEase - Favorites Model : Adding ", "Model created for " + favorite);
            favorites.add(model);
            Log.d("DebugUnitEase - Favorites Model : Adding ", "Model added for " + favorite);
            // Save the updated list to SharedPreferences
            SharedPrefUtils.saveFavorites(favorites, context);
        }
        // Check if the list has only one element and the current favorite is not already present
        else if (favorites.size() == 1 && !isFavorite(favorite)) {
            // Create a model with position 1 and set it as the last favorite pressed
            model = new FavoritesModel("1", favorite, true);
            Log.d("DebugUnitEase - Favorites Model : Adding ", "Model created for " + favorite);
            favorites.add(model);
            Log.d("DebugUnitEase - Favorites Model : Adding ", "Model added for " + favorite);
            // Save the updated list to SharedPreferences
            SharedPrefUtils.saveFavorites(favorites, context);
        }
        // Check if the list has two elements and the current favorite is not already present
        else if (favorites.size() == 2 && !isFavorite(favorite)) {
            // Check the position of the last favorite pressed
            if (favorites.get(1).isLastRight()) {
                // If the last favorite was on the right, update the model at position 0
                favorites.get(1).setLastRight(false);
                model = new FavoritesModel("0", favorite, false);
                favorites.set(0, model);
            } else {
                // If the last favorite was on the left, update the model at position 1
                model = new FavoritesModel("1", favorite, true);
                favorites.set(1, model);
            }
            // Save the updated list to SharedPreferences
            SharedPrefUtils.saveFavorites(favorites, context);
        }

        return favorites;
    }

    // Method to check if a conversion is already a favorite
    public boolean isFavorite(String favorite) {
        // Iterate through the list of favorites and check if the buttonName matches the given favorite
        for (FavoritesModel model : favorites) {
            if (model.buttonName.equals(favorite)) {
                return true;
            }
        }
        return false;
    }
}
