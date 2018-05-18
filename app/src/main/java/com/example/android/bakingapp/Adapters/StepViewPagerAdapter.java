package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.bakingapp.Model.Step;
import com.example.android.bakingapp.ProcedureDetailsFragment;

import java.util.ArrayList;

/**
 * This adapter displays the selected ProcedureDetailsFragment on the viewpager.
 */

public class StepViewPagerAdapter extends FragmentPagerAdapter {
    //Global variable declaration
    private Context mContext;
    private ArrayList<Step> mSteps;

    //Constructor declaration
    public StepViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Constructor declaration
    public StepViewPagerAdapter(Context context, FragmentManager fm, ArrayList<Step> steps) {
        super(fm);
        mContext = context;
        mSteps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        return ProcedureDetailsFragment.newInstance(mSteps.get(position).getDesc(), mSteps.get(position).getUrl(), mSteps.get(position).mStepId);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  mSteps.get(position).getStepId();
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }

}