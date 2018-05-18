package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.Model.Ingredient;
import com.example.android.bakingapp.R;

import java.util.ArrayList;

/**
 * This is the adapter to display ingredients
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>{

    ArrayList<Ingredient> mIngredients;

    public IngredientsAdapter(ArrayList<Ingredient> ingredients){
        mIngredients = ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_list_item, parent,false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {

        holder.ingredient = mIngredients.get(position);
        String ingredientName = holder.ingredient.getIngredientName();
        String quantity = holder.ingredient.getQuantity();
        String measure = holder.ingredient.getMeasure();

        String ingredientDesc = "- " + ingredientName +" ("+ quantity + " " + measure + " )";
        holder.bind(ingredientDesc);
    }

    @Override
    public int getItemCount() {
        if(mIngredients == null){
            return 0;
        }else {
            return mIngredients.size();
        }
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientTv;
        Ingredient ingredient;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ingredientTv = itemView.findViewById(R.id.tv_ingredients);
        }

        public void bind(String ingredient){
            ingredientTv.setText(ingredient);
        }
    }
}
