package com.trainingsapp.chrisals.trainingsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * Created by chris.als on 27.04.16.
 */
public final class DBHelper {
    public DBHelper() {
    }

    public static abstract class Datenbank implements BaseColumns {

        public static final String TABLE_NAME = "Trainingsplan";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITEL = "Workout_nummer";

        public static String COLUMN_SQUAT = "Squat";

        public static String COLUMN_BENCH = "Bench_press";

        public static String COLUMN_OHP = "Overhead_press";

        public static String COLUMN_DEADLIFT = "Deadlift";

        public static String COLUMN_WEIGHT = "Weight";
        public static String COLMN_REPS = "Reps";
        public static String COLUMN_SETS = "Sets";

        public static final String TABLE_NAME_2 = "Variablen";

        public static final String TABLE_NAME_3 = "VariablenWO";

        public static String COLUMN_VARNAME = "Var_Name";
        public static String COLUMN_VARVALUE = "Var_Value";

        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final String TEXT_TYPE = " TEXT";

    public static final String CREATE_ENTRY = "CREATE TABLE " +
            Datenbank.TABLE_NAME + " (" +
            Datenbank.COLUMN_ID + " INTEGER PRIMARY KEY" + "," +
            Datenbank.COLUMN_TITEL + TEXT_TYPE + "," +
            Datenbank.COLUMN_SQUAT + TEXT_TYPE + "," +
            Datenbank.COLUMN_OHP + TEXT_TYPE + "," +
            Datenbank.COLUMN_BENCH + TEXT_TYPE + "," +
            Datenbank.COLUMN_DEADLIFT + TEXT_TYPE + " );";


    public static final String CREATE_VAR_ENTRY = " CREATE TABLE " +
            Datenbank.TABLE_NAME_2 + " (" +
            Datenbank.COLUMN_ID + " INTEGER PRIMARY KEY," +
            Datenbank.COLUMN_VARNAME + TEXT_TYPE + "," +
            Datenbank.COLUMN_VARVALUE + " BOOLEAN" + " );";

    public static final String CREATE_VAR_ENTRY_2 = " CREATE TABLE " +
            Datenbank.TABLE_NAME_3 + " (" +
            Datenbank.COLUMN_ID + " INTEGER PRIMARY KEY," +
            Datenbank.COLUMN_VARNAME + TEXT_TYPE + "," +
            Datenbank.COLUMN_VARVALUE + " BOOLEAN" + " );";
}
