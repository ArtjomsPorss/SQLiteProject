package com.artjomsporss.sqliteproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User1 on 07/10/2015.
 */
public class DBOpenHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "person_database";
    public static final int DATABASE_VERSION = 1;
    public static final String TEXT_TYPE = " TEXT";

    public static final String SQL_CREATE_PERSON_TABLE = "CREATE TABLE "
            + DBFeederContract.PersonTable.TABLE_NAME + " ("
            + DBFeederContract.PersonTable._ID + " INTEGER PRIMARY KEY,"
            + DBFeederContract.PersonTable.COLUMN_NAME + TEXT_TYPE + ","
            + DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER + TEXT_TYPE + ","
            + DBFeederContract.PersonTable.COLUMN_EMAIL + TEXT_TYPE + ")";

    public DBOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
