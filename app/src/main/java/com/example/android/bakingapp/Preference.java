package com.example.android.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.bakingapp.Model.Recipe;

/**
 * Created by Sudha on 17-May-18.
 */

public class Preference {

    public static final String PREFS_NAME = "prefs";

    public static void saveRecipe(Context context, Recipe recipe) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.putString(context.getString(R.string.widget_recipe_key), Recipe.toBaseString(recipe));
        Log.v("recipeString", Recipe.toBaseString(recipe));
        prefs.apply();
    }

    public static Recipe loadRecipe(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String recipeBase64 = prefs.getString(context.getString(R.string.widget_recipe_key), "");

        return "".equals(recipeBase64) ? null : Recipe.fromBaseString(prefs.getString(context.getString(R.string.widget_recipe_key), ""));
    }
}
