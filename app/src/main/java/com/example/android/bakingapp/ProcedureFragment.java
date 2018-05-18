package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.Adapters.StepViewPagerAdapter;
import com.example.android.bakingapp.Model.Step;

import java.util.ArrayList;


public class ProcedureFragment extends Fragment {

    ArrayList<Step> mSteps = new ArrayList<>();
    private static final String ARG_PARAM = "Steps";

    public ProcedureFragment() {
        // Required empty public constructor
    }

    public static ProcedureFragment newInstance(ArrayList<Step> param) {
        ProcedureFragment fragment = new ProcedureFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSteps = getArguments().getParcelableArrayList(ARG_PARAM);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_procedure, container, false);

        if(savedInstanceState != null){
            mSteps = savedInstanceState.getParcelableArrayList("stepsList");
        }

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        StepViewPagerAdapter adapter = new StepViewPagerAdapter(getContext(), getFragmentManager(), mSteps);

        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(Integer.parseInt(mId));

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        viewPager.setSaveFromParentEnabled(false);

        tabLayout.setupWithViewPager(viewPager);


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("stepsList", mSteps);

    }

    public void setSteps(ArrayList<Step> steps){
        mSteps = steps;
    }
}
