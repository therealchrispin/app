package com.trainingsapp.chrisals.trainingsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public void setLastOrNext(String state) {
        this.lastOrNext = state;
    }

    public void setWorkName(String[] name) {
        this.WorkName = name;
    }

    public void setWorkMax(String[] max) {
        this.WorkMax = max;
    }

    public void setWorkoutGroup(String group) {
        this.WorkoutGroup = group;
    }

    public String getLastOrNext() {
        return lastOrNext;
    }

    public String[] getWorkName() {
        return WorkName;
    }

    public String[] getWorkMax() {
        return WorkMax;
    }

    public String getWorkoutGroup() {
        return WorkoutGroup;
    }
}



