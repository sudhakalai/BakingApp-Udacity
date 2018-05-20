package com.example.android.bakingapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.android.bakingapp.Database.RecipeContract.RecipeEntry;

/**
 * Creates a database.
 */

public class RecipeDbHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "recipe.db";
    public static int DATABASE_VERSION = 1;

    public RecipeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " + RecipeEntry.TABLE_NAME + "("
                + RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RecipeEntry.COLUMN_RECIPE_NAME + " TEXT NOT NULL, "
                + RecipeEntry.COLUMN_SERVING + " TEXT, "
                + RecipeEntry.COLUMN_IMAGE + " TEXT, "
                + RecipeEntry.COLUMN_INGREDIENTS + " TEXT, "
                + RecipeEntry.COLUMN_STEPS + " TEXT );";


        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITES_TABLE);

        Log.v("databasequery", SQL_CREATE_FAVORITES_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
