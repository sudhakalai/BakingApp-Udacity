package com.example.android.bakingapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.Adapters.MainAdapter;
import com.example.android.bakingapp.Database.RecipeContract;
import com.example.android.bakingapp.Database.RecipeDbHelper;
import com.example.android.bakingapp.Model.Ingredient;
import com.example.android.bakingapp.Model.Recipe;
import com.example.android.bakingapp.Model.Step;
import com.example.android.bakingapp.Utils.RecipeNetworkUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterOnClickHandler {

    RecyclerView recyclerView;
    Context context = this;
    MainAdapter mainAdapter;
    ArrayList<Recipe> mRecipes;
    private MainAdapter.MainAdapterOnClickHandler clickHandler;
    String stringUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    RecipeDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_main);

        clickHandler = this;

        mDbHelper = new RecipeDbHelper(this);
        mDbHelper.getWritableDatabase();

        GridLayoutManager layoutManager;

        boolean isPhone = getResources().getBoolean(R.bool.is_phone);

        if (isPhone) {
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                layoutManager= new GridLayoutManager(context, 1);
            }
            else{
                layoutManager= new GridLayoutManager(context, 2);
            }
        } else {

            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                layoutManager= new GridLayoutManager(context, 2);
            }
            else{
                layoutManager= new GridLayoutManager(context, 3);
            }
        }
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        URL jsonUrl = RecipeNetworkUtils.buildUrl(stringUrl);

        if(isNetworkConnected()){
            new MainRecipeAsyncTask().execute(jsonUrl);
        }

        new RecipeListAsyncTask().execute();

        mainAdapter = new MainAdapter(mRecipes, clickHandler);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("RecipeName", recipe.getRecipeName());
        intent.putExtra("Serving",recipe.getServing());
        intent.putExtra("Image",recipe.getImage());
        intent.putParcelableArrayListExtra("IngredientList", recipe.getIngredients());
        intent.putParcelableArrayListExtra("StepList", recipe.getSteps());
        startActivity(intent);
    }



    public class MainRecipeAsyncTask extends AsyncTask<URL, Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(URL... urls) {

            int rowsDeleted = getContentResolver().delete(RecipeContract.RecipeEntry.CONTENT_URI, null, null);

            URL jsonUrl = urls[0];
            try {
                String json = RecipeNetworkUtils.getResponseFromHttpUrl(jsonUrl);
                ArrayList<Recipe> recipesFromNetwork = new ArrayList<>();
               recipesFromNetwork = RecipeNetworkUtils.getRecipeDetailsFromJson(json);
                ContentValues[] contentValues;
                ArrayList<ContentValues> cv_ArrayList = new ArrayList<>();
                for(int i =0; i<recipesFromNetwork.size(); i++){
                    String recipeName = recipesFromNetwork.get(i).getRecipeName();
                    String serving = recipesFromNetwork.get(i).getServing();
                    String image = recipesFromNetwork.get(i).getImage();

                    ArrayList<Ingredient> ingredients = recipesFromNetwork.get(i).getIngredients();
                    Gson gson = new Gson();
                    String ingredientList= gson.toJson(ingredients);


                    ArrayList<Step> steps = recipesFromNetwork.get(i).getSteps();
                    Gson gson1 = new Gson();
                    String stepList= gson1.toJson(steps);

                    ContentValues values = new ContentValues();
                    values.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME, recipeName);
                    values.put(RecipeContract.RecipeEntry.COLUMN_SERVING, serving);
                    values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE, image);
                    values.put(RecipeContract.RecipeEntry.COLUMN_INGREDIENTS, ingredientList);
                    values.put(RecipeContract.RecipeEntry.COLUMN_STEPS, stepList);
                    cv_ArrayList.add(values);
                }
                contentValues = new ContentValues[cv_ArrayList.size()];
                cv_ArrayList.toArray(contentValues);
                getContentResolver().bulkInsert(RecipeContract.RecipeEntry.CONTENT_URI, contentValues);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    public class RecipeListAsyncTask extends AsyncTask<Void, Void,ArrayList<Recipe>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Recipe> doInBackground(Void... voids) {
            ArrayList<Recipe> recipes = new ArrayList<>();
            Cursor cursor = getContentResolver().query(RecipeContract.RecipeEntry.CONTENT_URI,null, null, null, null);
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++){
                String recipeName = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_RECIPE_NAME));
                String serving =cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_SERVING));
                String image = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IMAGE));

                String ingredientString = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_INGREDIENTS));
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Ingredient>>() {}.getType();
                ArrayList<Ingredient> ingredients = gson.fromJson(ingredientString, type);

                String stepString = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_STEPS));
                Gson gson1 = new Gson();
                Type type1 = new TypeToken<ArrayList<Step>>() {}.getType();
                ArrayList<Step> steps = gson1.fromJson(stepString, type1);
                recipes.add(new Recipe(recipeName, serving, ingredients,steps, image));
                cursor.moveToNext();

           }

           cursor.close();

            return recipes;
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            super.onPostExecute(recipes);
            mainAdapter.setRecipeList(recipes);

        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
