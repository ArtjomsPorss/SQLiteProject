package com.artjomsporss.sqliteproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.artjomsporss.sqliteproject.database.DBHandler;
import com.artjomsporss.sqliteproject.person.Person;

public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_person, menu);
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

    public void addNewPerson(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                EditText nameField = (EditText) findViewById(R.id.edit_text_name);
                EditText phoneNumberField = (EditText) findViewById(R.id.edit_text_phone_number);
                EditText emailField = (EditText) findViewById(R.id.edit_text_email);

                Person person = new Person();
                person.setEmail(emailField.getText().toString());
                person.setPhoneNum(phoneNumberField.getText().toString());
                person.setName(nameField.getText().toString());
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.addPerson(person);
                setResult(1);
                finish();
            }
        }).start();
    }
}
