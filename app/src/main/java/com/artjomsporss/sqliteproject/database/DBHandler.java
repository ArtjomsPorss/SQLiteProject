package com.artjomsporss.sqliteproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaDataSource;

import com.artjomsporss.sqliteproject.person.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User1 on 07/10/2015.
 * used for handling the database
 */
public class DBHandler {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBOpenHelper mDBOpenHelper;

    public DBHandler(Context context){
        mContext = context;
    }

    public void addPerson(Person p){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getWritableDatabase();

        // Stores the values that preparing to be stored in a database
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBFeederContract.PersonTable.COLUMN_NAME, p.getName());
        contentValues.put(DBFeederContract.PersonTable.COLUMN_EMAIL, p.getEmail());
        contentValues.put(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER, p.getPhoneNum());

        mDatabase.insert(DBFeederContract.PersonTable.TABLE_NAME, null, contentValues);
        mDatabase.close(); mDBOpenHelper.close();
        // The database is in its own filesystem, if you reference mDatabase to null, the table is still there
        mDatabase = null; mDBOpenHelper = null;
    }

    public List<Person> getAllPeople(){
        List<Person> people = new ArrayList<>();

        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getReadableDatabase();

        // rawQuery() returns a cursor
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBFeederContract.PersonTable.TABLE_NAME, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                int tempID = cursor.getInt(cursor.getColumnIndex(DBFeederContract.PersonTable._ID));
                String tempName = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_NAME));
                String tempPhoneNumber = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER));
                String tempEmail = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_EMAIL));

                Person person = new Person(tempEmail, tempID, tempName, tempPhoneNumber);
                people.add(person);
            }while(cursor.moveToNext());
            cursor.close();
        }
        mDBOpenHelper.close();
        mDatabase.close();
        mDBOpenHelper = null; mDatabase = null;

        return new ArrayList<>(people);
    }

    public Person getPerson(int id){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getReadableDatabase();
        Person person;

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBFeederContract.PersonTable.TABLE_NAME
                + " WHERE " + DBFeederContract.PersonTable._ID
                + " = " + id
                , null);

        if(cursor.getCount() == 0){
            person = null;
        }else {
            cursor.moveToFirst();
            String tempName = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_NAME));
            String tempPhoneNumber = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER));
            String tempEmail = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_EMAIL));

            person = new Person(tempEmail, id, tempName, tempPhoneNumber);

            cursor.close();
            mDBOpenHelper.close();
            mDatabase.close();
            mDBOpenHelper = null;
            mDatabase = null;
        }

        return person;
    }

    public void removePerson(int id){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getWritableDatabase();

        // execSQL() doesn't return a cursor
        mDatabase.execSQL("DELETE FROM " + DBFeederContract.PersonTable.TABLE_NAME
            + " WHERE " + DBFeederContract.PersonTable._ID + " = " + id);

        mDBOpenHelper.close(); mDatabase.close();
        mDBOpenHelper = null; mDatabase = null;
    }
}
