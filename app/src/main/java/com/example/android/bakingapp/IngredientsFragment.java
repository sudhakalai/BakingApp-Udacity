package com.example.android.bakingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.Adapters.IngredientsAdapter;
import com.example.android.bakingapp.Model.Ingredient;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment {

    private ArrayList<Ingredient> mIngredients;
    private IngredientsAdapter ingredientsAdapter;

    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mIngredients = savedInstanceState.getParcelableArrayList("ingredientsList");
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

        RecyclerView ingredientRecyclerView = rootView.findViewById(R.id.rv_ingredients);
        LinearLayoutManager layoutManagerIngredient = new LinearLayoutManager(getContext());
        ingredientRecyclerView.setLayoutManager(layoutManagerIngredient);
        ingredientRecyclerView.setHasFixedSize(true);
        ingredientsAdapter = new IngredientsAdapter(mIngredients);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);

        return rootView;
    }

    public void setIngredientsList(ArrayList<Ingredient> ingredients){
        mIngredients = ingredients;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("ingredientsList", mIngredients);
    }
}
