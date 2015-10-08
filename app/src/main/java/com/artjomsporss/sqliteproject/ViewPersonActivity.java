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
import com.artjomsporss.sqliteproject.person.Person;

public class ViewPersonActivity extends AppCompatActivity {

    public static final String KEY_ID = "PERSON_ID";
    public static final String KEY_NAME = "PERSON_NAME";
    public static final String KEY_PHONE_NUM = "PERSON_PHONE_NUMBER";
    public static final String KEY_EMAIL = "PERSON_EMAIL";
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        Intent intent = getIntent();

        this.person = new Person();
        person.setID(intent.getIntExtra(KEY_ID, -1));
        person.setName(intent.getStringExtra(KEY_NAME));
        person.setPhoneNum(intent.getStringExtra(KEY_PHONE_NUM));
        person.setEmail(intent.getStringExtra(KEY_EMAIL));

        showPerson();
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

    public void showPerson(){
        TextView nameView = (TextView)findViewById(R.id.view_name);
        TextView phoneNumView = (TextView)findViewById(R.id.view_phone_num);
        TextView emailView = (TextView)findViewById(R.id.view_email);

        nameView.setText(this.person.getName());
        phoneNumView.setText(this.person.getPhoneNum());
        emailView.setText(this.person.getEmail());
    }

    public void onClickRemove(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.removePerson(person.getID());
                setResult(1);
                finish();
            }
        }).start();
    }
}
