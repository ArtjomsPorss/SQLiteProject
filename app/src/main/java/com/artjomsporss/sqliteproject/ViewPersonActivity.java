package com.artjomsporss.sqliteproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.artjomsporss.sqliteproject.database.DBFeederContract;
import com.artjomsporss.sqliteproject.database.DBHandler;

public class ViewPersonActivity extends AppCompatActivity {

    public static final String KEY_ID = "PERSON_ID";
    public static final String KEY_NAME = "PERSON_NAME";
    public static final String KEY_PHONE_NUM = "PERSON_PHONE_NUMBER";
    public static final String KEY_EMAIL = "PERSON_EMAIL";

    private int personID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        Intent intent = getIntent();

        String strID = intent.getStringExtra(KEY_ID);
        try{
            personID = Integer.parseInt(strID);
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
        }

        showPerson(strID
                , intent.getStringExtra(KEY_NAME)
                , intent.getStringExtra(KEY_PHONE_NUM)
                , intent.getStringExtra(KEY_EMAIL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showPerson(String id, String name, String phoneNum, String email){
        TextView idView = (TextView)findViewById(R.id.view_id);
        TextView nameView = (TextView)findViewById(R.id.view_name);
        TextView phoneNumView = (TextView)findViewById(R.id.view_phone_num);
        TextView emailView = (TextView)findViewById(R.id.view_email);

        idView.setText(id);
        nameView.setText(name);
        phoneNumView.setText(phoneNum);
        emailView.setText(email);
    }

    public void onClickRemove(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                Log.d("ID", "" + personID);
                dbHandler.removePerson(personID);
                setResult(1);
                finish();
            }
        }).start();
    }
}
