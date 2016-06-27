package com.trainingsapp.chrisals.trainingsapp;

/**
 * Created by chris.als on 18.05.16.
 */
public class WorkOut {
    private String WorkoutGroup, lastOrNext;
    private String[] WorkMax, WorkName;

    public WorkOut(String[] WorkName, String[] WorkMax, String workoutGroup, String lastOrNext) {
        this.WorkName = WorkName;
        this.WorkMax = WorkMax;
        this.WorkoutGroup = workoutGroup;
        this.lastOrNext = lastOrNext;


    }

    public String getLastOrNext() {
        return lastOrNext;
    }

    public void setLastOrNext(String state) {
        this.lastOrNext = state;
    }

    public String[] getWorkName() {
        return WorkName;
    }

    public void setWorkName(String[] name) {
        this.WorkName = name;
    }

    public String[] getWorkMax() {
        return WorkMax;
    }

    public void setWorkMax(String[] max) {
        this.WorkMax = max;
    }

    public String getWorkoutGroup() {
        return WorkoutGroup;
    }

    public void setWorkoutGroup(String group) {
        this.WorkoutGroup = group;
    }
}



