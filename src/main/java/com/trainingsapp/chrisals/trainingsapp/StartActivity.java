package com.trainingsapp.chrisals.trainingsapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    public boolean firstTimeHere = true;
    public Cursor cursor;
    int var = 0;
    private List<WorkOut> WorkOutList = new ArrayList<>();
    private RecyclerView recyclerView;
    private WorkOutAdapter wAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        wAdapter = new WorkOutAdapter(WorkOutList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(wAdapter);
        wAdapter.notifyDataSetChanged();

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dosome(view);
            }
        });


        prepareWorkout();
        setFirstTimeHere();
        getCursor();


    }

    public String[] getRessources() {
        Resources res = getResources();
        return res.getStringArray(R.array.Exercises);
    }


    public String[] getWorkoutAnames() {

        String[] WorkoutA = {
                getRessources()[0],
                getRessources()[1],
                getRessources()[3]};

        return WorkoutA;
    }

    public String[] getWorkoutBnames() {

        String[] WorkoutB = {
                getRessources()[0],
                getRessources()[2],
                getRessources()[3]};

        return WorkoutB;
    }


    public SQLiteDatabase OpenDatabase() {
        DB helfer = new DB(this);
        SQLiteDatabase db = helfer.getReadableDatabase();

        return db;
    }

    public int getAorB() {
        SQLiteDatabase db = OpenDatabase();

        String[] projection = {
                DBHelper.Datenbank.COLUMN_VARVALUE,};

        Cursor c = db.query(DBHelper.Datenbank.TABLE_NAME_3, projection, null, null, null, null, null, null);
        c.moveToLast();
        return c.getInt(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_VARVALUE));
    }

    public void setAorB(int aorB) {

        SQLiteDatabase db = OpenDatabase();

        ContentValues values = new ContentValues();

        values.put(DBHelper.Datenbank.COLUMN_VARVALUE, String.valueOf(aorB));

        long newRow;
        newRow = db.insert(DBHelper.Datenbank.TABLE_NAME_3, null, values);
    }

    public Cursor getCursor() {

        SQLiteDatabase db = OpenDatabase();

        String[] projection = {
                DBHelper.Datenbank.COLUMN_TITEL,
                DBHelper.Datenbank.COLUMN_SQUAT,
                DBHelper.Datenbank.COLUMN_BENCH,
                DBHelper.Datenbank.COLUMN_OHP,
                DBHelper.Datenbank.COLUMN_DEADLIFT
        };

        Cursor c = db.query(DBHelper.Datenbank.TABLE_NAME, projection, null, null, null, null, null, null);
        this.cursor = c;
        return c;
    }

    public String[] cursorGetMaxesA(int position) {
        Cursor c = cursor;
        c.moveToPosition(position);

        String[] maxesA = {
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_SQUAT)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_BENCH)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_DEADLIFT)),
        };

        return maxesA;
    }

    public String[] cursorGetMaxesB(int position) {
        Cursor c = cursor;
        c.moveToPosition(position);

        String[] maxesB = {
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_SQUAT)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_OHP)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_DEADLIFT)),};

        return maxesB;
    }

    public void prepareWorkout() {
        Cursor c = getCursor();


        if (c != null && c.getCount() > 0) {
            c.moveToLast();
            String workoutGruppe = c.getString(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_TITEL));
            if (c.getCount() == 1) {
                setAorB(1);
                WorkOut workOut = new WorkOut(getWorkoutAnames(), cursorGetMaxesA(c.getCount() - 1), workoutGruppe, "First");
                WorkOutList.add(workOut);
            } else {

                if (getAorB() == 0) {
                    WorkOut workoutB = new WorkOut(getWorkoutBnames(), cursorGetMaxesB(c.getCount() - 1), "Workout B", "Next");
                    WorkOutList.add(workoutB);

                    WorkOut workoutA = new WorkOut(getWorkoutAnames(), cursorGetMaxesA(c.getCount() - 2), "Workout A", "Last");
                    WorkOutList.add(workoutA);

                } else {
                    WorkOut workoutA1 = new WorkOut(getWorkoutAnames(), cursorGetMaxesA(c.getCount() - 1), "Workout A", "Next");
                    WorkOutList.add(workoutA1);

                    WorkOut workoutB1 = new WorkOut(getWorkoutBnames(), cursorGetMaxesB(c.getCount() - 2), "Workout B", "Last");
                    WorkOutList.add(workoutB1);

                }
            }
        }
    }


    public void dosome(View view) {
        Intent intent1 = new Intent(this, DisplayMessageActivity.class);
        Intent intent = new Intent(this, StartActivity.class);

        if (cursor != null && cursor.getCount() > 0) {
            startActivity(intent1);

        } else {
            // previously invisible view
            View myView = findViewById(R.id.testtestestetst);
            ViewGroup view1 = (ViewGroup) findViewById(R.id.content_start_activity);
            FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab1);

            float x = -view1.getWidth() / 2 + floatingActionButton.getWidth() / 2;
            float y = -view1.getHeight() / 2 + floatingActionButton.getHeight() / 2;

            AlphaAnimation alpha = new AlphaAnimation(1, 0);


            TranslateAnimation animation = new TranslateAnimation(0, x, 0, y);

            //create the animator for this view (the start radius is zero)

            Animator anim = ViewAnimationUtils.createCircularReveal(myView,
                    myView.getWidth(),
                    myView.getHeight(),
                    0,
                    (float) Math.hypot(myView.getWidth(), myView.getHeight()));

            // make the view visible and start the animation

            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.setInterpolator(new AccelerateDecelerateInterpolator());


            myView.setVisibility(View.VISIBLE);
            floatingActionButton.setVisibility(View.GONE);

            animation.setDuration(200);
            anim.setDuration(500);

            alpha.setDuration(1000);

            Animation logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.call_main_acitivity);
            floatingActionButton.startAnimation(logoMoveAnimation);


            //alpha.start();
            //animation.start();

            anim.start();
            myView.setEnabled(true);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you realy want to reset the database ? All progress will be gone");
        builder.setCancelable(true);


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DB helfer = new DB(getApplicationContext());
                SQLiteDatabase db = helfer.getReadableDatabase();
                db.execSQL("DELETE FROM  " + DBHelper.Datenbank.TABLE_NAME);
                db.execSQL("DELETE FROM " + DBHelper.Datenbank.TABLE_NAME_3);

                Intent intent = new Intent(getApplicationContext(), StartActivity.class);

                startActivity(intent);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog allert11 = builder.create();
        allert11.show();

        return super.onOptionsItemSelected(item);
    }

    public void setFirstTimeHere() {
        firstTimeHere = false;
    }


    public String[] message() {
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4);

        String[] message = {
                editText1.getText().toString(),
                editText2.getText().toString(),
                editText3.getText().toString(),
                editText4.getText().toString()};

        for (int i = 0; i < message.length; i++) {
            if (message[i].length() == 0) {
                message[i] = "20";
            }
        }
        var = 0;
        for (int i = 0; i < message.length; i++) {
            if (Double.parseDouble(message[i]) == 0 || Double.parseDouble(message[i]) >= 2.5) {
                if (Double.parseDouble(message[i]) % 2.5 == 0 && Double.parseDouble(message[i]) <= 500) {
                    ++var;
                }
            }
        }

        return message;

    }


    public void sendMessage(View view) {

        String[] message = message();
        Intent intent = new Intent(this, StartActivity.class);


        if (var == 4) {
            DB helfer = new DB(this);
            SQLiteDatabase db = helfer.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DBHelper.Datenbank.COLUMN_ID, 0);
            values.put(DBHelper.Datenbank.COLUMN_TITEL, "Workout A");

            values.put(DBHelper.Datenbank.COLUMN_SQUAT, message[0]);

            values.put(DBHelper.Datenbank.COLUMN_BENCH, message[1]);

            values.put(DBHelper.Datenbank.COLUMN_OHP, message[2]);

            values.put(DBHelper.Datenbank.COLUMN_DEADLIFT, message[3]);

            long newRow;
            newRow = db.insert(DBHelper.Datenbank.TABLE_NAME, null, values);

            // previously visible view
            final View myView = findViewById(R.id.testtestestetst);

            // get the center for the clipping circle
            int cx = myView.getWidth();
            int cy = myView.getHeight();

            // get the initial radius for the clipping circle
            float initialRadius = (float) Math.hypot(cx, cy);

            // create the animation (the final radius is zero)
            final Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.setDuration(500);


            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            });
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
            Animation logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.go_back_to);
            fab.startAnimation(logoMoveAnimation);
            fab.setVisibility(View.VISIBLE);
            // start the animation
            anim.start();
            wAdapter.notifyDataSetChanged();
            startActivity(intent);


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter a VALID weight");
            builder.setCancelable(true);


            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog allert11 = builder.create();
            allert11.show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


