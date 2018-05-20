package com.example.android.bakingapp.Database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract class
 */

public class RecipeContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.bakingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "recipe";

    public static final class RecipeEntry implements BaseColumns{
        public static final String TABLE_NAME = "recipes";
        public static final String COLUMN_RECIPE_NAME = "recipename";
        public static final String COLUMN_SERVING = "serving";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_INGREDIENTS = "ingredients";
        public static final String COLUMN_STEPS = "steps";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MOVIE);

    }
}
