package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    // Initialize variables
    protected SharedPreferenceHelper sharedPreferenceHelper;
    protected TextView eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferenceHelper = new SharedPreferenceHelper(DataActivity.this);

        getEventData();
    }

    public void getEventData(){
        eventName = (TextView) findViewById(R.id.data_counterA);
        eventName.setText(String.format("%s: %d events", sharedPreferenceHelper.getEventName(0), sharedPreferenceHelper.getEventValue("event1")));

        eventName = (TextView) findViewById(R.id.data_counterB);
        eventName.setText(String.format("%s: %d events", sharedPreferenceHelper.getEventName(1), sharedPreferenceHelper.getEventValue("event2")));

        eventName = (TextView) findViewById(R.id.data_counterC);
        eventName.setText(String.format("%s: %d events", sharedPreferenceHelper.getEventName(2), sharedPreferenceHelper.getEventValue("event3")));

        eventName = (TextView) findViewById(R.id.total_events);
        eventName.setText(String.format("Total events: %d", sharedPreferenceHelper.getTotalEvents()));
    }

    // Display options menu in task-bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.datamenu, menu);
        return true;
    }

    // Create the action when an option on the task-bar is selected
    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.toggle_event_names) {
            Toast.makeText(this, "Item 2 Clicks", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // Navigate back to homepage on task-bar return
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

