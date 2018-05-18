package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.Model.Step;

import java.util.ArrayList;

public class ProcedureDetailsActivity extends AppCompatActivity {

    ArrayList<Step> mSteps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_details);

        if(savedInstanceState == null) {

            Intent intent = getIntent();

            if(intent.hasExtra("StepsList")){
                mSteps = intent.getParcelableArrayListExtra("StepsList");
            }


            ProcedureFragment procedureFragment = new ProcedureFragment();
            procedureFragment.setSteps(mSteps);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.procedure_container, procedureFragment)
                    .commit();
        }


    }


}
