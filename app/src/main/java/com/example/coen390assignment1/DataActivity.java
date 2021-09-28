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
    private String[] defaultNames = {"Counter 1", "Counter 2", "Counter 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferenceHelper = new SharedPreferenceHelper(DataActivity.this);

        if (sharedPreferenceHelper.getDataActivityMode()){
            setEventData(sharedPreferenceHelper.getEventName(0), sharedPreferenceHelper.getEventName(1), sharedPreferenceHelper.getEventName(2));
        }
        else {
            setEventData(defaultNames[0], defaultNames[1], defaultNames[2]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sharedPreferenceHelper.getDataActivityMode()){
            setEventData(sharedPreferenceHelper.getEventName(0), sharedPreferenceHelper.getEventName(1), sharedPreferenceHelper.getEventName(2));
        }
        else {
            setEventData(defaultNames[0], defaultNames[1], defaultNames[2]);
        }
    }

    public void setEventData(String event1, String event2, String event3){
        eventName = (TextView) findViewById(R.id.data_counterA);
        eventName.setText(String.format("%s: %d events", event1, sharedPreferenceHelper.getEventValue(0)));

        eventName = (TextView) findViewById(R.id.data_counterB);
        eventName.setText(String.format("%s: %d events", event2, sharedPreferenceHelper.getEventValue(1)));

        eventName = (TextView) findViewById(R.id.data_counterC);
        eventName.setText(String.format("%s: %d events", event3, sharedPreferenceHelper.getEventValue(2)));

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
            if (sharedPreferenceHelper.getDataActivityMode()) {
                // swap mode set
                sharedPreferenceHelper.setDataActivityMode(false);
                // swap to default event names
                setEventData(defaultNames[0], defaultNames[1], defaultNames[2]);
            }
            else {
                // swap mode set
                sharedPreferenceHelper.setDataActivityMode(true);
                // swap to event names used in settings
                setEventData(sharedPreferenceHelper.getEventName(0), sharedPreferenceHelper.getEventName(1), sharedPreferenceHelper.getEventName(2));

            }
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

