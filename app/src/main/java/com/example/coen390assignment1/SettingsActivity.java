package com.example.coen390assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    // Initialize variables
    protected EditText editEvent1;
    protected EditText editEvent2;
    protected EditText editEvent3;
    protected EditText maxCount;
    protected Button saveButton;
    protected SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Add task-bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editEvent1 = findViewById(R.id.editTextCounter1);
        editEvent2 = findViewById(R.id.editTextCounter2);
        editEvent3 = findViewById(R.id.editTextCounter3);
        maxCount = findViewById(R.id.editTextMaxCount);
        saveButton = findViewById(R.id.saveButton);

        // Save all entered data on event click
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Get all entered strings
                String event1 = editEvent1.getText().toString();
                String event2 = editEvent2.getText().toString();
                String event3 = editEvent3.getText().toString();
                String max = maxCount.getText().toString();

                sharedPreferenceHelper = new SharedPreferenceHelper(SettingsActivity.this);
                sharedPreferenceHelper.saveEventName(event1, 1);
                sharedPreferenceHelper.saveEventName(event2, 2);
                sharedPreferenceHelper.saveEventName(event3, 3);
                sharedPreferenceHelper.saveMaxCount(max);

                Toast toast = Toast.makeText(getApplicationContext(), "Save successful", Toast.LENGTH_LONG);
                toast.show();
            }
        });
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

            Toast.makeText(this, "Item Clicks", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    // Navigate back to homepage on task-bar return
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}