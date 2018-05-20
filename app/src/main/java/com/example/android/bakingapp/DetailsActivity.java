package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.android.bakingapp.Model.Ingredient;
import com.example.android.bakingapp.Model.Recipe;
import com.example.android.bakingapp.Model.Step;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    String recipeName;
    String serving;
    ArrayList<Ingredient> ingredients;
    ArrayList<Step> steps;
    Recipe mRecipe;
    String image;
    public static boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ScrollView scrollView = findViewById(R.id.sc_details_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) {

        Intent intent = getIntent();
        if(intent!= null){

            if(intent.hasExtra("RecipeName")){
                recipeName = intent.getStringExtra("RecipeName");
                setTitle(recipeName);
            }

            if(intent.hasExtra("Serving")){
                serving = intent.getStringExtra("Serving");
            }

            if(intent.hasExtra("Image")){
                image = intent.getStringExtra("Image");
            }

            if(intent.hasExtra("IngredientList")){
                ingredients = intent.getParcelableArrayListExtra("IngredientList");
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setIngredientsList(ingredients);
                FragmentManager fragmentManagerIngredients = getSupportFragmentManager();
                fragmentManagerIngredients.beginTransaction()
                        .add(R.id.ingredients_container, ingredientsFragment)
                        .commit();

            }
            if(intent.hasExtra("StepList")){
                steps = intent.getParcelableArrayListExtra("StepList");
                StepsFragment stepsFragment = new StepsFragment();
                stepsFragment.setStepsList(steps);
                FragmentManager fragmentManagerSteps = getSupportFragmentManager();
                fragmentManagerSteps.beginTransaction()
                        .add(R.id.steps_container, stepsFragment)
                        .commit();
            }

            mRecipe = new Recipe(recipeName, serving, ingredients, steps,image);
            }

            if(findViewById(R.id.tablet_procedure_layout) != null){
            mTwoPane = true;
                ProcedureFragment procedureFragment = new ProcedureFragment();
                procedureFragment.setSteps(steps);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.procedure_container, procedureFragment)
                        .commit();
            }

        }
    }

    public static Boolean getIsTwoPane(){
        return mTwoPane;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.widget_recipe, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_to_widget) {
            Toast.makeText(getBaseContext(), getResources().getString(R.string.recipe_widget), Toast.LENGTH_SHORT).show();
            RecipeWidgetService.updateWidget(this, mRecipe);
            return true;
        } else if(item.getItemId() == android.R.id.home){

            NavUtils.navigateUpFromSameTask(this);
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }
}
