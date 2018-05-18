package com.example.android.bakingapp;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.Model.Recipe;

/**
 * Created by Sudha on 17-May-18.
 */

public class ListRemoteViews implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private Recipe recipe;

    public ListRemoteViews(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        recipe = Preference.loadRecipe(mContext);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return recipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(mContext.getPackageName(), R.layout.recipe_widget_list_item);

        row.setTextViewText(R.id.ingredient_item_text, recipe.getIngredients().get(position).getIngredientName());

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
