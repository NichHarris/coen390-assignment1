package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coen390assignment1.Database.Config;
import com.example.coen390assignment1.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    // Initialize variables
    protected SharedPreferenceHelper sharedPreferenceHelper;
    protected DatabaseHelper dbHelper;
    protected TextView eventName;
    protected ListView historyList;
    private final String[] defaultNames = {"Counter 1", "Counter 2", "Counter 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize shared pref helper
        sharedPreferenceHelper = new SharedPreferenceHelper(DataActivity.this);
        // Initialize db helper
        dbHelper = new DatabaseHelper(this, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);

        if (sharedPreferenceHelper.getDataActivityMode()) {
            setEventData(sharedPreferenceHelper.getEventName(0), sharedPreferenceHelper.getEventName(1), sharedPreferenceHelper.getEventName(2), true);
        }
        else {
            setEventData(defaultNames[0], defaultNames[1], defaultNames[2], false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sharedPreferenceHelper.getDataActivityMode()) {
            setEventData(sharedPreferenceHelper.getEventName(0), sharedPreferenceHelper.getEventName(1), sharedPreferenceHelper.getEventName(2), true);
        }
        else {
            setEventData(defaultNames[0], defaultNames[1], defaultNames[2], false);
        }
    }

    public void setEventData(String event1, String event2, String event3, boolean mode) {
        eventName = (TextView) findViewById(R.id.data_counterA);
        eventName.setText(String.format("%s: %d events", event1, sharedPreferenceHelper.getEventValue(0)));

        eventName = (TextView) findViewById(R.id.data_counterB);
        eventName.setText(String.format("%s: %d events", event2, sharedPreferenceHelper.getEventValue(1)));

        eventName = (TextView) findViewById(R.id.data_counterC);
        eventName.setText(String.format("%s: %d events", event3, sharedPreferenceHelper.getEventValue(2)));

        eventName = (TextView) findViewById(R.id.total_events);
        eventName.setText(String.format("Total events: %d", sharedPreferenceHelper.getTotalEvents()));

        historyList = (ListView) findViewById(R.id.history_list);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, convertList(dbHelper.getAllCounters(), mode));
        historyList.setAdapter(arrayAdapter);
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
                setEventData(defaultNames[0], defaultNames[1], defaultNames[2], false);
            }
            else {
                // swap mode set
                sharedPreferenceHelper.setDataActivityMode(true);
                // swap to event names used in settings
                setEventData(sharedPreferenceHelper.getEventName(0), sharedPreferenceHelper.getEventName(1), sharedPreferenceHelper.getEventName(2), true);
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

    // Convert list of tuples from the DB to list of strings based on current Mode for DataActivity
    // Conversion is done after the fact since Name is stored in the DB as: 1, 2, 3 since it is generic
    // If the mode if set to name mode then can use the names stored in shared data to create the final strings
    private List<String> convertList(List<Pair<String, Integer>> iList, boolean mode) {
        List<String> returnList = new ArrayList<>();
        if (mode){
            for (int i = 0; i < iList.size(); i++) {
                switch (iList.get(i).first) {
                    case "1":
                        returnList.add(String.format("%s %d", sharedPreferenceHelper.returnName(0), iList.get(i).second));
                        break;
                    case "2":
                        returnList.add(String.format("%s %d", sharedPreferenceHelper.returnName(1), iList.get(i).second));
                        break;
                    case "3":
                        returnList.add(String.format("%s %d", sharedPreferenceHelper.returnName(2), iList.get(i).second));
                        break;
                }
            }
        }
        else {
            for (int i = 0; i < iList.size(); i++) {
                returnList.add(String.format("%s %d", iList.get(i).first, iList.get(i).second));
            }
        }
        return returnList;
    }
}

