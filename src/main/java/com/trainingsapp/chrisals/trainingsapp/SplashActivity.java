package com.trainingsapp.chrisals.trainingsapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent firstTime = new Intent(this, TutorialViewPagerActivity.class);
        Intent notFirstTime = new Intent(this, StartActivity.class);


        DB helfer = new DB(this);
        SQLiteDatabase db = helfer.getWritableDatabase();

        String[] projection = {
                DBHelper.Datenbank.COLUMN_VARVALUE,
        };

        Cursor c = db.query(DBHelper.Datenbank.TABLE_NAME_2, projection, null, null, null, null, null, null);

        int firstimehere = 0;

        if (c.getCount() != 0) {
            c.moveToFirst();
            firstimehere = c.getInt(c.getColumnIndexOrThrow(DBHelper.Datenbank.COLUMN_VARVALUE));
        }
        if (firstimehere == 1 || c.getCount() != 0) {
            startActivity(notFirstTime);
        } else {
            startActivity(notFirstTime);
        }


        finish();

    }
}

