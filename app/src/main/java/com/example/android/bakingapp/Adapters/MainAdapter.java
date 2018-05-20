package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.Model.Recipe;
import com.example.android.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * The MainAdapter populates the recipe cards in the MainActivity
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private static ArrayList<Recipe> mRecipes;
    private static MainAdapterOnClickHandler mOnClickHandler;

    public interface MainAdapterOnClickHandler{
        void onClick(Recipe recipe);
    }

    public MainAdapter(ArrayList<Recipe> recipes, MainAdapterOnClickHandler clickHandler){
        mRecipes = recipes;
        mOnClickHandler = clickHandler;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_grid_item, parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        holder.recipe = mRecipes.get(position);
        String recipeName = holder.recipe.getRecipeName();
        String serving = holder.recipe.getServing();
        String image = holder.recipe.getImage();

        holder.bind(recipeName, serving,image);

    }

    @Override
    public int getItemCount() {
        if(mRecipes != null){
            return mRecipes.size();
        }else {
            return 0;
        }
    }

    public void setRecipeList(ArrayList<Recipe> list){
        this.mRecipes = list;
        notifyDataSetChanged();
    }


    public static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        TextView recipeNameTv;
        TextView servingTv;
        ImageView recipeIv;
        Recipe recipe;

        public MainViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            recipeNameTv = itemView.findViewById(R.id.iv_recipe_name);
            servingTv = itemView.findViewById(R.id.tv_serving);
            recipeIv = itemView.findViewById(R.id.main_activity_iv);
            itemView.setOnClickListener(this);
        }

        public void bind(String recipeName, String serving, String image){
            recipeNameTv.setText(recipeName);
            String servingText = context.getResources().getString(R.string.serving)+ serving;
            servingTv.setText(servingText);

            if(image != null && !image.isEmpty()){
                Picasso.with(context)
                        .load(image)
                        .into(recipeIv);
            }

        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Recipe clickedRecipe = mRecipes.get(adapterPosition);
            mOnClickHandler.onClick(clickedRecipe);
        }
    }
}
