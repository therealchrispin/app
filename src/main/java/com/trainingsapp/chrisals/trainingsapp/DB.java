package com.trainingsapp.chrisals.trainingsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.inputmethodservice.Keyboard;

import java.util.Date;

import static com.trainingsapp.chrisals.trainingsapp.DBHelper.*;

/**
 * Created by chris.als on 27.04.16.
 */
public class DB extends SQLiteOpenHelper {
    public static final String DB_NAME = "Trainingsplan";
    public static int DATABASE_VERSION = 1;


    public DB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ENTRY);
        db.execSQL(CREATE_VAR_ENTRY);
        db.execSQL(CREATE_VAR_ENTRY_2);

    }


    public void onUpgrade(SQLiteDatabase db, int newversion, int oldversion) {
        db.execSQL(DBHelper.Datenbank.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}


