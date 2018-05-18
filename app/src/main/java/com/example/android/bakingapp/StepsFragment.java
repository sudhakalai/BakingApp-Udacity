package com.example.android.bakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.Adapters.StepAdapter;
import com.example.android.bakingapp.Model.Step;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler {


    private ArrayList<Step> mSteps;
    private StepAdapter stepAdapter;
    private boolean isTwoPane = true;


    public StepsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mSteps = savedInstanceState.getParcelableArrayList("stepsList");
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        isTwoPane = DetailsActivity.getIsTwoPane();
        Log.v("testValue", String.valueOf(isTwoPane));


        RecyclerView stepRecyclerView = rootView.findViewById(R.id.rv_steps);
        LinearLayoutManager layoutManagerStep = new LinearLayoutManager(getContext());
        stepRecyclerView.setLayoutManager(layoutManagerStep);
        stepRecyclerView.setHasFixedSize(true);
        stepAdapter = new StepAdapter(mSteps, this);
        stepRecyclerView.setAdapter(stepAdapter);


        return rootView;
    }



    public void setStepsList(ArrayList<Step> steps){
        Log.v("insideFragment", String.valueOf(steps.size()));
        mSteps = steps;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("stepsList", mSteps);
    }

    @Override
    public void onClick(Step step) {

        if(isTwoPane){
            ProcedureFragment.getViewPager().setCurrentItem(Integer.parseInt(step.getStepId()));
        }else {
            Intent intent = new Intent(getContext(), ProcedureDetailsActivity.class);
            intent.putExtra("stepDesc", step);
            intent.putParcelableArrayListExtra("StepsList", mSteps);
            startActivity(intent);
        }

    }
}
