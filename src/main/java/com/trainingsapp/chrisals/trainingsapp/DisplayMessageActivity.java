package com.trainingsapp.chrisals.trainingsapp;

import android.animation.Animator;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {
    Double[] extraMessageDoubleArray = new Double[3];
    int index = 0;
    boolean[] exes = new boolean[3];
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test(view);

            }
        });


        startTraining();
    }


    public SQLiteDatabase getDB() {
        DB helfer = new DB(this);
        SQLiteDatabase db = helfer.getWritableDatabase();
        return db;
    }

    public Cursor getCursor() {
        SQLiteDatabase db = getDB();

        String[] projection = {
                DBHelper.Datenbank.COLUMN_TITEL,
                DBHelper.Datenbank.COLUMN_SQUAT,
                DBHelper.Datenbank.COLUMN_BENCH,
                DBHelper.Datenbank.COLUMN_OHP,
                DBHelper.Datenbank.COLUMN_DEADLIFT
        };

        Cursor c = db.query(DBHelper.Datenbank.TABLE_NAME, projection, null, null, null, null, null, null);
        return c;
    }

    public int getAorB() {
        SQLiteDatabase db = getDB();

        String[] projection = {
                DBHelper.Datenbank.COLUMN_VARVALUE,};

        Cursor c = db.query(DBHelper.Datenbank.TABLE_NAME_3, projection, null, null, null, null, null, null);
        c.moveToLast();
        return c.getInt(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_VARVALUE));
    }

    public void setAorB(int aorB) {

        SQLiteDatabase db = getDB();

        ContentValues values = new ContentValues();

        values.put(DBHelper.Datenbank.COLUMN_VARVALUE, String.valueOf(aorB));

        long newRow;
        newRow = db.insert(DBHelper.Datenbank.TABLE_NAME_3, null, values);
    }

    public String[] getMaxes() {
        Cursor c = getCursor();
        c.moveToLast();
        String Workout;

        if (getAorB() == 1) {
            Workout = c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_BENCH));
        } else {
            Workout = c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_OHP));
        }
        String[] Ex = {
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_SQUAT)),
                Workout,
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_DEADLIFT))
        };
        return Ex;
    }

    public String[] getNames() {
        Resources res = getResources();
        String[] array = res.getStringArray(R.array.Exercises);

        String Workout;

        if (getAorB() == 1) {
            Workout = array[1];
        } else {
            Workout = array[2];
        }

        String[] Ex = {
                array[0],
                Workout,
                array[3]
        };
        return Ex;
    }

    public Double[] extraMaxesArray() {
        String[] maxes = getMaxes();
        Double[] Dmaxes = new Double[3];

        Dmaxes[0] = Double.valueOf(maxes[0]);
        Dmaxes[1] = Double.valueOf(maxes[1]);
        Dmaxes[2] = Double.valueOf(maxes[2]);

        return Dmaxes;
    }

    public double nummer() {

        Double[] maxes = extraMaxesArray();

        extraMessageDoubleArray[0] = maxes[0];
        extraMessageDoubleArray[1] = maxes[1];
        extraMessageDoubleArray[2] = maxes[2];

        return extraMessageDoubleArray[index];
    }

    public Double[] getPlates(Double nummer) {

        Double mw;

        if ((nummer - 20) % 2.5 == 0) {
            mw = (nummer - 20) / 2;
        } else {
            mw = 0.0;
        }

        Double[] plates = {20.0, 15.0, 10.0, 5.0, 2.5, 1.25};

        int i = 0;
        int ai = 0;

        Double[] MaxWeight = new Double[8];

        while (mw > 0) {
            if (mw >= plates[i]) {
                mw -= plates[i];
                MaxWeight[ai] = plates[i];
                ai++;
            } else {
                if (i < 5) {
                    i++;
                }
            }
        }
        return MaxWeight;
    }

    public String setPlates(Double nummer) {

        String plate = "";

        if (nummer <= 20) {
            plate = "Nur die Stange";
        } else {
            Double[] plates = getPlates(nummer);
            for (int i = 0; i < plates.length - 1; i++) {
                if (plates[i] != null) {
                    plate += String.valueOf(plates[i]) + " | ";
                }
            }
        }
        return plate;
    }

    public void startTraining() {

        String[] exercisesName = getNames();

        TextView textView10 = (TextView) findViewById(R.id.textView10);
        textView10.setText(exercisesName[index]);

        Double nummer;
        nummer = nummer();

        if (nummer > 40.0) {
            setWarmUp();
        }


        String erg5, erg6, erg7;

        erg5 = String.valueOf(nummer);
        erg6 = String.valueOf(nummer);
        erg7 = String.valueOf(nummer);

        TextView textView5 = (TextView) findViewById(R.id.textView32);
        TextView textView6 = (TextView) findViewById(R.id.textView33);
        TextView textView7 = (TextView) findViewById(R.id.textView34);

        textView5.setText(erg5);
        textView6.setText(erg6);
        textView7.setText(erg7);


        TextView textView = (TextView) findViewById(R.id.platestextview);
        TextView textView1 = (TextView) findViewById(R.id.platestextview1);
        TextView textView2 = (TextView) findViewById(R.id.platestextview2);

        String plates = setPlates(nummer());

        textView.setText(plates);
        textView1.setText(plates);
        textView2.setText(plates);
    }

    public void setWarmUp() {

        Double wup, wfirst, wsecond, wthird;
        int wint1, wint2, win3;

        wup = nummer();

        wfirst = (int) ((wup * 0.5) / 2.5) * 2.5;
        wsecond = (int) ((wup * 0.7) / 2.5) * 2.5;
        wthird = (int) ((wup * 0.9) / 2.5) * 2.5;

        TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;

        tv1 = (TextView) findViewById(R.id.warmup_sets);
        tv2 = (TextView) findViewById(R.id.warmup_sets1);
        tv3 = (TextView) findViewById(R.id.warmup_sets2);

        tv1.setText("5");
        tv2.setText("2 - 5");
        tv3.setText("1 - 2");

        tv4 = (TextView) findViewById(R.id.warmup_weight);
        tv5 = (TextView) findViewById(R.id.warmup_weight1);
        tv6 = (TextView) findViewById(R.id.warmup_weight2);

        tv4.setText(String.valueOf(wfirst));
        tv5.setText(String.valueOf(wsecond));
        tv6.setText(String.valueOf(wthird));

        tv7 = (TextView) findViewById(R.id.warmup_plates);
        tv8 = (TextView) findViewById(R.id.warmup_plates1);
        tv9 = (TextView) findViewById(R.id.warmup_plates2);

        String first = setPlates(wfirst);

        tv7.setText(first);
        tv8.setText(setPlates(wsecond));
        tv9.setText(setPlates(wthird));
    }

    public Double getBench() {

        Cursor c = getCursor();

        c.moveToLast();

        return Double.valueOf(c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_BENCH)));
    }

    public Double getOHP() {

        Cursor c = getCursor();

        c.moveToLast();

        return Double.valueOf(c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_OHP)));
    }

    public boolean successfulWorkout() {

        boolean didYouLift;

        TextView reps1, reps2, reps3;
        int[] textV1 = new int[3];

        reps1 = (TextView) findViewById(R.id.textReps1);
        reps2 = (TextView) findViewById(R.id.textReps2);
        reps3 = (TextView) findViewById(R.id.textReps3);

        textV1[0] = Integer.valueOf(reps1.getText().toString());
        textV1[1] = Integer.valueOf(reps2.getText().toString());
        textV1[2] = Integer.valueOf(reps3.getText().toString());

        if (textV1[0] == 5 && textV1[1] == 5 && textV1[2] == 5) {
            didYouLift = true;
        } else {
            didYouLift = false;
        }

        return didYouLift;
    }

    public ContentValues values() {

        ContentValues values = new ContentValues();
        String WORKOUT_ROW;

        double[] plusWeight = new double[3];


        for (int i = 0; i < plusWeight.length; i++) {
            if (exes[i] == true) {
                plusWeight[i] = 2.5;
            } else {
                plusWeight[i] = 0.0;
            }
        }

        values.put(DBHelper.Datenbank.COLUMN_SQUAT, extraMessageDoubleArray[0] + plusWeight[0]);

        if (getAorB() == 1) {
            WORKOUT_ROW = "Workout A";
            values.put(DBHelper.Datenbank.COLUMN_BENCH, String.valueOf(getBench() + plusWeight[1]));
            values.put(DBHelper.Datenbank.COLUMN_OHP, String.valueOf(getOHP()));
            values.put(DBHelper.Datenbank.COLUMN_TITEL, WORKOUT_ROW);
            setAorB(0);
        } else {

            WORKOUT_ROW = "Workout B";
            values.put(DBHelper.Datenbank.COLUMN_BENCH, String.valueOf(getBench()));
            values.put(DBHelper.Datenbank.COLUMN_OHP, String.valueOf(getOHP() + plusWeight[1]));
            values.put(DBHelper.Datenbank.COLUMN_TITEL, WORKOUT_ROW);
            setAorB(1);
        }

        values.put(DBHelper.Datenbank.COLUMN_DEADLIFT, extraMessageDoubleArray[2] + plusWeight[2]);

        return values;
    }

    public void test(View view) {

        TextView[] t1 = {
                (TextView) findViewById(R.id.textReps1),
                (TextView) findViewById(R.id.textReps2),
                (TextView) findViewById(R.id.textReps3)
        };

        for (int i = 0; i < t1.length; ++i) {
            t1[i].setText("5");
        }
        exes[index] = successfulWorkout();

        if (index < extraMessageDoubleArray.length - 1) {
            index++;

            if (index == extraMessageDoubleArray.length - 1) {
                CardView view1 = (CardView) findViewById(R.id.view1);
                CardView view2 = (CardView) findViewById(R.id.view2);
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
            }
            startTraining();
        } else {
            Intent intent = new Intent(this, StartActivity.class);

            SQLiteDatabase db = getDB();

            ContentValues values = values();

            long newRow;
            newRow = db.insert(DBHelper.Datenbank.TABLE_NAME, null, values);
            exes = new boolean[exes.length];
            startActivity(intent);


        }


    }

    public void prepareWarmUp(View view) {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.warmup_reps_weight_plates);

        if (linearLayout.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void decreaseReps(View view) {

        int alt = Integer.valueOf(((TextView) view).getText().toString());
        String neu = String.valueOf(alt - 1);

        TextView text = (TextView) view;
        if (alt > 0) {
            text.setText(neu);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

