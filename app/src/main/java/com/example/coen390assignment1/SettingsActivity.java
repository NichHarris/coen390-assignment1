package com.example.coen390assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.example.coen390assignment1.Database.Config;
import com.example.coen390assignment1.Database.DatabaseHelper;

public class SettingsActivity extends AppCompatActivity {

    // Initialize variables
    protected EditText editEvent1;
    protected EditText editEvent2;
    protected EditText editEvent3;
    protected EditText maxCount;
    protected Button saveButton;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    protected DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Initialize shared pref helper
        sharedPreferenceHelper = new SharedPreferenceHelper(SettingsActivity.this);
        // Initialize db helper
        dbHelper = new DatabaseHelper(this, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
        // Add task-bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editEvent1 = findViewById(R.id.editTextCounter1);
        editEvent2 = findViewById(R.id.editTextCounter2);
        editEvent3 = findViewById(R.id.editTextCounter3);
        maxCount = findViewById(R.id.editTextMaxCount);
        saveButton = findViewById(R.id.saveButton);

        modifyEvents(sharedPreferenceHelper.getEditMode());

        // Save all entered data on event click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Toast message
                String message = "Save successful";

                // Clear the DB
                dbHelper.clearDb(Config.COUNTER_TABLE_NAME);

                // Clear previous data
                sharedPreferenceHelper.removeData("totalEvents");
                sharedPreferenceHelper.removeData("event1");
                sharedPreferenceHelper.removeData("event2");
                sharedPreferenceHelper.removeData("event3");

                // Get all entered strings
                String event1 = editEvent1.getText().toString();
                String event2 = editEvent2.getText().toString();
                String event3 = editEvent3.getText().toString();
                String max = maxCount.getText().toString();

                if (event1.equals("") || event2.equals("") || event3.equals("") || max.equals("")){
                    message = "Wrong or missing attribute. Nothing will be saved, staying in edit more";
                }
                else{
                    sharedPreferenceHelper = new SharedPreferenceHelper(SettingsActivity.this);
                    sharedPreferenceHelper.saveEventName(event1, 0);
                    sharedPreferenceHelper.saveEventName(event2, 1);
                    sharedPreferenceHelper.saveEventName(event3, 2);
                    sharedPreferenceHelper.saveMaxCount(max);
                    sharedPreferenceHelper.setEditMode(false);

                    modifyEvents(sharedPreferenceHelper.getEditMode());
                }
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        modifyEvents(sharedPreferenceHelper.getEditMode());
        if(!sharedPreferenceHelper.getEditMode()) {
            editEvent1 = findViewById(R.id.editTextCounter1);
            editEvent2 = findViewById(R.id.editTextCounter2);
            editEvent3 = findViewById(R.id.editTextCounter3);
            maxCount = findViewById(R.id.editTextMaxCount);

            editEvent1.setHint(sharedPreferenceHelper.getEventName(0));
            editEvent2.setHint(sharedPreferenceHelper.getEventName(1));
            editEvent3.setHint(sharedPreferenceHelper.getEventName(2));
            maxCount.setHint(sharedPreferenceHelper.getMaxCount()+"");
        }
    }

    // Display options menu in task-bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbarmenu, menu);
        return true;
    }

    // Create the action when an option on the task-bar is selected
    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.edit_settings) {
            modifyEvents(true);
        }
        return super.onOptionsItemSelected(item);
    }

    // Navigate back to homepage on task-bar return
    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

    // Set text boxes and buttons to enabled/disabled
    public void modifyEvents(boolean enabled){
        editEvent1.setEnabled(enabled);
        editEvent2.setEnabled(enabled);
        editEvent3.setEnabled(enabled);
        maxCount.setEnabled(enabled);
        saveButton.setEnabled(enabled);
    }
}