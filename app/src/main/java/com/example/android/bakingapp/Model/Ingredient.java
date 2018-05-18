package com.example.android.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is the class object for ingredient.
 */

public class Ingredient implements Parcelable{
    public String mIngredientName;
    public String mQuantity;
    public String mMeasure;

    public Ingredient(String ingredientName, String quantity, String measure){
        mIngredientName = ingredientName;
        mQuantity = quantity;
        mMeasure =  measure;
    }

    protected Ingredient(Parcel in) {
        mIngredientName = in.readString();
        mQuantity = in.readString();
        mMeasure = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getIngredientName() { return mIngredientName; }
    public String getQuantity() {return mQuantity; }
    public String getMeasure() { return mMeasure; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mIngredientName);
        parcel.writeString(mQuantity);
        parcel.writeString(mMeasure);
    }
}
