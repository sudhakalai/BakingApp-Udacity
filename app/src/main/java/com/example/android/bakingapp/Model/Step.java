package com.example.android.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is a class object for step details;
 */

public class Step implements Parcelable {

    public String mStepId;
    public String mShortDesc;
    public String mDesc;
    public String mUrl;

    public Step(String stepId, String shortDesc, String desc, String url){
        mStepId = stepId;
        mShortDesc = shortDesc;
        mDesc = desc;
        mUrl = url;
    }

    protected Step(Parcel in) {
        mStepId = in.readString();
        mShortDesc = in.readString();
        mDesc = in.readString();
        mUrl = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public String getStepId() { return mStepId; }
    public String getShortDesc() { return mShortDesc; }
    public String getDesc() { return mDesc; }
    public String getUrl() { return mUrl; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mStepId);
        parcel.writeString(mShortDesc);
        parcel.writeString(mDesc);
        parcel.writeString(mUrl);
    }
}
