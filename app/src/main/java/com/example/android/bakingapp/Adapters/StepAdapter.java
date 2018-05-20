package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.Model.Step;
import com.example.android.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This adapter populates the step recyclerview
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

   private ArrayList<Step> mSteps;
   private StepAdapterOnClickHandler mOnClickHandler;

   public interface StepAdapterOnClickHandler{
       void onClick(Step step);
    }

    public StepAdapter(ArrayList<Step> steps, StepAdapterOnClickHandler clickHandler){
        mSteps = steps;
        mOnClickHandler = clickHandler;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.steps_list_item, parent,false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {

        holder.step = mSteps.get(position);
        String stepId = holder.step.getStepId();
        String desc = holder.step.getShortDesc();
        String url = holder.step.getUrl();
        String thumbnail = holder.step.getThumbnail();

        String stepDesc = stepId + ". " + desc;
        holder.bind(stepDesc, url,thumbnail);
    }

    @Override
    public int getItemCount() {
        if(mSteps != null){
            return mSteps.size();
        }else {
            return 0;
        }
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView stepTv;
        ImageView playIv;
        ImageView imageView;
        Step step;
        Context context;

        public StepViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            stepTv = itemView.findViewById(R.id.tv_step_desc);
            playIv = itemView.findViewById(R.id.iv_play_button);
            imageView = itemView.findViewById(R.id.step_iv);
            itemView.setOnClickListener(this);
        }

        public void bind(String stepDesc, String url, String thumbnail){

            stepTv.setText(stepDesc);
            if( !(url != null && !url.isEmpty())){
                playIv.setVisibility(View.INVISIBLE);
            }
            if(thumbnail != null && !thumbnail.isEmpty()){
                Picasso.with(context)
                        .load(thumbnail)
                        .into(imageView);
            }

        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Step clickedStep = mSteps.get(adapterPosition);
            mOnClickHandler.onClick(clickedStep);
        }
    }
}
