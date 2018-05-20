package com.example.android.bakingapp.Utils;

import android.util.Log;

import com.example.android.bakingapp.Model.Ingredient;
import com.example.android.bakingapp.Model.Recipe;
import com.example.android.bakingapp.Model.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Kalaimaran on 13-May-18.
 */

public class RecipeNetworkUtils {


    public static URL buildUrl (String stringUrl){


        URL jsonUrl = null;
        try {
            jsonUrl = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("utils", jsonUrl.toString());
        return jsonUrl;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        // Checks if the Url is null
        if(url == null){
            return null;
        }
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<Recipe> getRecipeDetailsFromJson(String json){

        ArrayList<Recipe> recipes = new ArrayList<>();



            if(json == null){
                return null;
            }
        JSONArray recipesArray = null;
        try {
            recipesArray = new JSONArray(json);

            for(int i=0; i< recipesArray.length(); i++){
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                ArrayList<Step> steps = new ArrayList<>();

                try {
                    JSONObject recipeObject = recipesArray.getJSONObject(i);
                    String recipeName = recipeObject.getString("name");
                    String serving = recipeObject.getString("servings");
                    String image = recipeObject.getString("image");
                    JSONArray ingredientsArray = recipeObject.getJSONArray("ingredients");
                    Log.v("ingrearraySize", String.valueOf(ingredientsArray.length()));
                    for (int j=0; j< ingredientsArray.length(); j++){
                        JSONObject ingredientObject = ingredientsArray.getJSONObject(j);
                        String ingredientName = ingredientObject.getString("ingredient");
                        String quantity =ingredientObject.getString("quantity");
                        String measure = ingredientObject.getString("measure");
                        ingredients.add(new Ingredient(ingredientName, quantity, measure));
                    }
                    JSONArray stepsArray = recipeObject.getJSONArray("steps");
                    for (int k=0; k< stepsArray.length(); k++){
                        JSONObject stepObject = stepsArray.getJSONObject(k);
                        String ingredientId = stepObject.getString("id");
                        String shortDesc = stepObject.getString("shortDescription");
                        String desc = stepObject.getString("description");
                        String url = stepObject.getString("videoURL");
                        String thumbnailUrl = stepObject.getString("thumbnailURL");
                        steps.add(new Step(ingredientId, shortDesc, desc, url, thumbnailUrl));
                    }
                    recipes.add(new Recipe(recipeName, serving, ingredients, steps,image));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }


}
