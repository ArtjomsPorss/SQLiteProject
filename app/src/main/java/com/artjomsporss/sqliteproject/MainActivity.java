package com.artjomsporss.sqliteproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.artjomsporss.sqliteproject.database.DBHandler;
import com.artjomsporss.sqliteproject.person.Person;

import java.util.List;

public class MainActivity extends ListActivity {

    private DBHandler mDBHandler;
    private List<Person> mPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void initListView(){
        mDBHandler = new DBHandler(getApplicationContext());
        mPeople = mDBHandler.getAllPeople();
        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<>(getApplicationContext()
                , R.layout.list_text_view
                , mPeople);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        Person person = mPeople.get(position);
        Intent intent = new Intent(this, ViewPersonActivity.class);
        intent.putExtra(ViewPersonActivity.KEY_ID, person.getID());
        intent.putExtra(ViewPersonActivity.KEY_NAME, person.getName());
        intent.putExtra(ViewPersonActivity.KEY_PHONE_NUM, person.getPhoneNum());
        intent.putExtra(ViewPersonActivity.KEY_EMAIL, person.getEmail());

        startActivityForResult(intent, 1);
    }

    public void onAddPersonClicked(View view){
        Intent intent = new Intent(this, AddPersonActivity.class);
        // Starts activity for result of code '1'
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1){
            initListView();
        }
    }
}
