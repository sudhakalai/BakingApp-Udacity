package com.example.android.bakingapp.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This is the class object for a recipe.
 */

public class Recipe {

    private String mRecipeName;
    private String mServing;
    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;
    private String mImage;

    public Recipe(String recipeName, String serving, ArrayList<Ingredient> ingredients, ArrayList<Step> steps,String image ){
        mRecipeName = recipeName;
        mServing = serving;
        mIngredients = ingredients;
        mSteps = steps;
        mImage = image;
    }

    public String getRecipeName(){ return mRecipeName; }
    public String getServing() { return mServing; }
    public ArrayList<Ingredient> getIngredients(){ return mIngredients;}
    public ArrayList<Step> getSteps(){ return mSteps;}
    public String getImage(){ return mImage; }

    public static String toBaseString(Recipe recipe) {
        Gson gson = new Gson();
        return gson.toJson(recipe);
    }

    public static Recipe fromBaseString(String encoded) {
        if (!"".equals(encoded)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Recipe>() {}.getType();
           return gson.fromJson(encoded, type);

        }
        return null;
    }

}
