package com.trainingsapp.chrisals.trainingsapp;

/**
 * Created by chris.als on 18.05.16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class WorkOutAdapter extends RecyclerView.Adapter<WorkOutAdapter.MyViewHolder> {

    private List<WorkOut> WorkOutList;

    public WorkOutAdapter(List<WorkOut> workOutList) {
        this.WorkOutList = workOutList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_list_row, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WorkOut workOut = WorkOutList.get(position);

        String[] maxes = workOut.getWorkMax();
        String[] exes = workOut.getWorkName();

        holder.WloN.setText(workOut.getLastOrNext());

        holder.Wgroup.setText(workOut.getWorkoutGroup());

        holder.Wex1.setText(exes[0]);
        holder.Wex2.setText(exes[1]);
        holder.Wex3.setText(exes[2]);


        holder.Wmax1.setText(maxes[0]);
        holder.Wmax2.setText(maxes[1]);
        holder.Wmax3.setText(maxes[2]);


    }

    @Override
    public int getItemCount() {
        return WorkOutList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView WloN, Wex1, Wex2, Wex3, Wmax1, Wmax2, Wmax3, Wgroup;

        public MyViewHolder(View view) {
            super(view);

            WloN = (TextView) view.findViewById(R.id.lastOrNext);

            Wgroup = (TextView) view.findViewById(R.id.WorkoutName);

            Wex1 = (TextView) view.findViewById(R.id.exercise1);
            Wex2 = (TextView) view.findViewById(R.id.exercise2);
            Wex3 = (TextView) view.findViewById(R.id.exercise3);

            Wmax1 = (TextView) view.findViewById(R.id.exercise1Max);
            Wmax2 = (TextView) view.findViewById(R.id.exercise2Max);
            Wmax3 = (TextView) view.findViewById(R.id.exercise3Max);


        }

    }


}
